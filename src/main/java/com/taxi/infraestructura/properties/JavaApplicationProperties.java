package com.taxi.infraestructura.properties;

import com.taxi.dominio.properties.ApplicationProperties;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rjacome
 */
public class JavaApplicationProperties implements ApplicationProperties<Properties> {

    @Override
    public Properties getProperties(String source) throws Exception {
        Properties properties = new Properties();
        
        try {    
            InputStream inputStream = JavaApplicationProperties
                                        .class
                                        .getClassLoader()
                                        .getResourceAsStream(source)
            ;
            
            properties.load(inputStream);
        } catch(Exception exception) {
            Logger.getLogger(this.getClass().getName()).log(
                    Level.SEVERE, "Error reading file configuration"
            );
            
            throw exception;
        }
        
        return properties;
    }
}
