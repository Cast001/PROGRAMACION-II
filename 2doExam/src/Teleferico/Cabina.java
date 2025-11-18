package Teleferico;

import java.util.ArrayList;

public class Cabina {
    private int nroCabina;
    private ArrayList<Persona> personas;

    public Cabina(int nroCabina) {
        this.nroCabina = nroCabina;
        this.personas = new ArrayList<>();
    }

    public boolean agregarPersona(Persona p) {
        if (personas.size() >= 5) {
            System.out.println("Cabina " + nroCabina + " llena (máx 5).");
            return false;
        }

        float pesoTotal = 0;
        for (Persona per : personas) {
            pesoTotal += per.getPeso();
        }

        if (pesoTotal + p.getPeso() > 850) {
            System.out.println("Cabina " + nroCabina + " supera el peso máximo.");
            return false;
        }

        personas.add(p);
        return true;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public int getNumero() {
        return nroCabina;
    }
}
