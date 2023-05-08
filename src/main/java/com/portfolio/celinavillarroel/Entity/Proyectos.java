package com.portfolio.celinavillarroel.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreProy;
    private String descripcionProy;
    private String link;
    private String fecha;

    public Proyectos() {
    }

    public Proyectos(String nombreProy, String descripcionProy, String link, String fecha) {
        this.nombreProy = nombreProy;
        this.descripcionProy = descripcionProy;
        this.link = link;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
