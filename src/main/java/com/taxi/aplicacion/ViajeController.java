package com.taxi.aplicacion;

import com.taxi.dominio.conductor.ConductorService;
import com.taxi.dominio.database.DatabaseConnection;
import com.taxi.dominio.shared.Id;
import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;
import com.taxi.dominio.shared.respuesta.RespuestaBase;
import com.taxi.dominio.viaje.Viaje;
import com.taxi.dominio.viaje.ViajeConductoresDisponibles;
import com.taxi.dominio.viaje.ViajeService;

import com.taxi.infraestructura.conductor.MySqlConductorRepository;
import com.taxi.infraestructura.database.MySqlConnection;
import com.taxi.infraestructura.pasajero.MySqlPasajeroRepository;
import com.taxi.infraestructura.properties.JavaApplicationProperties;
import com.taxi.infraestructura.viaje.MySqlViajeRepository;

import java.sql.Connection;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author rjacome
 */
@Path("taxi/viaje")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeController {
    private ViajeService viajeService;
    
    @POST
    @Path("crear")
    public Response crear(Viaje entidad) {
        RespuestaBase<ViajeConductoresDisponibles> respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            viajeService = new ViajeService(
                new MySqlViajeRepository(connection)
                , new MySqlConductorRepository(connection)
                , new MySqlPasajeroRepository(connection)
                , new ConductorService(
                    new MySqlConductorRepository(connection)
                )
            );
            
            respuesta.setEntidad(
                viajeService.crear(entidad)
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
    @Path("obtener")
    public Response obtener() {
        RespuestaBase<List<Viaje>> respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            viajeService = new ViajeService(
                new MySqlViajeRepository(connection)
            );
            
            respuesta.setEntidad(
                viajeService.obtener()
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
    
    @POST
    @Path("terminar")
    public Response terminarViaje(Id id) {
        RespuestaBase respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            viajeService = new ViajeService(
                new MySqlViajeRepository(connection)
                , new MySqlConductorRepository(connection)
            );
            
            viajeService.terminarViaje(id);
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
    
    @POST
    @Path("asociar/conductor")
    public Response asociarConductor(Viaje entidad) {
        RespuestaBase respuesta = new RespuestaBase<>();
        Response.Status estadoRespuesta = Response.Status.OK;
        
        try {            
            DatabaseConnection database = new MySqlConnection(
                new JavaApplicationProperties()
            );
        
            Connection connection = (Connection) database.getConnection();
            
            viajeService = new ViajeService(
                new MySqlViajeRepository(connection)
                , new MySqlConductorRepository(connection)
            );
            
            viajeService.asociarConductor(entidad);
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
