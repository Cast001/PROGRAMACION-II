package Teleferico;

import java.util.ArrayList;

public class MiTeleferico {
    private ArrayList<Linea> lineas;

    public MiTeleferico() {
        lineas = new ArrayList<>();
    }

    public void agregarLinea(Linea l) {
        lineas.add(l);
    }

   
    public void verificarReglas() {
        for (Linea l : lineas) {
            for (Cabina c : l.getCabinas()) {
                float pesoTotal = 0;
                for (Persona p : c.getPersonas()) pesoTotal += p.getPeso();

                if (c.getPersonas().size() > 5 || pesoTotal > 850) {
                    System.out.println("Cabina " + c.getNumero() +
                            " de la línea " + l.getColor() + " NO cumple reglas.");
                }
            }
        }
    }

   
    public float calcularTotalIngresos() {
        float total = 0;

        for (Linea l : lineas) {
            for (Cabina c : l.getCabinas()) {
                for (Persona p : c.getPersonas()) {
                    if (p.getEdad() < 12) total += 1;
                    else if (p.getEdad() > 25 && p.getEdad() < 60) total += 1.5;
                    else total += 3;
                }
            }
        }

        return total;
    }

    
    public String lineaMayorIngresoRegular() {
        String mejor = "";
        float mayor = 0;

        for (Linea l : lineas) {
            float ingreso = 0;

            for (Cabina c : l.getCabinas()) {
                for (Persona p : c.getPersonas()) {
                    if (!(p.getEdad() < 12 || (p.getEdad() > 25 && p.getEdad() < 60))) {
                        ingreso += 3;
                    }
                }
            }

            if (ingreso > mayor) {
                mayor = ingreso;
                mejor = l.getColor();
            }
        }

        return mejor;
    }
}
