package com.hospital.emergenciashospitalarias.dao;

import com.hospital.emergenciashospitalarias.modelo.NivelPrioridad;
import com.hospital.emergenciashospitalarias.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void insertarPaciente(Paciente paciente) {

        String sql = "INSERT INTO pacientes "
                + "(nombre_completo, edad, dpi, sintomas, prioridad, hora_ingreso, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, paciente.getNombreCompleto());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getDpi());
            ps.setString(4, paciente.getSintomas());
            ps.setString(5, paciente.getPrioridad().name());

            ps.setTimestamp(
                    6,
                    Timestamp.valueOf(
                            paciente.getHoraIngreso()
                    )
            );

            ps.setString(7, paciente.getEstado());

            ps.executeUpdate();

            System.out.println("Paciente guardado correctamente");

        } catch (Exception e) {

            System.out.println("Error al guardar paciente");
            System.out.println(e.getMessage());
        }
    }

    public List<Paciente> obtenerPacientesEnEspera() {

        List<Paciente> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM pacientes "
                + "WHERE estado = 'EN_ESPERA' "
                + "ORDER BY "
                + "FIELD(prioridad, "
                + "'CRITICAL', "
                + "'HIGH', "
                + "'MEDIUM', "
                + "'LOW'), "
                + "hora_ingreso ASC";

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNombreCompleto(rs.getString("nombre_completo"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setDpi(rs.getString("dpi"));
                paciente.setSintomas(rs.getString("sintomas"));

                paciente.setPrioridad(
                        NivelPrioridad.valueOf(
                                rs.getString("prioridad")
                        )
                );

                paciente.setHoraIngreso(
                        rs.getTimestamp("hora_ingreso")
                                .toLocalDateTime()
                );

                paciente.setEstado(
                        rs.getString("estado")
                );

                lista.add(paciente);
            }

        } catch (Exception e) {

            System.out.println("Error al obtener pacientes");
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public void marcarComoAtendido(int idPaciente) {

        String sql =
                "UPDATE pacientes "
                + "SET estado = 'ATENDIDO' "
                + "WHERE id = ?";

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, idPaciente);

            ps.executeUpdate();

            System.out.println("Paciente actualizado");

        } catch (Exception e) {

            System.out.println("Error al actualizar paciente");
            System.out.println(e.getMessage());
        }
    }

    public void registrarAtencion(int pacienteId) {

        String sql =
                "INSERT INTO atenciones "
                + "(paciente_id, hora_atencion, observacion) "
                + "VALUES (?, NOW(), ?)";

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, pacienteId);

            ps.setString(
                    2,
                    "Paciente atendido correctamente"
            );

            ps.executeUpdate();

            System.out.println("Atención registrada");

        } catch (Exception e) {

            System.out.println("Error al registrar atención");
            System.out.println(e.getMessage());
        }
    }
    
    public List<Paciente> obtenerPacientesAtendidos() {

    List<Paciente> lista = new ArrayList<>();

    String sql =
            "SELECT * FROM pacientes "
            + "WHERE estado = 'ATENDIDO' "
            + "ORDER BY hora_ingreso DESC";

    try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Paciente paciente = new Paciente();

            paciente.setId(rs.getInt("id"));
            paciente.setNombreCompleto(rs.getString("nombre_completo"));
            paciente.setEdad(rs.getInt("edad"));
            paciente.setDpi(rs.getString("dpi"));
            paciente.setSintomas(rs.getString("sintomas"));

            paciente.setPrioridad(
                    NivelPrioridad.valueOf(
                            rs.getString("prioridad")
                    )
            );

            paciente.setHoraIngreso(
                    rs.getTimestamp("hora_ingreso")
                            .toLocalDateTime()
            );

            paciente.setEstado(rs.getString("estado"));

            lista.add(paciente);
        }

    } catch (Exception e) {
        System.out.println("Error al obtener pacientes atendidos");
        System.out.println(e.getMessage());
    }

    return lista;
}
}