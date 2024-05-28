package top.anyel;

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
        // Crear la ventana principal
        // Crear el marco de la aplicación
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

        // Agregar el comportamiento al botón
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText();
                String[] notasStr = gradesField.getText().split(",");
                List<Double> notas = new ArrayList<>();

                try {
                    for (String nota : notasStr) {
                        notas.add(Double.parseDouble(nota.trim()));
                    }

                    Map<String, Object> registroNotas = GradeCalculator.guardarNotasEstudiante(nombre, notas);
                    registroNotas = textArea.convertirNombreAMayusculas(registroNotas);

                    outputArea.append("Nombre: " + registroNotas.get("nombre") + "\n");
                    outputArea.append("Notas: " + registroNotas.get("notas") + "\n");
                    outputArea.append("Promedio: " + registroNotas.get("promedio") + "\n\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor ingrese notas válidas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Hacer visible el marco
        frame.setVisible(true);
    }

}