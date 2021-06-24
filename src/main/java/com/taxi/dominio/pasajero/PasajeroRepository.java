package com.taxi.dominio.pasajero;

import java.util.List;

/**
 *
 * @author rjacome
 */
public interface PasajeroRepository {
    
    public List<Pasajero> obtener() throws Exception;
    
    public Pasajero obtenerPorId(int id) throws Exception;
}
