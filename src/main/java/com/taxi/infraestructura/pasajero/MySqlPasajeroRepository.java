package com.taxi.infraestructura.pasajero;

import com.taxi.dominio.pasajero.Pasajero;
import com.taxi.dominio.pasajero.PasajeroRepository;
import com.taxi.dominio.ubicacion.Ubicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rjacome
 */
public class MySqlPasajeroRepository implements PasajeroRepository {
    private final Connection connection;

    public MySqlPasajeroRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Pasajero> obtener() throws Exception {
        List<Pasajero> lista = new ArrayList<>();
        
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_PASAJEROS_R() }"
        );
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Pasajero pasajero = new Pasajero(
                resultSet.getInt("ID")
                , resultSet.getString("NOMBRE")
                , resultSet.getString("APELLIDOPATERNO")
                , resultSet.getString("APELLIDOMATERNO")
                , resultSet.getString("RFC")
                , resultSet.getString("FECHANACIMIENTO")
                , resultSet.getString("TELEFONO")
                , resultSet.getString("ENTIDADFEDERATIVA")
                , resultSet.getString("MUNICIPIO")
                , resultSet.getBoolean("ESTADO")
                , resultSet.getBoolean("DISPONIBILIDAD")
                , new Ubicacion(
                    resultSet.getDouble("LATITUD")
                    , resultSet.getDouble("LONGITUD")
                )
            );
            
            lista.add(pasajero);
        }
        
        resultSet.close();
        statement.close();
        
        return lista;
    }

    @Override
    public Pasajero obtenerPorId(int id) throws Exception {
        Pasajero pasajero = null;
        
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_PASAJEROS_R_POR_ID(?) }"
        );
        
        statement.setInt("PID", id);
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            pasajero = new Pasajero(
                resultSet.getInt("ID")
                , resultSet.getString("NOMBRE")
                , resultSet.getString("APELLIDOPATERNO")
                , resultSet.getString("APELLIDOMATERNO")
                , resultSet.getString("RFC")
                , resultSet.getString("FECHANACIMIENTO")
                , resultSet.getString("TELEFONO")
                , resultSet.getString("ENTIDADFEDERATIVA")
                , resultSet.getString("MUNICIPIO")
                , resultSet.getBoolean("ESTADO")
                , resultSet.getBoolean("DISPONIBILIDAD")
                , new Ubicacion(
                    resultSet.getDouble("LATITUD")
                    , resultSet.getDouble("LONGITUD")
                )
            );
            
            break;
        }
        
        resultSet.close();
        statement.close();
        
        return pasajero;
    }
}
