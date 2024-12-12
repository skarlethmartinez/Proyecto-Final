
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SistemaGestionGUI {
    private JFrame frame;
    private ArrayList<String> estudiantes;
    private ArrayList<String> profesores;
    private ArrayList<String> cursos;
    private Materias materias;
    private DesempenoNotas desempenoNotas;

    public SistemaGestionGUI() {
        estudiantes = new ArrayList<>();
        profesores = new ArrayList<>();
        cursos = new ArrayList<>();
        materias = new Materias();
        desempenoNotas = new DesempenoNotas();

        frame = new JFrame("Sistema de Gestión de Recursos Educativos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Crear el menú principal
        JPanel menuPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        JButton btnEstudiantes = new JButton("Gestionar Estudiantes");
        JButton btnProfesores = new JButton("Gestionar Profesores");
        JButton btnCursos = new JButton("Gestionar Cursos");
        JButton btnMaterias = new JButton("Gestionar Materias");
        JButton btnDesempeno = new JButton("Gestionar Desempeño de Notas");
        JButton btnReportes = new JButton("Generar Reportes");
        JButton btnSalir = new JButton("Salir");

        menuPanel.add(btnEstudiantes);
        menuPanel.add(btnProfesores);
        menuPanel.add(btnCursos);
        menuPanel.add(btnMaterias);
        menuPanel.add(btnDesempeno);
        menuPanel.add(btnReportes);
        menuPanel.add(btnSalir);

        // Acciones de botones
        btnSalir.addActionListener(e -> System.exit(0));
        btnEstudiantes.addActionListener(e -> mostrarGestionEstudiantes());
        btnProfesores.addActionListener(e -> mostrarGestionProfesores());
        btnCursos.addActionListener(e -> mostrarGestionCursos());
        btnMaterias.addActionListener(e -> mostrarGestionMaterias());
        btnDesempeno.addActionListener(e -> mostrarGestionDesempeno());
        btnReportes.addActionListener(e -> mostrarVentanaReportes());

        frame.add(menuPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void mostrarGestionCursos() {
        JFrame cursosFrame = new JFrame("Gestionar Cursos");
        cursosFrame.setSize(400, 300);
        cursosFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblIdCurso = new JLabel("ID del Curso:");
        JTextField txtIdCurso = new JTextField();
        JLabel lblNombreCurso = new JLabel("Nombre del Curso:");
        JTextField txtNombreCurso = new JTextField();
        JButton btnAgregarCurso = new JButton("Agregar Curso");
        JButton btnCancelarCurso = new JButton("Cancelar");

        cursosFrame.add(lblIdCurso);
        cursosFrame.add(txtIdCurso);
        cursosFrame.add(lblNombreCurso);
        cursosFrame.add(txtNombreCurso);
        cursosFrame.add(btnAgregarCurso);
        cursosFrame.add(btnCancelarCurso);

        btnAgregarCurso.addActionListener(e -> {
            String idCurso = txtIdCurso.getText().trim();
            String nombreCurso = txtNombreCurso.getText().trim();
            if (!idCurso.isEmpty() && !nombreCurso.isEmpty()) {
                if (!idExiste(cursos, idCurso)) {
                    cursos.add(idCurso + " - " + nombreCurso);
                    JOptionPane.showMessageDialog(frame, "Curso agregado exitosamente.");
                    cursosFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "El ID del curso ya existe.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Ingrese un ID y nombre de curso válido.");
            }
        });

        btnCancelarCurso.addActionListener(e -> cursosFrame.dispose());
        cursosFrame.setVisible(true);
    }

    private void mostrarGestionProfesores() {
        JFrame profesoresFrame = new JFrame("Gestionar Profesores");
        profesoresFrame.setSize(400, 300);
        profesoresFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblIdProfesor = new JLabel("ID del Profesor:");
        JTextField txtIdProfesor = new JTextField();
        JLabel lblNombreProfesor = new JLabel("Nombre del Profesor:");
        JTextField txtNombreProfesor = new JTextField();
        JLabel lblCursoProfesor = new JLabel("Curso:");
        JComboBox<String> comboCursos = new JComboBox<>(cursos.toArray(new String[0]));
        JButton btnAgregarProfesor = new JButton("Agregar Profesor");
        JButton btnCancelarProfesor = new JButton("Cancelar");

        profesoresFrame.add(lblIdProfesor);
        profesoresFrame.add(txtIdProfesor);
        profesoresFrame.add(lblNombreProfesor);
        profesoresFrame.add(txtNombreProfesor);
        profesoresFrame.add(lblCursoProfesor);
        profesoresFrame.add(comboCursos);
        profesoresFrame.add(btnAgregarProfesor);
        profesoresFrame.add(btnCancelarProfesor);

        btnAgregarProfesor.addActionListener(e -> {
            String idProfesor = txtIdProfesor.getText().trim();
            String nombreProfesor = txtNombreProfesor.getText().trim();
            String cursoSeleccionado = (String) comboCursos.getSelectedItem();
            if (!idProfesor.isEmpty() && !nombreProfesor.isEmpty() && cursoSeleccionado != null) {
                if (!idExiste(profesores, idProfesor)) {
                    profesores.add(idProfesor + " - " + nombreProfesor + " (Curso: " + cursoSeleccionado + ")");
                    JOptionPane.showMessageDialog(frame, "Profesor agregado exitosamente.");
                    profesoresFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "El ID del profesor ya existe.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Ingrese un ID y nombre de profesor válido.");
            }
        });

        btnCancelarProfesor.addActionListener(e -> profesoresFrame.dispose());
        profesoresFrame.setVisible(true);
    }

    private void mostrarGestionEstudiantes() {
        JFrame estudiantesFrame = new JFrame("Gestionar Estudiantes");
        estudiantesFrame.setSize(400, 300);
        estudiantesFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblIdEstudiante = new JLabel("ID del Estudiante:");
        JTextField txtIdEstudiante = new JTextField();
        JLabel lblNombreEstudiante = new JLabel("Nombre:");
        JTextField txtNombreEstudiante = new JTextField();
        JLabel lblCursoEstudiante = new JLabel("Curso:");
        JComboBox<String> comboCursos = new JComboBox<>(cursos.toArray(new String[0]));
        JButton btnAgregarEstudiante = new JButton("Agregar Estudiante");
        JButton btnCancelarEstudiante = new JButton("Cancelar");

        estudiantesFrame.add(lblIdEstudiante);
        estudiantesFrame.add(txtIdEstudiante);
        estudiantesFrame.add(lblNombreEstudiante);
        estudiantesFrame.add(txtNombreEstudiante);
        estudiantesFrame.add(lblCursoEstudiante);
        estudiantesFrame.add(comboCursos);
        estudiantesFrame.add(btnAgregarEstudiante);
        estudiantesFrame.add(btnCancelarEstudiante);

        btnAgregarEstudiante.addActionListener(e -> {
            String idEstudiante = txtIdEstudiante.getText().trim();
            String nombreEstudiante = txtNombreEstudiante.getText().trim();
            String cursoSeleccionado = (String) comboCursos.getSelectedItem();
            if (!idEstudiante.isEmpty() && !nombreEstudiante.isEmpty() && cursoSeleccionado != null) {
                if (!idExiste(estudiantes, idEstudiante)) {
                    estudiantes.add(idEstudiante + " - " + nombreEstudiante + " (Curso: " + cursoSeleccionado + ")");
                    JOptionPane.showMessageDialog(frame, "Estudiante agregado exitosamente.");
                    estudiantesFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "El ID del estudiante ya existe.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Ingrese un ID y nombre de estudiante válido.");
            }
        });

        btnCancelarEstudiante.addActionListener(e -> estudiantesFrame.dispose());
        estudiantesFrame.setVisible(true);
    }

    
       private void mostrarGestionMaterias() {
        JFrame materiasFrame = new JFrame("Gestionar Materias");
        materiasFrame.setSize(400, 300);
        materiasFrame.setLayout(new BorderLayout());

        DefaultListModel<String> modeloMaterias = new DefaultListModel<>();
        for (String materia : materias.listarMaterias()) {
            modeloMaterias.addElement(materia);
        }
        JList<String> listaMaterias = new JList<>(modeloMaterias);

        JPanel panelInferior = new JPanel(new FlowLayout());
        JTextField txtMateria = new JTextField(20);
        JButton btnAgregar = new JButton("Agregar Materia");
        JButton btnEliminar = new JButton("Eliminar Materia");
        JButton btnCerrar = new JButton("Cerrar");

        btnAgregar.addActionListener(e -> {
            String nuevaMateria = txtMateria.getText().trim();
            if (!nuevaMateria.isEmpty()) {
                materias.agregarMateria(nuevaMateria);
                modeloMaterias.addElement(nuevaMateria);
                txtMateria.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor ingrese un nombre de materia válido.");
            }
        });

        btnEliminar.addActionListener(e -> {
            String materiaSeleccionada = listaMaterias.getSelectedValue();
            if (materiaSeleccionada != null) {
                materias.listarMaterias().remove(materiaSeleccionada);
                modeloMaterias.removeElement(materiaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor seleccione una materia para eliminar.");
            }
        });

        btnCerrar.addActionListener(e -> materiasFrame.dispose());

        panelInferior.add(txtMateria);
        panelInferior.add(btnAgregar);
        panelInferior.add(btnEliminar);
        panelInferior.add(btnCerrar);

        materiasFrame.add(new JScrollPane(listaMaterias), BorderLayout.CENTER);
        materiasFrame.add(panelInferior, BorderLayout.SOUTH);
        materiasFrame.setVisible(true);
    }

       private void mostrarGestionDesempeno() {
        JFrame desempenoFrame = new JFrame("Gestionar Desempeño de Notas");
        desempenoFrame.setSize(400, 300);
        desempenoFrame.setLayout(new BorderLayout());

        DefaultListModel<Double> modeloNotas = new DefaultListModel<>();
        for (Double nota : desempenoNotas.getNotas()) {
            modeloNotas.addElement(nota);
        }
        JList<Double> listaNotas = new JList<>(modeloNotas);

        JPanel panelInferior = new JPanel(new FlowLayout());
        JTextField txtNota = new JTextField(5);
        JButton btnAgregar = new JButton("Agregar Nota");
        JButton btnCalcular = new JButton("Calcular Promedio");
        JButton btnCerrar = new JButton("Cerrar");

        btnAgregar.addActionListener(e -> {
            try {
                double nuevaNota = Double.parseDouble(txtNota.getText().trim());
                if (nuevaNota >= 0 && nuevaNota <= 100) {
                    desempenoNotas.agregarNota(nuevaNota);
                    modeloNotas.addElement(nuevaNota);
                    txtNota.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor ingrese una nota válida entre 0 y 100.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor ingrese un número válido.");
            }
        });

        btnCalcular.addActionListener(e -> {
            double promedio = desempenoNotas.calcularPromedio();
            String desempeno = desempenoNotas.obtenerDesempeno();
            JOptionPane.showMessageDialog(frame, "Promedio: " + promedio + "\nDesempeño: " + desempeno);
            txtNota.setText("");
        });

        btnCerrar.addActionListener(e -> desempenoFrame.dispose());

        panelInferior.add(txtNota);
        panelInferior.add(btnAgregar);
        panelInferior.add(btnCalcular);
        panelInferior.add(btnCerrar);

        desempenoFrame.add(new JScrollPane(listaNotas), BorderLayout.CENTER);
        desempenoFrame.add(panelInferior, BorderLayout.SOUTH);
        desempenoFrame.setVisible(true);
    }
       
        private void mostrarVentanaReportes() {
        JFrame reportesFrame = new JFrame("Generar Reportes");
        reportesFrame.setSize(400, 300);
        reportesFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnReporteCursos = new JButton("Reporte de Cursos");
        JButton btnReporteEstudiantes = new JButton("Reporte de Estudiantes");
        JButton btnReporteProfesores = new JButton("Reporte de Profesores");
        JButton btnCerrar = new JButton("Cerrar");

        btnReporteCursos.addActionListener(e -> mostrarReporte("Cursos", cursos));
        btnReporteEstudiantes.addActionListener(e -> mostrarReporte("Estudiantes", estudiantes));
        btnReporteProfesores.addActionListener(e -> mostrarReporte("Profesores", profesores));
        btnCerrar.addActionListener(e -> reportesFrame.dispose());

        reportesFrame.add(btnReporteCursos);
        reportesFrame.add(btnReporteEstudiantes);
        reportesFrame.add(btnReporteProfesores);
        reportesFrame.add(btnCerrar);

        reportesFrame.setVisible(true);
    }

    private void mostrarReporte(String titulo, ArrayList<String> lista) {
        StringBuilder reporte = new StringBuilder("Reporte de " + titulo + ":\n\n");
        if (lista.isEmpty()) {
            reporte.append("No hay ").append(titulo.toLowerCase()).append(" registrados.\n");
        } else {
            for (String elemento : lista) {
                reporte.append(elemento).append("\n");
            }
        }
        JOptionPane.showMessageDialog(frame, reporte.toString());
    }

    private boolean idExiste(ArrayList<String> lista, String id) {
        for (String elemento : lista) {
            if (elemento.startsWith(id + " -")) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        new SistemaGestionGUI();
    }
}
