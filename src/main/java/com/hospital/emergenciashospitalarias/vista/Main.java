package com.hospital.emergenciashospitalarias.vista;

import com.hospital.emergenciashospitalarias.controlador.PacienteController;
import com.hospital.emergenciashospitalarias.modelo.NivelPrioridad;
import com.hospital.emergenciashospitalarias.modelo.Paciente;

import java.time.LocalDateTime;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField txtNombre;
    private TextField txtEdad;
    private TextField txtDpi;
    private TextArea txtSintomas;

    private ComboBox<NivelPrioridad> comboPrioridad;

    private TableView<Paciente> tablaPacientes;

    private Label lblTotalPacientes;

    private PacienteController controller = new PacienteController();

    @Override
    public void start(Stage stage) {

        Label titulo = new Label("Sistema de Emergencias Hospitalarias");

        titulo.setStyle(
                "-fx-font-size: 24px;"
                + "-fx-font-weight: bold;"
        );

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtNombre.setMaxWidth(350);

        txtEdad = new TextField();
        txtEdad.setPromptText("Edad");
        txtEdad.setMaxWidth(350);

        txtDpi = new TextField();
        txtDpi.setPromptText("DPI u otro identificador");
        txtDpi.setMaxWidth(350);

        txtSintomas = new TextArea();
        txtSintomas.setPromptText("Síntomas o descripción del caso");
        txtSintomas.setPrefRowCount(3);
        txtSintomas.setMaxWidth(350);

        comboPrioridad = new ComboBox<>();
        comboPrioridad.setItems(
                FXCollections.observableArrayList(
                        NivelPrioridad.values()
                )
        );
        comboPrioridad.setPromptText("Nivel de prioridad");
        comboPrioridad.setMaxWidth(350);

        Button btnRegistrar = new Button("Registrar paciente");
        Button btnAtender = new Button("Atender siguiente");
        Button btnActualizar = new Button("Actualizar lista");
        Button btnHistorial = new Button("Ver historial atendidos");

        btnRegistrar.setMinWidth(200);
        btnAtender.setMinWidth(200);
        btnActualizar.setMinWidth(200);
        btnHistorial.setMinWidth(200);

        btnRegistrar.setOnAction(e -> registrarPaciente());
        btnAtender.setOnAction(e -> atenderPaciente());
        btnActualizar.setOnAction(e -> cargarPacientes());
        btnHistorial.setOnAction(e -> cargarPacientesAtendidos());

        tablaPacientes = new TableView<>();

        lblTotalPacientes = new Label("Pacientes en espera: 0");

        lblTotalPacientes.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;"
        );

        TableColumn<Paciente, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Paciente, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombreCompleto")
        );

        TableColumn<Paciente, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(
                new PropertyValueFactory<>("edad")
        );

        TableColumn<Paciente, String> colDpi = new TableColumn<>("DPI");
        colDpi.setCellValueFactory(
                new PropertyValueFactory<>("dpi")
        );

        TableColumn<Paciente, String> colSintomas = new TableColumn<>("Síntomas");
        colSintomas.setCellValueFactory(
                new PropertyValueFactory<>("sintomas")
        );

        TableColumn<Paciente, NivelPrioridad> colPrioridad = new TableColumn<>("Prioridad");
        colPrioridad.setCellValueFactory(
                new PropertyValueFactory<>("prioridad")
        );

        TableColumn<Paciente, LocalDateTime> colHora = new TableColumn<>("Hora ingreso");
        colHora.setCellValueFactory(
                new PropertyValueFactory<>("horaIngreso")
        );

        tablaPacientes.getColumns().addAll(
                colId,
                colNombre,
                colEdad,
                colDpi,
                colSintomas,
                colPrioridad,
                colHora
        );

        tablaPacientes.setPrefHeight(300);
        tablaPacientes.setMaxWidth(950);

        VBox root = new VBox(10);

        root.setAlignment(Pos.CENTER);

        root.setFillWidth(true);

        root.setStyle("-fx-padding: 20;");

        root.getChildren().addAll(
                titulo,
                new Label("Registro de paciente"),
                txtNombre,
                txtEdad,
                txtDpi,
                txtSintomas,
                comboPrioridad,
                btnRegistrar,
                btnAtender,
                btnActualizar,
                btnHistorial,
                new Label("Pacientes en espera / historial"),
                lblTotalPacientes,
                tablaPacientes
        );

        Scene scene = new Scene(root, 1000, 700);

        stage.setTitle("Emergencias Hospitalarias");
        stage.setScene(scene);
        stage.show();

        cargarPacientes();
    }

    private void registrarPaciente() {

        try {

            String nombre = txtNombre.getText();
            String edadTexto = txtEdad.getText();
            String dpi = txtDpi.getText();
            String sintomas = txtSintomas.getText();
            NivelPrioridad prioridad = comboPrioridad.getValue();

            if (
                    nombre.isEmpty()
                    || edadTexto.isEmpty()
                    || dpi.isEmpty()
                    || sintomas.isEmpty()
                    || prioridad == null
            ) {

                mostrarAlerta("Debe llenar todos los campos.");
                return;
            }

            int edad = Integer.parseInt(edadTexto);

            Paciente paciente = new Paciente(
                    nombre,
                    edad,
                    dpi,
                    sintomas,
                    prioridad,
                    LocalDateTime.now(),
                    "EN_ESPERA"
            );

            controller.guardarPaciente(paciente);

            limpiarCampos();

            cargarPacientes();

            mostrarAlerta("Paciente registrado correctamente.");

        } catch (NumberFormatException e) {

            mostrarAlerta("La edad debe ser un número.");
        }
    }

    private void cargarPacientes() {

        List<Paciente> pacientes = controller.obtenerPacientes();

        tablaPacientes.setItems(
                FXCollections.observableArrayList(pacientes)
        );

        lblTotalPacientes.setText(
                "Pacientes en espera: " + pacientes.size()
        );
    }

    private void cargarPacientesAtendidos() {

        List<Paciente> pacientes = controller.obtenerPacientesAtendidos();

        tablaPacientes.setItems(
                FXCollections.observableArrayList(pacientes)
        );

        lblTotalPacientes.setText(
                "Pacientes atendidos: " + pacientes.size()
        );
    }

    private void atenderPaciente() {

        List<Paciente> pacientes = controller.obtenerPacientes();

        if (pacientes.isEmpty()) {

            mostrarAlerta("No hay pacientes en espera.");
            return;
        }

        Paciente paciente = pacientes.get(0);

        controller.atenderPaciente(paciente.getId());

        mostrarAlerta(
                "Paciente atendido:\n\n"
                + paciente.getNombreCompleto()
                + "\nPrioridad: "
                + paciente.getPrioridad()
        );

        cargarPacientes();
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtEdad.clear();
        txtDpi.clear();
        txtSintomas.clear();

        comboPrioridad.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

    public static void main(String[] args) {

        launch(args);
    }
}