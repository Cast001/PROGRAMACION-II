package ejercicio1;

import java.util.Scanner;

public class Ejercicio1 {  

    private static class EcuacionLineal {
        private double a, b, c, d, e, f;

        public EcuacionLineal(double a, double b, double c, double d, double e, double f) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
        }

        public boolean tieneSolucion() {
            return (a * d - b * c) != 0;
        }

        public double getX() {
            return (e * d - b * f) / (a * d - b * c);
        }

        public double getY() {
            return (a * f - e * c) / (a * d - b * c);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce los valores para la ecuacion lineal (a, b, c, d, e, f):");
        System.out.print("a: ");
        double a = scanner.nextDouble();
        System.out.print("b: ");
        double b = scanner.nextDouble();
        System.out.print("c: ");
        double c = scanner.nextDouble();
        System.out.print("d: ");
        double d = scanner.nextDouble();
        System.out.print("e: ");
        double e = scanner.nextDouble();
        System.out.print("f: ");
        double f = scanner.nextDouble();

        EcuacionLineal ecuacion = new EcuacionLineal(a, b, c, d, e, f);

        if (ecuacion.tieneSolucion()) {
            System.out.println("x = " + ecuacion.getX());
            System.out.println("y = " + ecuacion.getY());
        } else {
            System.out.println("La ecuación no tiene solución.");
        }

        scanner.close();
    }
}
