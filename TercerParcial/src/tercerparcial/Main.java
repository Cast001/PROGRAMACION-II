package tercerparcial;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        final String ARCHIVO_MEDICOS = "medicos.json";
        final String ARCHIVO_CONSULTAS = "consultas.json";
        Consultorio consultorio = new Consultorio(ARCHIVO_CONSULTAS, ARCHIVO_MEDICOS);
        List<Medico> medicos = Arrays.asList(
            new Medico(101, "Carlos", "Perez", 15),
            new Medico(102, "Maria", "Gomez", 8),
            new Medico(103, "Juan", "Lopez", 22)
        );       
        List<Consulta> consultas = Arrays.asList(
            new Consulta(1, "Ana", "Torres", 101, 3, "junio", 2025),
            new Consulta(2, "Luis", "Vega", 102, 15, "marzo", 2025),
            new Consulta(3, "Sofia", "Rios", 103, 5, "abril", 2025),
            new Consulta(4, "Diego", "Cruz", 101, 25, "diciembre", 2025),
            new Consulta(5, "Elena", "Mora", 102, 1, "enero", 2026),
            new Consulta(6, "Pablo", "Soto", 103, 20, "abril", 2025),
            new Consulta(7, "Nuria", "Gala", 101, 22, "julio", 2025),
            new Consulta(8, "Ivan", "Ruiz", 102, 22, "julio", 2025),
            new Consulta(9, "Clara", "Vela", 103, 10, "mayo", 2025)
        );
        consultorio.altaInicial(medicos, consultas);
        consultorio.bajaMedico("Juan", "Lopez");
        consultorio.cambiarDiaFestivo(15, "septiembre");
        consultorio.mostrarPacientesPorDia(3, "junio");
    }
}