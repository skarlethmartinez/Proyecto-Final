
import java.util.ArrayList;

public class Curso {
    private String id;
    private String nombre;
    private Profesor profesor;
    private ArrayList<Estudiante> estudiantes;

    public Curso(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
    }

    public void asignarProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void eliminarEstudiante(String idEstudiante) {
        estudiantes.removeIf(est -> est.getId().equals(idEstudiante));
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    @Override
    public String toString() {
        return "Curso: " + nombre + " (ID: " + id + ")";
    }
}
