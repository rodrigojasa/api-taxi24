package com.taxi.dominio.conductor;

import java.util.List;

/**
 *
 * @author rjacome
 */
public interface ConductorRepository {
    
    public List<Conductor> obtener(ConductorFiltroObtener filtro) throws Exception;
    
    public Conductor obtenerPorId(int id) throws Exception;
    
    public void actualizar(int id, boolean disponibilidad) throws Exception;
}
