package com.taxi.dominio.viaje;

import com.taxi.dominio.shared.exception.OperationNotPermittedTaxiException;

/**
 *
 * @author rjacome
 */
public class Viaje {
    private int id;
    private String estado;
    private int idPasajero;
    private int idConductor;
    private double latitud;
    private double longitud;

    public Viaje() {
    }

    public Viaje(
        int id, String estado, int idPasajero, int idConductor, double latitud, double longitud
    ) {
        this.id = id;
        this.estado = estado;
        this.idPasajero = idPasajero;
        this.idConductor = idConductor;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) throws Exception {
        if (latitud < -90 || latitud > 90) {
            throw new OperationNotPermittedTaxiException(
                "La latitud se encuentra fuera del rango(-90, 90)"
            );
        }
        
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) throws Exception {
        if (longitud < -180 || longitud > 180) {
            throw new OperationNotPermittedTaxiException(
                "La longitud se encuentra fuera del rango(-180, 180)"
            );
        }
        
        this.longitud = longitud;
    }
}
