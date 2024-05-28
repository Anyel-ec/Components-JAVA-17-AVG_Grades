import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Map<String, Object>> NotasEstudiantes = new HashMap<>();

        // Crear la ventana principal
        JFrame frame = new JFrame("Registro de Notas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Crear el panel de entrada de datos
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Nombre del Estudiante:");
        JTextField nameField = new JTextField();

        JLabel gradesLabel = new JLabel("Notas (separadas por comas):");
        JTextField gradesField = new JTextField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(gradesLabel);
        inputPanel.add(gradesField);

        // Crear el botón para calcular y guardar las notas
        GradeCalculator calculateButton = new GradeCalculator("Guardar Notas");
        inputPanel.add(calculateButton);

        // Crear el área de texto para mostrar los registros
        textArea outputArea = new textArea();
        outputArea.setEditable(false);

        // Agregar los paneles al marco
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        JLabel searchLabel = new JLabel("Buscar Estudiante:");
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Buscar");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel notasPanel = new JPanel();
        notasPanel.setLayout(new GridLayout(1, 3));
        JLabel editLabel = new JLabel("Editar Notas:");
        JTextField editField = new JTextField();
        JButton editButton = new JButton("Editar");
        notasPanel.add(editLabel);
        notasPanel.add(editField);
        notasPanel.add(editButton);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));

        southPanel.add(searchPanel);
        southPanel.add(notasPanel);

        frame.add(southPanel, BorderLayout.SOUTH);

        // Agregar el comportamiento al botón
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText(); // valor obtenido del textField
                String[] notasStr = gradesField.getText().split(","); // valor obtenido del textField
                List<Double> notas = new ArrayList<>();

                try {
                    for (String nota : notasStr) {
                        notas.add(Double.parseDouble(nota.trim()));
                    }

                    Map<String, Object> registroNotas = GradeCalculator.guardarNotasEstudiante(nombre, notas);
                    registroNotas = textArea.convertirNombreAMayusculas(registroNotas);

                    NotasEstudiantes.put(nombre, registroNotas);

                    outputArea.append("Nombre: " + registroNotas.get("nombre") + "\n");
                    outputArea.append("Notas: " + registroNotas.get("notas") + "\n");
                    outputArea.append("Promedio: " + registroNotas.get("promedio") + "\n\n");


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor ingrese notas válidas.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = searchField.getText();
                List<Double> notas = searchStudent.obtenerNotas(NotasEstudiantes, nombre);
                if (notas != null) {
                    editField.setText(notas.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar el comportamiento al botón de edición
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = searchField.getText();
                String notasStr = editField.getText();
                List<Double> nuevasNotas = new ArrayList<>();

                String[] notasArray = notasStr.split(",");
                for (String notaStr : notasArray) {
                    try {
                        double nota = Double.parseDouble(notaStr.trim());
                        nuevasNotas.add(nota);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Por favor ingrese notas válidas", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                EditNote.editarNotas(NotasEstudiantes, nombre, nuevasNotas);
                JOptionPane.showMessageDialog(frame, "Notas actualizadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                searchField.setText("");
                editField.setText("");
                outputArea.setText("");

                System.out.println(NotasEstudiantes);
                for (Map.Entry<String, Map<String, Object>> entry : NotasEstudiantes.entrySet()) {
                    String estudiante = entry.getKey();
                    Map<String, Object> registroNotas = entry.getValue();
                    outputArea.append("Nombre: " + estudiante + "\n");
                    outputArea.append("Notas: " + registroNotas.get("notas") + "\n");
                    outputArea.append("Promedio: " + registroNotas.get("promedio") + "\n\n");
                }
            }
        });

        // Hacer visible el marco
        frame.setVisible(true);
    }
}
