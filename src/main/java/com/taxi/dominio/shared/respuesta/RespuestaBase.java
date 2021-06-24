package com.taxi.dominio.shared.respuesta;

/**
 *
 * @author rjacome
 */
public class RespuestaBase<T> {
    private boolean error;
    private String mensaje;
    private String mensajeDetalle;
    private T entidad;

    public RespuestaBase() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeDetalle() {
        return mensajeDetalle;
    }

    public void setMensajeDetalle(String mensajeDetalle) {
        this.mensajeDetalle = mensajeDetalle;
    }

    public T getEntidad() {
        return entidad;
    }

    public void setEntidad(T entidad) {
        this.entidad = entidad;
    }
}
