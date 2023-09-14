package entities;

import java.io.FileWriter;
import java.io.IOException;

public class WordSaver {

    public static void saveWord(String word) {
        try {
            // Cambia la ruta y el nombre del archivo según tus necesidades
            FileWriter writer = new FileWriter("D:\\Eclipse\\sts-4.18.1.RELEASE\\XXX\\nombres\\words.txt", true);

            // Escribe la palabra en el archivo
            writer.write(word);
            writer.write("\n"); // Agrega un salto de línea para separar palabras

            // Cierra el archivo
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}