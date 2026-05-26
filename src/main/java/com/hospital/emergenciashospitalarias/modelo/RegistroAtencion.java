package com.hospital.emergenciashospitalarias.modelo;

import java.time.LocalDateTime;

public class RegistroAtencion {

    private int id;
    private int pacienteId;
    private LocalDateTime horaAtencion;
    private String observacion;

    public RegistroAtencion() {
    }

    public RegistroAtencion(int pacienteId, LocalDateTime horaAtencion, String observacion) {
        this.pacienteId = pacienteId;
        this.horaAtencion = horaAtencion;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}