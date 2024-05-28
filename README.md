# Grades Registry
## Description
This project is a Java desktop application that allows you to record, calculate, and edit student grades. The graphical user interface is built with `Swing`, and the custom components are packaged in a JAR file.

## Components
### EditNote
A class that extends `JButton` and allows editing a student's grades.

```java
public class EditNote extends JButton {
    public EditNote() {
    }

    public static void editarNotas(Map<String, Map<String, Object>> notasEstudiantes, String nombre, List<Double> nuevasNotas) {
        Map<String, Object> registroNotas = (Map)notasEstudiantes.get(nombre);
        double suma = 0.0;

        for (Double nota : nuevasNotas) {
            suma += nota;
        }

        double promedio = suma / nuevasNotas.size();
        registroNotas.put("notas", nuevasNotas);
        registroNotas.put("promedio", promedio);
    }
}
```

### GradeCalculator
A class that extends `JButton` and allows calculating and saving a student's grades.

```java
public class GradeCalculator extends JButton {
    public GradeCalculator(String text) {
        super(text);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
    }

    public static Map<String, Object> guardarNotasEstudiante(String nombre, List<Double> notas) {
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
```

### searchStudent
A class that allows retrieving a student's grades.

```java
public class searchStudent {
    public searchStudent() {
    }

    public static List<Double> obtenerNotas(Map<String, Map<String, Object>> notasEstudiantes, String nombre) {
        Map<String, Object> registroNotas = (Map)notasEstudiantes.get(nombre);
        return registroNotas != null ? (List)registroNotas.get("notas") : null;
    }
}
```

### textArea
A class that extends `JTextArea` and allows converting a student's name to uppercase.

```java
public class textArea extends JTextArea {
    public textArea() {
    }

    public static Map<String, Object> convertirNombreAMayusculas(Map<String, Object> registroNotas) {
        String nombre = (String)registroNotas.get("nombre");
        if (nombre != null) {
            registroNotas.put("nombre", nombre.toUpperCase());
        }
        return registroNotas;
    }
}
```

## Running the Project

### Requirements
- Java 8 or higher
- IDE like IntelliJ IDEA or Eclipse
- JAR file with custom components

### Instructions
1. **Set up the project:**
   - Create a new project in your IDE.
   - Add the JAR file with the custom components to the project's classpath.

2. **Main Code (`Main.java`):**
   - Copy and paste the following code into your `Main.java` file.

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, Map<String, Object>> NotasEstudiantes = new HashMap<>();

        // Create the main window
        JFrame frame = new JFrame("Grades Registry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create the data input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField();

        JLabel gradesLabel = new JLabel("Grades (comma-separated):");
        JTextField gradesField = new JTextField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(gradesLabel);
        inputPanel.add(gradesField);

        // Create the button to calculate and save grades
        GradeCalculator calculateButton = new GradeCalculator("Save Grades");
        inputPanel.add(calculateButton);

        // Create the text area to display records
        textArea outputArea = new textArea();
        outputArea.setEditable(false);

        // Add panels to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        JLabel searchLabel = new JLabel("Search Student:");
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel notasPanel = new JPanel();
        notasPanel.setLayout(new GridLayout(1, 3));
        JLabel editLabel = new JLabel("Edit Grades:");
        JTextField editField = new JTextField();
        JButton editButton = new JButton("Edit");
        notasPanel.add(editLabel);
        notasPanel.add(editField);
        notasPanel.add(editButton);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));

        southPanel.add(searchPanel);
        southPanel.add(notasPanel);

        frame.add(southPanel, BorderLayout.SOUTH);

        // Add action listener to the button
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

                    NotasEstudiantes.put(nombre, registroNotas);

                    outputArea.append("Name: " + registroNotas.get("nombre") + "\n");
                    outputArea.append("Grades: " + registroNotas.get("notas") + "\n");
                    outputArea.append("Average: " + registroNotas.get("promedio") + "\n\n");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid grades.",
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
                    JOptionPane.showMessageDialog(frame, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener to the edit button
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
                        JOptionPane.showMessageDialog(frame, "Please enter valid grades", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                EditNote.editarNotas(NotasEstudiantes, nombre, nuevasNotas);
                JOptionPane.showMessageDialog(frame, "Grades updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                searchField.setText("");
                editField.setText("");
                outputArea.setText("");

                for (Map.Entry<String, Map<String, Object>> entry : NotasEstudiantes.entrySet()) {
                    String estudiante = entry.getKey();
                    Map<String, Object> registroNotas = entry.getValue();
                    outputArea.append("Name: " + estudiante + "\n");
                    outputArea.append("Grades: " + registroNotas.get("notas") + "\n");
                    outputArea.append("Average: " + registroNotas.get("promedio") + "\n\n");
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
```

3. **Run the Project:**
   - Run the `Main` class from your IDE to start the application.

### Using the Application
1. **Register Grades:**
   - Enter the student's name in the "Student Name" field.
   - Enter the grades separated by commas in the "Grades (comma-separated)" field.
   - Click "Save Grades".

2. **Search Grades:**
   - Enter the student's name in the "Search Student" field.
   - Click "Search" to display the student's grades in the edit field.

3. **Edit Grades:**
   - After searching for a student, edit the grades in the "Edit Grades" field.
   - Click "Edit" to update the student's grades.

## Contact
For any questions or suggestions, please contact the project developer.
