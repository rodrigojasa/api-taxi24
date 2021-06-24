package com.taxi.aplicacion;

import com.taxi.dominio.database.DatabaseConnection;
import com.taxi.dominio.pasajero.Pasajero;
import com.taxi.dominio.pasajero.PasajeroService;
import com.taxi.dominio.shared.Id;
import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;
import com.taxi.dominio.shared.respuesta.RespuestaBase;
import com.taxi.infraestructura.database.MySqlConnection;
import com.taxi.infraestructura.pasajero.MySqlPasajeroRepository;
import com.taxi.infraestructura.properties.JavaApplicationProperties;

import java.sql.Connection;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author rjacome
 */
@Path("taxi/pasajero")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasajeroController {
    private PasajeroService pasajeroService;
    
    @GET
    @Path("obtener")
    public Response obtener() {
        RespuestaBase<List<Pasajero>> respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            pasajeroService = new PasajeroService(
                new MySqlPasajeroRepository(connection)
            );
            
            respuesta.setEntidad(
                pasajeroService.obtener()
            );
            
        } catch (OperationNotPermittedTaxiException ex) {
            estadoRespuesta = Response.Status.BAD_REQUEST;
            respuesta.setError(true);
            respuesta.setMensaje(ex.getMessage());
            
        } catch (Exception ex) {
            estadoRespuesta = Response.Status.INTERNAL_SERVER_ERROR;
            respuesta.setError(true);
            respuesta.setMensaje("Error en el procesamiento de la petición");
            respuesta.setMensajeDetalle(ex.getMessage());
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(estadoRespuesta).entity(respuesta).build();
    }
    
    @GET
    @Path("obtener/info")
    public Response obtenerPorId(Id filtro) {
        RespuestaBase<Pasajero> respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            pasajeroService = new PasajeroService(
                new MySqlPasajeroRepository(connection)
            );
            
            respuesta.setEntidad(
                pasajeroService.obtenerPorId(filtro)
            );
            
        } catch (OperationNotPermittedTaxiException ex) {
            estadoRespuesta = Response.Status.BAD_REQUEST;
            respuesta.setError(true);
            respuesta.setMensaje(ex.getMessage());
            
        } catch (Exception ex) {
            estadoRespuesta = Response.Status.INTERNAL_SERVER_ERROR;
            respuesta.setError(true);
            respuesta.setMensaje("Error en el procesamiento de la petición");
            respuesta.setMensajeDetalle(ex.getMessage());
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(estadoRespuesta).entity(respuesta).build();
    }
}
