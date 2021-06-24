package com.taxi.dominio.viaje;

import com.taxi.dominio.conductor.Conductor;
import com.taxi.dominio.conductor.ConductorRepository;
import com.taxi.dominio.conductor.ConductorService;
import com.taxi.dominio.pasajero.PasajeroRepository;
import com.taxi.dominio.shared.Id;
import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;
import com.taxi.dominio.ubicacion.Ubicacion;

import java.util.List;

/**
 *
 * @author rjacome
 */
public class ViajeService {
    private final ViajeRepository viajeRepository;
    private final ConductorRepository conductorRepository;
    private final PasajeroRepository pasajeroRepository;
    
    private final ConductorService conductorService;

    /**
     * Obtener
     * @param viajeRepository 
     */
    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
        this.conductorRepository = null;
        this.pasajeroRepository = null;
        this.conductorService = null;
    }
    
    /**
     * asociar conductor, terminarViaje
     * @param viajeRepository
     * @param conductorRepository 
     */
    public ViajeService(
        ViajeRepository viajeRepository, ConductorRepository conductorRepository
    ) {
        this.viajeRepository = viajeRepository;
        this.conductorRepository = conductorRepository;
        this.pasajeroRepository = null;
        this.conductorService = null;
    }

    /**
     * crear viaje
     * @param viajeRepository
     * @param conductorRepository
     * @param pasajeroRepository 
     */
    public ViajeService(
        ViajeRepository viajeRepository
        , ConductorRepository conductorRepository
        , PasajeroRepository pasajeroRepository
        , ConductorService conductorService
    ) {
        this.viajeRepository = viajeRepository;
        this.conductorRepository = conductorRepository;
        this.pasajeroRepository = pasajeroRepository;
        this.conductorService = conductorService;
    }
    
    public ViajeConductoresDisponibles crear(Viaje entidad) throws Exception {
        if (entidad == null) {
            throw new OperationNotPermittedTaxiException(
                "La entidad es requerida"
            );
        }
        
        if (entidad.getIdPasajero() < 0) {
            throw new OperationNotPermittedTaxiException(
                "El id del pasajero es requerido"
            );
        }
        
        if (pasajeroRepository.obtenerPorId(entidad.getIdPasajero()) == null) {
            throw new OperationNotPermittedTaxiException(
                "El pasajero no existe"
            );
        }
        
//      los viajes nuevos por default estarán pendientes
        entidad.setEstado("P");
        
        this.viajeRepository.crear(entidad);
        
        ViajeConductoresDisponibles viajeConductoresDisponibles = 
            new ViajeConductoresDisponibles(
                entidad.getId()
            );
        ;
        
        List<Conductor> conductoresDisponibles = 
            this.conductorService.obtenerDisponiblesPorUbicacion(
                new Ubicacion(
                    entidad.getLatitud(), entidad.getLongitud()
                )
            )
        ;
        
        viajeConductoresDisponibles.setConductores(conductoresDisponibles);
        
        return viajeConductoresDisponibles;
    }
    
    /**
     * Obtener listado de viajes activos
     * @return
     * @throws Exception 
     */
    public List<Viaje> obtener() throws Exception {
        return this.viajeRepository.obtener();
    }
    
    public void asociarConductor(Viaje entidad) throws Exception {
        if (entidad == null) {
            throw new OperationNotPermittedTaxiException(
                "La entidad es requerida"
            );
        }
        
        if (entidad.getId() < 1) {
            throw new OperationNotPermittedTaxiException(
                "El id del viaje es requerido"
            );
        }
        
        if (entidad.getIdConductor() < 1) {
            throw new OperationNotPermittedTaxiException(
                "El id del conductor es requerido"
            );
        }
        
        if (!viajeRepository.verificarExistencia(entidad.getId())) {
            throw new OperationNotPermittedTaxiException(
                "El viaje no existe"
            );
        }
        
        if (conductorRepository.obtenerPorId(entidad.getIdConductor()) == null) {
            throw new OperationNotPermittedTaxiException(
                "El conductor no existe"
            );
        }
        
        this.viajeRepository.actualizar(
            entidad.getId(), null, entidad.getIdConductor()
        );
        
        // cambiar la disponibilidad del conductor
        this.conductorRepository.actualizar(entidad.getIdConductor(), false);
    }
    
    public void terminarViaje(Id id) throws Exception {
        if (id == null) {
            throw new OperationNotPermittedTaxiException(
                "El id del viaje es requerido"
            );
        }
        
        if (id.getId() < 1) {
            throw new OperationNotPermittedTaxiException(
                "El id del viaje es requerido"
            );
        }
        
        if (!viajeRepository.verificarExistencia(id.getId())) {
            throw new OperationNotPermittedTaxiException(
                "El viaje no existe"
            );
        }
        
        this.viajeRepository.actualizar(id.getId(), "C", 0);
        
        Viaje viaje = this.viajeRepository.obtenerPorId(id.getId());
        
        this.conductorRepository.actualizar(viaje.getIdConductor(), true);
    }
}
