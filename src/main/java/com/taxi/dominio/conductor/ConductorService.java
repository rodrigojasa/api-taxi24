package com.taxi.dominio.conductor;

import com.taxi.dominio.shared.Id;
import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;
import com.taxi.dominio.ubicacion.Ubicacion;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rjacome
 */
public class ConductorService {
    private final ConductorRepository conductorRepository;
    private static final double DISTANCIAKM = 3;

    public ConductorService(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }
    
    /**
     * Obtener listdado de conductores, se pueden filtrar los disponibles
     * @param filtro
     * @return
     * @throws Exception 
     */
    public List<Conductor> obtener(ConductorFiltroObtener filtro) throws Exception {
        return this.conductorRepository.obtener(filtro);
    }
    
    /**
     * Obtener conductor por id
     * @param idConductor
     * @return
     * @throws Exception 
     */
    public Conductor obtenerPorId(Id idConductor) throws Exception {
        if (idConductor == null) {
            throw new OperationNotPermittedTaxiException(
                "El id del conductor es requerido"
            );
        }
        
        if (idConductor.getId() < 1) {
            throw new OperationNotPermittedTaxiException(
                "El id del conductor es requerido"
            );
        }
        
        Conductor conductor = this.conductorRepository.obtenerPorId(idConductor.getId());
        
        if (conductor == null) {
            throw new OperationNotPermittedTaxiException(
                "El id del conductor no existe"
            );
        }
        
        return conductor;
    }
    
    public List<Conductor> obtenerDisponiblesPorUbicacion(Ubicacion filtro) throws Exception {
        if (filtro == null) {
            throw new OperationNotPermittedTaxiException("El filtro es requerido");
        }
        
        if (filtro.getLatitud() < -90 || filtro.getLatitud() > 90) {
            throw new OperationNotPermittedTaxiException(
                "La latitud se encuentra fuera del rango(-90, 90)"
            );
        }
        
        if (filtro.getLongitud() < -180 || filtro.getLongitud()> 180) {
            throw new OperationNotPermittedTaxiException(
                "La longitud se encuentra fuera del rango(-180, 180)"
            );
        }
        
        ConductorFiltroObtener filtroDisponible = new ConductorFiltroObtener(true);
        
        List<Conductor> conductoresDisponibles = 
            this.conductorRepository.obtener(filtroDisponible)
        ;        
        
        List<Conductor> conductoresCercanos = conductoresDisponibles.stream().filter(
            conductor -> ConductorService.distanciaEntreCoordenadas(
                conductor.getUbicacion().getLatitud()
                , conductor.getUbicacion().getLongitud()
                , filtro.getLatitud()
                , filtro.getLongitud()
            ) <= ConductorService.DISTANCIAKM
        ).collect(Collectors.toList());
        
        return conductoresCercanos;
    }
    
    private static double distanciaEntreCoordenadas(
        double latitud1, double longitud1, double latitud2, double longitud2
    ) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        
        double dLat = Math.toRadians(latitud2 - latitud1);
        
        double dLng = Math.toRadians(longitud2 - longitud1);
        
        double sindLat = Math.sin(dLat / 2);
        
        double sindLng = Math.sin(dLng / 2);
        
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) 
                        * Math.cos(Math.toRadians(latitud1)) 
                        * Math.cos(Math.toRadians(latitud2))
        ;
        
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        
        double distancia = radioTierra * va2;
        
        return distancia;
    }
}
