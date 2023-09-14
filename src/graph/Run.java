package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import test.*;


public class Run extends JFrame {

    private JTextField textField;
    private JTextArea textArea;
    private PrintStream standardOut;

    public Run() {
        // Configurar la ventana
        setTitle("Name Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Crear los componentes
        JButton button = new JButton("Run");
        textArea = new JTextArea(1, 10);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        Font font = textArea.getFont();
        textArea.setFont(new Font(font.getName(), font.BOLD, 80)); // Tamaño de fuente: 16
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Agregar los componentes a la ventana
        add(button);
        add(scrollPane);

        // Configurar el evento del botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirigir la salida estándar hacia el JTextArea
                redirectSystemOut();
                textArea.setText("");
                // Lógica para ejecutar la función main de la clase Test
                String[] args = { };
                Test.main(args);

                // Restaurar la salida estándar
                restoreSystemOut();
            }
        });

        // Mostrar la ventana
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void redirectSystemOut() {
        standardOut = System.out;

        // Crear un nuevo PrintStream y redirigir la salida estándar
        PrintStream customOut = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(customOut);
    }

    private void restoreSystemOut() {
        // Restaurar la salida estándar original
        System.setOut(standardOut);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Run();
            }
        });
    }

    // Clase personalizada para redirigir la salida hacia el JTextArea
    private static class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // Mostrar el texto en el JTextArea
            textArea.append(String.valueOf((char) b));
            // Mover el scroll hacia la posición más reciente
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}

