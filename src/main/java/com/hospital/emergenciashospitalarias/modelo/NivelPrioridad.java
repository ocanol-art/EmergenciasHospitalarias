package com.hospital.emergenciashospitalarias.modelo;

public enum NivelPrioridad {

    CRITICAL(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4);

    private final int valor;

    NivelPrioridad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}