import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GestorEstudiantes gestor = new GestorEstudiantes();

        int opcion;

        do {

            System.out.println("===== SISTEMA DE REGISTRO DE ESTUDIANTES =====");

            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Carnet: ");
                    String carnet = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();

                    Estudiante nuevo = new Estudiante(carnet, nombre, edad);

                    gestor.agregarEstudiante(nuevo);

                    System.out.println("Estudiante agregado.");
                    break;

                case 2:

                    gestor.listarEstudiantes();
                    break;

                case 3:

                    System.out.print("Carnet a buscar: ");

                    String buscar = sc.nextLine();

                    Estudiante encontrado =
                            gestor.buscarEstudiante(buscar);

                    if (encontrado != null) {

                        encontrado.mostrarInfo();

                    } else {

                        System.out.println("No encontrado");
                    }

                    break;

                case 4:

                    System.out.println("Saliendo...");
                    break;

                default:

                    System.out.println("Opción inválida");
            }

        } while (opcion != 4);
    }
}