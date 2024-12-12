
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestion {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Profesor> profesores;
    private ArrayList<Curso> cursos;

    public SistemaGestion() {
        estudiantes = new ArrayList<>();
        profesores = new ArrayList<>();
        cursos = new ArrayList<>();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1 -> gestionarEstudiantes(scanner);
                case 2 -> gestionarProfesores(scanner);
                case 3 -> gestionarCursos(scanner);
                case 4 -> generarReportes(scanner);
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestionar Estudiantes");
        System.out.println("2. Gestionar Profesores");
        System.out.println("3. Gestionar Cursos");
        System.out.println("4. Generar Reportes");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void gestionarEstudiantes(Scanner scanner) {
        System.out.println("\n--- Gestionar Estudiantes ---");
        System.out.println("1. Agregar Estudiante");
        System.out.println("2. Eliminar Estudiante");
        System.out.println("3. Listar Estudiantes");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        switch (opcion) {
            case 1 -> agregarEstudiante(scanner);
            case 2 -> eliminarEstudiante(scanner);
            case 3 -> listarEstudiantes();
            default -> System.out.println("Opción no válida.");
        }
    }

    private void agregarEstudiante(Scanner scanner) {
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Email: ");
        String email = scanner.nextLine();
        estudiantes.add(new Estudiante(id, nombre, email));
        System.out.println("Estudiante agregado.");
    }

    private void eliminarEstudiante(Scanner scanner) {
        System.out.print("Ingrese ID del estudiante a eliminar: ");
        String id = scanner.nextLine();
        estudiantes.removeIf(est -> est.getId().equals(id));
        System.out.println("Estudiante eliminado.");
    }

    private void listarEstudiantes() {
        System.out.println("\n--- Lista de Estudiantes ---");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }

    private void gestionarProfesores(Scanner scanner) {
        // Similar a gestionarEstudiantes pero para Profesores
    }

    private void gestionarCursos(Scanner scanner) {
        // Similar a gestionarEstudiantes pero para Cursos
    }

    private void generarReportes(Scanner scanner) {
        // Implementar lógica para reportes
    }
}
