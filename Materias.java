
import java.util.ArrayList;

public class Materias {
    private ArrayList<String> materias;

    public Materias() {
        materias = new ArrayList<>();
    }

    public void agregarMateria(String materia) {
        materias.add(materia);
    }

    public ArrayList<String> listarMaterias() {
        return materias;
    }
}
