package tercerparcial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Consultorio {
    private final String consultas; 
    private final String medicos;   
    private final Gson gson; 
    public Consultorio(String archivoConsultas, String archivoMedicos) {
        this.consultas = archivoConsultas;
        this.medicos = archivoMedicos;
        this.gson = new GsonBuilder().setPrettyPrinting().create(); 
    }
    private List<Medico> leerMedicos() {
        File file = new File(this.medicos);
        if (!file.exists() || file.length() == 0) { 
            return new ArrayList<>();
        }      
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Medico>>(){}.getType(); 
            List<Medico> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException | com.google.gson.JsonSyntaxException e) {
            System.err.println("Error al leer archivo de Médicos: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
    private void escribirMedicos(List<Medico> lista) {
        try (FileWriter writer = new FileWriter(this.medicos)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Error al escribir archivo de Médicos: " + e.getMessage());
        }
    }
    private List<Consulta> leerConsultas() {
        File file = new File(this.consultas);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }        
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Consulta>>(){}.getType();
            List<Consulta> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException | com.google.gson.JsonSyntaxException e) {
            System.err.println("Error al leer archivo de Consultas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    private void escribirConsultas(List<Consulta> lista) {
        try (FileWriter writer = new FileWriter(this.consultas)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Error al escribir archivo de Consultas: " + e.getMessage());
        }
    }
    public void altaInicial(List<Medico> nuevosMedicos, List<Consulta> nuevasConsultas) {
        System.out.println("--- Inciso a: Alta Inicial ---");
        List<Medico> medicosActuales = leerMedicos();
        medicosActuales.addAll(nuevosMedicos);
        escribirMedicos(medicosActuales);
        System.out.println("- Se registraron " + nuevosMedicos.size() + " Médicos.");
        List<Consulta> consultasActuales = leerConsultas();
        consultasActuales.addAll(nuevasConsultas);
        escribirConsultas(consultasActuales);
        System.out.println("- Se registraron " + nuevasConsultas.size() + " Consultas.");
    }
    public void bajaMedico(String nombre, String apellido) {
        System.out.println("\n--- Inciso b: Baja de Médico y Consultas ---");
        List<Medico> medicos = leerMedicos();
        List<Consulta> consultas = leerConsultas();
        int idMedicoABorrar = medicos.stream()
                .filter(m -> m.getNombreMed().equalsIgnoreCase(nombre) && m.getApellidoMed().equalsIgnoreCase(apellido))
                .map(Medico::getIdMed)
                .findFirst()
                .orElse(-1);
        if (idMedicoABorrar == -1) {
            System.out.println("x ERROR: Médico '" + nombre + " " + apellido + "' no encontrado.");
            return;
        }
        List<Medico> medicosRestantes = medicos.stream()
                .filter(m -> m.getIdMed() != idMedicoABorrar)
                .collect(Collectors.toList());
        escribirMedicos(medicosRestantes);
        System.out.println("- Médico con ID " + idMedicoABorrar + " (" + nombre + " " + apellido + ") dado de baja.");

        int consultasBorradas = (int) consultas.stream()
            .filter(c -> c.getIdMed() == idMedicoABorrar)
            .count();
            
        List<Consulta> consultasRestantes = consultas.stream()
                .filter(c -> c.getIdMed() != idMedicoABorrar)
                .collect(Collectors.toList());
        escribirConsultas(consultasRestantes);
        System.out.println("- Se eliminaron " + consultasBorradas + " consultas asociadas al médico.");
    }

    public void cambiarDiaFestivo(int nuevoDia, String nuevoMes) {
        System.out.println("\n--- Inciso c: Cambio de Día de Consultas Festivas ---");
        List<Consulta> consultas = leerConsultas();
        List<Consulta> consultasActualizadas = new ArrayList<>();
        int contadorCambios = 0;

        for (Consulta c : consultas) {
            boolean esNavidad = c.getDia() == 25 && c.getMes().equalsIgnoreCase("diciembre");
            boolean esAnioNuevo = c.getDia() == 1 && c.getMes().equalsIgnoreCase("enero");

            if (esNavidad || esAnioNuevo) {
                Consulta consultaModificada = new Consulta(
                    c.getCi(), c.getNombrePaciente(), c.getApellidoPaciente(), c.getIdMed(), 
                    nuevoDia, nuevoMes, c.getAnio()
                );
                consultasActualizadas.add(consultaModificada);
                System.out.println("   - Consulta CI " + c.getCi() + " (" + c.getDia() + "/" + c.getMes() + ") cambiada a " + nuevoDia + "/" + nuevoMes);
                contadorCambios++;
            } else {
                consultasActualizadas.add(c);
            }
        }

        if (contadorCambios > 0) {
            escribirConsultas(consultasActualizadas);
            System.out.println("- Se cambiaron el día de " + contadorCambios + " consultas.");
        } else {
            System.out.println("- No se encontraron consultas para Navidad o Año Nuevo para cambiar.");
        }
    }

    public void mostrarPacientesPorDia(int dia, String mes) {
        System.out.println("\n--- Inciso d (Opcional): Pacientes atendidos el " + dia + "/" + mes + " ---");
        List<Consulta> consultas = leerConsultas();

        List<String> pacientes = consultas.stream()
                .filter(c -> c.getDia() == dia && c.getMes().equalsIgnoreCase(mes))
                .map(c -> c.getNombrePaciente() + " " + c.getApellidoPaciente() + " (CI: " + c.getCi() + ")")
                .collect(Collectors.toList());

        if (pacientes.isEmpty()) {
            System.out.println("- No se encontraron pacientes atendidos ese día.");
        } else {
            System.out.println("Pacientes atendidos:");
            pacientes.forEach(p -> System.out.println("   - " + p));
        }
    }
}