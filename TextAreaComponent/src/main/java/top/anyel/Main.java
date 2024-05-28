package top.anyel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static top.anyel.textArea.convertirNombreAMayusculas;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Map<String, Object> registroNotas = new HashMap<>();
        registroNotas.put("nombre", "Juan");
        registroNotas.put("notas", "A, B, C");
        registroNotas.put("promedio", 85);

        registroNotas = convertirNombreAMayusculas(registroNotas);

        JFrame frame = new JFrame("TextArea Map Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        textArea area = new textArea();
        area.setText("Nombre: " + registroNotas.get("nombre") + "\n" +
                "Notas: " + registroNotas.get("notas") + "\n" +
                "Promedio: " + registroNotas.get("promedio"));

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(area), BorderLayout.CENTER);

        frame.setVisible(true);
    }

}