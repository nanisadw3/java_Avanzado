package org.example;

import java.io.FileWriter;

public class Escritura {

    public void escribir() {
        try {

            String cadena = "Esta es la cadena que desea escribir y que posterior mente se va a leer";
            FileWriter fw = new FileWriter("Escritura.txt");
            for (int i = 0; i < cadena.length(); i++) {
                fw.write(cadena.charAt(i));
            }
            fw.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
