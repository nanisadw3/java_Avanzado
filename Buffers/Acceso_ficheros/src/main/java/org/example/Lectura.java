package org.example;

import java.io.FileReader;

public class Lectura {
    public void leer(){

        try {

            FileReader fr = new FileReader("Escritura.txt");
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
            fr.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
