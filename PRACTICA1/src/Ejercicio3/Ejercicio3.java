package Ejercicio3;

import java.util.Scanner;
import java.util.Locale;

public class Ejercicio3 {

    private static class Estadistica {
        private double[] datos;

        public Estadistica(double[] datos) {
            this.datos = datos;
        }

        public double promedio() {
            double suma = 0;
            for (double num : datos) {
                suma += num;
            }
            return suma / datos.length;
        }

        public double desviacion() {
            double promedio = promedio();
            double sumaDiferenciaCuadrada = 0;
            for (double num : datos) {
                sumaDiferenciaCuadrada += Math.pow(num - promedio, 2);
            }
            return Math.sqrt(sumaDiferenciaCuadrada / (datos.length - 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);  // Para decimales con punto

        double[] numeros = new double[10];
        System.out.println("Introduce 10 números:");

        for (int i = 0; i < 10; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numeros[i] = scanner.nextDouble();
        }

        Estadistica estadistica = new Estadistica(numeros);

        System.out.printf("El promedio es %.2f%n", estadistica.promedio());
        System.out.printf("La desviación estándar es %.4f%n", estadistica.desviacion());

        scanner.close();
    }
}
