package com.hospital.emergenciashospitalarias.servicio;

import com.hospital.emergenciashospitalarias.modelo.Paciente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ColaPrioridadService {

    private PriorityQueue<Paciente> cola;

    public ColaPrioridadService() {

        cola = new PriorityQueue<>(new Comparator<Paciente>() {

            @Override
            public int compare(Paciente p1, Paciente p2) {

                // Primero comparar prioridad
                int prioridad =
                        Integer.compare(
                                p1.getPrioridad().getValor(),
                                p2.getPrioridad().getValor()
                        );

                // Si tienen misma prioridad,
                // comparar hora de ingreso
                if (prioridad == 0) {
                    return p1.getHoraIngreso()
                            .compareTo(p2.getHoraIngreso());
                }

                return prioridad;
            }
        });
    }

    // Agregar paciente
    public void agregarPaciente(Paciente paciente) {
        cola.add(paciente);
    }

    // Atender siguiente paciente
    public Paciente atenderPaciente() {
        return cola.poll();
    }

    // Obtener lista de pacientes
    public List<Paciente> obtenerPacientes() {
        return new ArrayList<>(cola);
    }

    // Verificar si está vacía
    public boolean estaVacia() {
        return cola.isEmpty();
    }
}