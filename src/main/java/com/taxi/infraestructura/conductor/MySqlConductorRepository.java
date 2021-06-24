package com.taxi.infraestructura.conductor;

import com.taxi.dominio.conductor.Conductor;
import com.taxi.dominio.conductor.ConductorFiltroObtener;
import com.taxi.dominio.conductor.ConductorRepository;
import com.taxi.dominio.ubicacion.Ubicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rjacome
 */
public class MySqlConductorRepository implements ConductorRepository {
    private final Connection connection;

    public MySqlConductorRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Conductor> obtener(ConductorFiltroObtener filtro) throws Exception {
        List<Conductor> lista = new ArrayList<>();
        
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_CONDUCTORES_R(?) }"
        );
        
        if (filtro == null || filtro.getDisponibilidad() == null) {
            statement.setNull("PDISPONIBILIDAD", Types.BOOLEAN);
        } else {
            statement.setBoolean("PDISPONIBILIDAD", filtro.getDisponibilidad());
        }
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Conductor conductor = new Conductor(
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
            
            lista.add(conductor);
        }
        
        resultSet.close();
        statement.close();
        
        return lista;
    }

    @Override
    public Conductor obtenerPorId(int id) throws Exception {
        Conductor conductor = null;
        
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_CONDUCTORES_R_POR_ID(?) }"
        );
        
        statement.setInt("PID", id);
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            conductor = new Conductor(
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
        
        return conductor;
    }

    @Override
    public void actualizar(int id, boolean disponibilidad) throws Exception {
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_CONDUCTORES_U(?,?) }"
        );

        statement.setInt("PID", id);
        statement.setString("PDISPONIBILIDAD", disponibilidad ? "1" : "0");
        
        statement.executeUpdate();
        statement.close();
    }
}
