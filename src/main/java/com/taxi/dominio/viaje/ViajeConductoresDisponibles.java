package com.taxi.dominio.viaje;

import com.taxi.dominio.conductor.Conductor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rjacome
 */
public class ViajeConductoresDisponibles {
    private int id; // id de viaje
    private List<Conductor> conductores;

    public ViajeConductoresDisponibles() {
        this.conductores = new ArrayList<>();
    }

    public ViajeConductoresDisponibles(int id) {
        this.id = id;
        this.conductores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }
}
