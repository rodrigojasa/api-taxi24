package com.taxi.infraestructura.viaje;

import com.taxi.dominio.viaje.Viaje;
import com.taxi.dominio.viaje.ViajeRepository;

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
public class MySqlViajeRepository implements ViajeRepository {
    private final Connection connection;

    public MySqlViajeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Viaje entidad) throws Exception {
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_VIAJES_C(?,?,?,?,?) }"
        );

        statement.setString("PESTADO", entidad.getEstado());
        statement.setInt("PIDPASAJERO", entidad.getIdPasajero());
        statement.setDouble("PLATITUD", entidad.getLatitud());
        statement.setDouble("PLONGITUD", entidad.getLongitud());

        statement.registerOutParameter("PID", Types.BIGINT);

        statement.executeUpdate();
        
        entidad.setId(statement.getInt("PID"));
        
        statement.close();
    }

    @Override
    public List<Viaje> obtener() throws Exception {
        List<Viaje> lista = new ArrayList<>();
        
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_VIAJES_R() }"
        );
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Viaje viaje = new Viaje(
                resultSet.getInt("ID")
                , resultSet.getString("ESTADO")
                , resultSet.getInt("IDPASAJERO")
                , resultSet.getInt("IDCONDUCTOR")
                , resultSet.getDouble("LATITUD")
                , resultSet.getDouble("LONGITUD")
            );
            
            lista.add(viaje);
        }
        
        resultSet.close();
        statement.close();
        
        return lista;
    }
    
    @Override
    public Viaje obtenerPorId(int id) throws Exception {
        Viaje viaje = null;
        
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_VIAJES_R_POR_ID(?) }"
        );
        
        statement.setInt("PID", id);
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            viaje = new Viaje(
                resultSet.getInt("ID")
                , resultSet.getString("ESTADO")
                , resultSet.getInt("IDPASAJERO")
                , resultSet.getInt("IDCONDUCTOR")
                , resultSet.getDouble("LATITUD")
                , resultSet.getDouble("LONGITUD")
            );
            
            break;
        }
        
        resultSet.close();
        statement.close();
        
        return viaje;
    }

    @Override
    public void actualizar(int idViaje, String estado, int idConductor) throws Exception {
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_VIAJES_U(?,?,?) }"
        );

        statement.setInt("PID", idViaje);
        
        if (estado == null || estado.trim().isEmpty()) {
            statement.setNull("PESTADO", Types.VARCHAR);
        } else {
            statement.setString("PESTADO", estado);
        }
        
        if (idConductor < 1) {
            statement.setNull("PIDCONDUCTOR", Types.VARCHAR);
        } else {
            statement.setInt("PIDCONDUCTOR", idConductor);
        }
        
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public boolean verificarExistencia(int id) throws Exception {
        CallableStatement statement = connection.prepareCall(
            "{ CALL USP_VIAJES_R_VERIFICAR_EXISTENCIA(?,?) }"
        );

        statement.registerOutParameter("PEXISTE", Types.BOOLEAN);
        statement.setLong("PID", id);

        statement.execute();
        
        boolean valido = statement.getBoolean("PEXISTE");
        statement.close();
        
        return valido;
    }
}
