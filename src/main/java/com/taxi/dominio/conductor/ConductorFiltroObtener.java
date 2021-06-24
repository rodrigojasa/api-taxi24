package com.taxi.dominio.conductor;

/**
 *
 * @author rjacome
 */
public class ConductorFiltroObtener {
    private Boolean disponibilidad;

    public ConductorFiltroObtener() {
    }

    public ConductorFiltroObtener(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
