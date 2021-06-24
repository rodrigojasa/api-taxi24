
package com.taxi.dominio.properties;

/**
 *
 * @author rjacome
 */
public interface ApplicationProperties<T> {
    /**
     * Obtener propiedades de archivo de configuracion
     * @param source
     * @return
     * @throws Exception 
     */
    public T getProperties(String source) throws Exception;
}
