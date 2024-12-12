
import java.util.ArrayList;

public class DesempenoNotas {
    private ArrayList<Double> notas;

    public DesempenoNotas() {
        notas = new ArrayList<>();
    }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    public String obtenerDesempeno() {
        double promedio = calcularPromedio();
        if (promedio >= 90) return "Excelente";
        if (promedio >= 75) return "Bueno";
        if (promedio >= 60) return "Regular";
        return "Deficiente";
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }
}
