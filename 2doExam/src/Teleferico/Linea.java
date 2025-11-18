package Teleferico;

import java.util.ArrayList;

public class Linea {
    private String color;
    private ArrayList<Persona> filaPersonas;
    private ArrayList<Cabina> cabinas;

    public Linea(String color) {
        this.color = color;
        filaPersonas = new ArrayList<>();
        cabinas = new ArrayList<>();
    }

    public void agregarPersonaFila(Persona p) {
        filaPersonas.add(p);
    }

    public void agregarCabina(int numero) {
        cabinas.add(new Cabina(numero));
    }

    
    public boolean enviarPersonaACabina(int nroCabina) {
        if (filaPersonas.isEmpty()) {
            System.out.println("La fila está vacía.");
            return false;
        }

        Persona primera = filaPersonas.get(0);

        for (Cabina c : cabinas) {
            if (c.getNumero() == nroCabina) {
                boolean ok = c.agregarPersona(primera);
                if (ok) filaPersonas.remove(0);
                return ok;
            }
        }

        System.out.println("No existe la cabina " + nroCabina);
        return false;
    }

    public ArrayList<Cabina> getCabinas() {
        return cabinas;
    }

    public String getColor() {
        return color;
    }
}
