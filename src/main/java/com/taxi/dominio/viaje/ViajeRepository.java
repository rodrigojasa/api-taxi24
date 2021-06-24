package com.taxi.dominio.viaje;

import java.util.List;

/**
 *
 * @author rjacome
 */
public interface ViajeRepository {
    
    public void crear(Viaje entidad) throws Exception;
    
    public List<Viaje> obtener() throws Exception;
    
    public Viaje obtenerPorId(int id) throws Exception;
    
    public void actualizar(
        int idViaje, String estado, int idConductor
    ) throws Exception;
    
    public boolean verificarExistencia(int id) throws Exception;
}
