package com.taxi.infraestructura.database;

import com.taxi.dominio.database.DatabaseConfiguration;
import com.taxi.dominio.database.DatabaseConnection;
import com.taxi.dominio.database.DatabaseProperties;
import com.taxi.dominio.properties.ApplicationProperties;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rjacome
 */
public class MySqlConnection implements DatabaseConnection<Connection> {
    private ApplicationProperties applicationProperties;

    public MySqlConnection(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
    
    @Override
    public Connection getConnection() throws Exception {
        Connection connection = null;
        
        Properties databaseProperties = (Properties) applicationProperties.getProperties(
            "database.properties"
        );
        
        DatabaseConfiguration databaseConfiguration = this.mapPropertiesToObject(databaseProperties);
        
        String stringConnection = this.getStrigConnection(databaseConfiguration);
        
        try {
            connection = DriverManager.getConnection(
                stringConnection
                , databaseConfiguration.getUser()
                , databaseConfiguration.getPassword()
            );
        } catch(Exception exception) {
            Logger.getLogger(this.getClass().getName()).log(
                Level.SEVERE
                , "Database connection error"
            );
            
            throw exception;
        }
        
        return connection;
    }
    
    private String getStrigConnection(DatabaseConfiguration database) {
        String stringConnection = 
            "jdbc:mysql://" 
                + database.getHost() + ":" 
                + database.getPort() + "/"
                + database.getDatabaseName()
                + "?zeroDateTimeBehavior=convertToNull"
        ;
        
        
        return stringConnection;
    }
    
    private DatabaseConfiguration mapPropertiesToObject(Properties properties) {
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(
            properties.getProperty(DatabaseProperties.host)
            , properties.getProperty(DatabaseProperties.port)
            , properties.getProperty(DatabaseProperties.database)
            , properties.getProperty(DatabaseProperties.user)
            , properties.getProperty(DatabaseProperties.password)
        );
        
        return databaseConfiguration;
    }
}
