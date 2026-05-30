import java.util.ArrayList;

public class GestorEstudiantes {

    private ArrayList<Estudiante> lista = new ArrayList<>();

    public void agregarEstudiante(Estudiante e) {
        lista.add(e);
    }

    public void listarEstudiantes() {

        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        for (Estudiante e : lista) {
            e.mostrarInfo();
            System.out.println("-------------------");
        }
    }

    public Estudiante buscarEstudiante(String carnet) {

        for (Estudiante e : lista) {

            if (e.getCarnet().equalsIgnoreCase(carnet)) {
                return e;
            }
        }

        return null;
    }
}