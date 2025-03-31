package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Buffer_Lectura {
    public void Escritura() {

        try {

            FileReader fr = new FileReader("Escritura.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
