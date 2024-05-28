package top.anyel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class textArea extends JTextArea {

    public static Map<String, Object> convertirNombreAMayusculas(Map<String, Object> registroNotas) {
        String nombre = (String) registroNotas.get("nombre");
        if (nombre != null) {
            registroNotas.put("nombre", nombre.toUpperCase());
        }
        return registroNotas;
    }
}
