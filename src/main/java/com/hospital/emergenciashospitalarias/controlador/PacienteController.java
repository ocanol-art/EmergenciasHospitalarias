package com.hospital.emergenciashospitalarias.controlador;

import com.hospital.emergenciashospitalarias.dao.PacienteDAO;
import com.hospital.emergenciashospitalarias.modelo.Paciente;

import java.util.List;

public class PacienteController {

    private PacienteDAO pacienteDAO;

    public PacienteController() {
        pacienteDAO = new PacienteDAO();
    }

    public void guardarPaciente(Paciente paciente) {
        pacienteDAO.insertarPaciente(paciente);
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteDAO.obtenerPacientesEnEspera();
    }

    public List<Paciente> obtenerPacientesAtendidos() {
        return pacienteDAO.obtenerPacientesAtendidos();
    }

    public void atenderPaciente(int idPaciente) {
        pacienteDAO.marcarComoAtendido(idPaciente);
        pacienteDAO.registrarAtencion(idPaciente);
    }
}