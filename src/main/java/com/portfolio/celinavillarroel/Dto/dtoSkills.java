package com.portfolio.celinavillarroel.Dto;

import jakarta.validation.constraints.NotBlank;


public class dtoSkills {
    @NotBlank
    private String nombreSkill;
    @NotBlank
    private int porcentaje;

    public dtoSkills() {
    }

    public dtoSkills(String nombreSkill, int porcentaje) {
        this.nombreSkill = nombreSkill;
        this.porcentaje = porcentaje;
    }

    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    
}
