import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static ComponenteCalcularNotas.GradeCalculator.guardarNotasEstudiante;

public class Main {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Registro de Notas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        // Crear los componentes de la interfaz
        JLabel labelNombre = new JLabel("Nombre del Estudiante:");
        JTextField textFieldNombre = new JTextField();
        JLabel labelNotas = new JLabel("Notas (separadas por comas):");
        JTextField textFieldNotas = new JTextField();
        JButton buttonCalcular = new JButton("Calcular Promedio");
        JTextArea textAreaResultado = new JTextArea();

        // Agregar los componentes a la ventana
        frame.add(labelNombre);
        frame.add(textFieldNombre);
        frame.add(labelNotas);
        frame.add(textFieldNotas);
        frame.add(buttonCalcular);
        frame.add(new JLabel()); // Espacio vacío
        frame.add(new JLabel()); // Espacio vacío
        frame.add(new JLabel()); // Espacio vacío
        frame.add(new JLabel("Resultado:"));
        frame.add(textAreaResultado);

        // Acción del botón
        buttonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEstudiante = textFieldNombre.getText();
                String notasTexto = textFieldNotas.getText();

                // Convertir las notas a una lista de Double
                String[] notasArray = notasTexto.split(",");
                List<Double> notasEstudiante = new ArrayList<>();
                try {
                    for (String nota : notasArray) {
                        notasEstudiante.add(Double.parseDouble(nota.trim()));
                    }

                    // Llamar a la lógica para calcular el promedio
                    Map<String, Object> registroNotasEstudiante = guardarNotasEstudiante(nombreEstudiante, notasEstudiante);

                    // Mostrar el resultado
                    textAreaResultado.setText("Nombre del estudiante: " + registroNotasEstudiante.get("nombre") + "\n" +
                            "Notas del estudiante: " + registroNotasEstudiante.get("notas") + "\n" +
                            "Promedio del estudiante: " + registroNotasEstudiante.get("promedio"));
                } catch (NumberFormatException ex) {
                    textAreaResultado.setText("Error: Asegúrese de ingresar solo números en las notas.");
                }
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}