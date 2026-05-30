public class Estudiante {

    private String carnet;
    private String nombre;
    private int edad;

    public Estudiante(String carnet, String nombre, int edad) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void mostrarInfo() {
        System.out.println("Carnet: " + carnet);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
    }
}