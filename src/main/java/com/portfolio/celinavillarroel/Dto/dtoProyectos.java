package com.portfolio.celinavillarroel.Dto;

import jakarta.validation.constraints.NotBlank;

public class dtoProyectos {
    @NotBlank
    private String nombreProy;
    @NotBlank
    private String descripcionProy;
    @NotBlank
    private String link;
    @NotBlank
    private String fecha;

    public dtoProyectos() {
    }

    public dtoProyectos(String nombreProy, String descripcionProy, String link, String fecha) {
        this.nombreProy = nombreProy;
        this.descripcionProy = descripcionProy;
        this.link = link;
        this.fecha = fecha;
    }

    public String getNombreProy() {
        return nombreProy;
    }

    public void setNombreProy(String nombreProy) {
        this.nombreProy = nombreProy;
    }

    public String getDescripcionProy() {
        return descripcionProy;
    }

    public void setDescripcionProy(String descripcionProy) {
        this.descripcionProy = descripcionProy;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
