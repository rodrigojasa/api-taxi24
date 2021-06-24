package com.taxi.dominio.pasajero;

import com.taxi.dominio.pasajero.Pasajero;
import com.taxi.dominio.pasajero.PasajeroRepository;
import com.taxi.dominio.shared.Id;
import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;

import java.util.List;

/**
 *
 * @author rjacome
 */
public class PasajeroService {
    private final PasajeroRepository pasajeroRepository;

    public PasajeroService(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }
    
    public List<Pasajero> obtener() throws Exception {
        return this.pasajeroRepository.obtener();
    }
    
    public Pasajero obtenerPorId(Id idPasajero) throws Exception {
        if (idPasajero == null) {
            throw new OperationNotPermittedTaxiException(
                "El id del pasajero es requerido"
            );
        }
        
        if (idPasajero.getId() < 1) {
            throw new OperationNotPermittedTaxiException(
                "El id del pasajero es requerido"
            );
        }
        
        Pasajero pasajero = this.pasajeroRepository.obtenerPorId(idPasajero.getId());
        
        if (pasajero == null) {
            throw new OperationNotPermittedTaxiException(
                "El id del pasajero no existe"
            );
        }
        
        return pasajero;
    }
}
