package Teleferico;


public class Persona {
    private String nombre;
    private int edad;
    private float peso;

    public Persona(String nombre, int edad, float peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public float getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años, " + peso + " kg)";
    }
}
