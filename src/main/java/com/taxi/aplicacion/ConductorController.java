package com.taxi.aplicacion;

import com.taxi.dominio.conductor.Conductor;
import com.taxi.dominio.conductor.ConductorFiltroObtener;
import com.taxi.dominio.conductor.ConductorService;
import com.taxi.dominio.database.DatabaseConnection;
import com.taxi.dominio.shared.Id;
import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;
import com.taxi.dominio.shared.respuesta.RespuestaBase;
import com.taxi.dominio.ubicacion.Ubicacion;

import com.taxi.infraestructura.conductor.MySqlConductorRepository;
import com.taxi.infraestructura.database.MySqlConnection;
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
@Path("taxi/conductor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConductorController {
    private ConductorService conductorService;
    
    @GET
    @Path("obtener")
    public Response obtener(ConductorFiltroObtener filtro) {
        RespuestaBase<List<Conductor>> respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            conductorService = new ConductorService(
                new MySqlConductorRepository(connection)
            );
            
            respuesta.setEntidad(
                conductorService.obtener(filtro)
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
        RespuestaBase<Conductor> respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            conductorService = new ConductorService(
                new MySqlConductorRepository(connection)
            );
            
            respuesta.setEntidad(
                conductorService.obtenerPorId(filtro)
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
    @Path("obtener/ubicacion")
    public Response obtenerDisponiblesPorUbicacion(Ubicacion filtro) {
        RespuestaBase<List<Conductor>> respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            conductorService = new ConductorService(
                new MySqlConductorRepository(connection)
            );
            
            respuesta.setEntidad(
                conductorService.obtenerDisponiblesPorUbicacion(filtro)
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
