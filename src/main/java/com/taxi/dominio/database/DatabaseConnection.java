package com.taxi.dominio.database;

/**
 *
 * @author rjacome
 * @param <T>
 */
public interface DatabaseConnection<T> {
    
    /**
     * Obtener conexion de base de datos
     * @return
     * @throws Exception 
     */
    public T getConnection() throws Exception;
}
