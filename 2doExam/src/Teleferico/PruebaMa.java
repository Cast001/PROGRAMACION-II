package Teleferico;

public class PruebaMa {
    public static void main(String[] args) {

        MiTeleferico mt = new MiTeleferico();

        Linea roja = new Linea("Roja");
        roja.agregarCabina(1);
        roja.agregarCabina(2);

      
        roja.agregarPersonaFila(new Persona("Ana", 10, 30));
        roja.agregarPersonaFila(new Persona("Luis", 30, 70));
        roja.agregarPersonaFila(new Persona("Marta", 50, 60));

      
        roja.enviarPersonaACabina(1);
        roja.enviarPersonaACabina(1);
        roja.enviarPersonaACabina(2);

     
        roja.agregarPersonaFila(new Persona("Carlos", 20, 70));
        roja.enviarPersonaACabina(1);

       
        mt.agregarLinea(roja);

       
        mt.verificarReglas();
        System.out.println("Ingreso total: " + mt.calcularTotalIngresos() + " Bs");
        System.out.println("Linea con mayor ingreso regular: " + mt.lineaMayorIngresoRegular());
    }
}
