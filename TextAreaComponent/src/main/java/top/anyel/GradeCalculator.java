package top.anyel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeCalculator extends JButton {
    public GradeCalculator(String text) {
        super(text);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
    }    public static Map<String, Object> guardarNotasEstudiante(String nombre, List<Double> notas) {
        Map<String, Object> registroNotas = new HashMap<>();

        double suma = 0.0;
        for (Double nota : notas) {
            suma += nota;
        }
        double promedio = suma / notas.size();

        registroNotas.put("nombre", nombre);
        registroNotas.put("notas", notas);
        registroNotas.put("promedio", promedio);

        return registroNotas;
    }
}

