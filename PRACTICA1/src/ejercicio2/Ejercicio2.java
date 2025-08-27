package ejercicio2;

import java.util.Scanner;
import java.util.Locale;

public class Ejercicio2 {

    private static class EcuacionCuadratica {
        private double a, b, c;

        public EcuacionCuadratica(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public double getDiscriminante() {
            return b * b - 4 * a * c;
        }

        public double getRaiz1() {
            double discriminante = getDiscriminante();
            if (discriminante >= 0) {
                return (-b + Math.sqrt(discriminante)) / (2 * a);
            }
            return Double.NaN;
        }

        public double getRaiz2() {
            double discriminante = getDiscriminante();
            if (discriminante >= 0) {
                return (-b - Math.sqrt(discriminante)) / (2 * a);
            }
            return Double.NaN;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Introduce los valores para la ecuación cuadrática (a, b, c):");

        System.out.print("a: ");
        double a = scanner.nextDouble();

        System.out.print("b: ");
        double b = scanner.nextDouble();

        System.out.print("c: ");
        double c = scanner.nextDouble();

        if (a == 0) {
            System.out.println("No es una ecuación cuadrática (a no puede ser 0).");
            scanner.close();
            return;
        }

        EcuacionCuadratica ecuacion = new EcuacionCuadratica(a, b, c);
        double discriminante = ecuacion.getDiscriminante();

        if (discriminante > 0) {
            System.out.println("La ecuacion tiene dos raices " 
                + String.format("%.6f", ecuacion.getRaiz1()) + " " 
                + String.format("%.6f", ecuacion.getRaiz2()));
        } else if (discriminante == 0) {
            System.out.println("La ecuacion tiene una raiz " + String.format("%.6f", ecuacion.getRaiz1()));
        } else {
            System.out.println("La ecuacion no tiene raices reales.");
        }

        scanner.close();
    }
}
