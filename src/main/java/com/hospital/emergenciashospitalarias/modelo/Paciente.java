package com.hospital.emergenciashospitalarias.modelo;

import java.time.LocalDateTime;

public class Paciente {

    private int id;
    private String nombreCompleto;
    private int edad;
    private String dpi;
    private String sintomas;
    private NivelPrioridad prioridad;
    private LocalDateTime horaIngreso;
    private String estado;

    public Paciente() {
    }

    public Paciente(String nombreCompleto, int edad, String dpi,
                    String sintomas, NivelPrioridad prioridad,
                    LocalDateTime horaIngreso, String estado) {

        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.dpi = dpi;
        this.sintomas = sintomas;
        this.prioridad = prioridad;
        this.horaIngreso = horaIngreso;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public String getDpi() {
        return dpi;
    }

    public String getSintomas() {
        return sintomas;
    }

    public NivelPrioridad getPrioridad() {
        return prioridad;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setPrioridad(NivelPrioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}