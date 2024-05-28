package top.anyel;

import javax.swing.*;
import java.util.Map;

public class textArea extends JTextArea {

    public static Map<String, Object> convertirNombreAMayusculas(Map<String, Object> registroNotas) {
        JFrame frame = new JFrame("TextArea Map Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        String nombre = (String) registroNotas.get("nombre");
        if (nombre != null) {
            registroNotas.put("nombre", nombre.toUpperCase());
        }
        textArea area = new textArea();

        area.setText("Nombre: " + registroNotas.get("nombre") + "\n" +
        "Notas: " + registroNotas.get("notas") + "\n" +
        "Promedio: " + registroNotas.get("promedio"));

        return registroNotas;
    }
}
