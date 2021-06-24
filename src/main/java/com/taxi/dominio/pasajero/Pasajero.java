package com.taxi.dominio.pasajero;

import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;
import com.taxi.dominio.ubicacion.Ubicacion;

/**
 *
 * @author rjacome
 */
public class Pasajero {
    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;
    private String fechaNacimiento;
    private String telefono;
    private String entidadFederativa;
    private String municipio;
    private boolean estado;     // activo, inactivo
    private boolean disponibilidad;
    
//  informacion de ubicacion
    private Ubicacion ubicacion;

    public Pasajero() {
    }

    // obtener, obtener por id

    public Pasajero(
        int id, String nombre, String apellidoPaterno, String apellidoMaterno
        , String rfc, String fechaNacimiento, String telefono, String entidadFederativa
        , String municipio, boolean estado, boolean disponibilidad, Ubicacion ubicacion
    ) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.entidadFederativa = entidadFederativa;
        this.municipio = municipio;
        this.estado = estado;
        this.disponibilidad = disponibilidad;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre == null) {
            throw new OperationNotPermittedTaxiException("El nombre del pasajero es requerido");
        }
        
        nombre = nombre.trim();
        
        if (nombre.isEmpty()) {
            throw new OperationNotPermittedTaxiException("El nombre del pasajero es requerido");
        }
        
        if (nombre.length() > 40) {
            throw new OperationNotPermittedTaxiException("El nombre excede los 40 caracteres");
        }
        
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) throws Exception {
        if (apellidoPaterno == null) {
            throw new OperationNotPermittedTaxiException("El apellido paterno del pasajero es requerido");
        }
        
        apellidoPaterno = apellidoPaterno.trim();
        
        if (apellidoPaterno.isEmpty()) {
            throw new OperationNotPermittedTaxiException("El apellido paterno del pasajero es requerido");
        }
        
        if (apellidoPaterno.length() > 40) {
            throw new OperationNotPermittedTaxiException("El apellido paterno excede los 40 caracteres");
        }
        
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) throws Exception {
        if (apellidoMaterno == null) {
            throw new OperationNotPermittedTaxiException("El apellido materno del pasajero es requerido");
        }
        
        apellidoMaterno = apellidoMaterno.trim();
        
        if (apellidoMaterno.isEmpty()) {
            throw new OperationNotPermittedTaxiException("El apellido materno del pasajero es requerido");
        }
        
        if (apellidoMaterno.length() > 40) {
            throw new OperationNotPermittedTaxiException("El apellido materno excede los 40 caracteres");
        }
        
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) throws Exception {
        if (rfc == null) {
            throw new OperationNotPermittedTaxiException("El rfc del pasajero es requerido");
        }
        
        rfc = rfc.trim();
        
        if (rfc.isEmpty()) {
            throw new OperationNotPermittedTaxiException("El rfc del pasajero es requerido");
        }
        
        if (rfc.length() < 12) {
            throw new OperationNotPermittedTaxiException("El rfc debe tener como mínimo 12 caracteres");
        }
        
        if (rfc.length() > 13) {
            throw new OperationNotPermittedTaxiException("El rfc excede los 13 caracteres");
        }
        
        this.rfc = rfc;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) throws Exception {
        if (fechaNacimiento == null) {
            throw new OperationNotPermittedTaxiException("La fecha de nacimiento es requerida");
        }
        
        fechaNacimiento = fechaNacimiento.trim();
        
        if (fechaNacimiento.isEmpty()) {
            throw new OperationNotPermittedTaxiException("La fecha de nacimiento es requerida");
        }
        
        // TODO añadir regex
        
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws Exception {
        if (telefono == null) {
            throw new OperationNotPermittedTaxiException("El telefono es requerido");
        }
        
        telefono = telefono.trim();
        
        if (telefono.isEmpty()) {
            throw new OperationNotPermittedTaxiException("El telefono es requerido");
        }
        
        // TODO añadir regex
        
        this.telefono = telefono;
    }

    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    public void setEntidadFederativa(String entidadFederativa) throws Exception {
        if (entidadFederativa == null) {
            throw new OperationNotPermittedTaxiException("La entidad Federativa es requerida");
        }
        
        entidadFederativa = entidadFederativa.trim();
        
        if (entidadFederativa.isEmpty()) {
            throw new OperationNotPermittedTaxiException("La entidad Federativa es requerida");
        }
        
        // TODO agregar filtro
        
        this.entidadFederativa = entidadFederativa;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) throws Exception {
        if (municipio == null) {
            throw new OperationNotPermittedTaxiException("El municipio es requerido");
        }
        
        municipio = municipio.trim();
        
        if (municipio.isEmpty()) {
            throw new OperationNotPermittedTaxiException("El municipio es requerido");
        }
        
        // TODO agregar filtro
        
        this.municipio = municipio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
