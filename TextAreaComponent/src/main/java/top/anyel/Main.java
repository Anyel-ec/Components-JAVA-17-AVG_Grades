package top.anyel;

import java.util.List;
import java.util.Map;

import static top.anyel.botonCalcular.guardarNotasEstudiante;
import static top.anyel.textArea.convertirNombreAMayusculas;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<Double> notas = List.of(5.0, 6.0, 7.0);
        Map<String, Object> registro = guardarNotasEstudiante("juan", notas);

        System.out.println("Antes de convertir a mayúsculas: " + registro);
        Map<String, Object> registroActualizado = convertirNombreAMayusculas(registro);
        System.out.println("Después de convertir a mayúsculas: " + registroActualizado);

    }
}