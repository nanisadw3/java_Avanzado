package org.example;

import java.io.FileOutputStream;

public class Escritura {
    public void escribir(int[] imagen) {
        try {

            FileOutputStream imagen_c = new FileOutputStream("Umbreon_Copia.png");
            for (int i = 0; i < imagen.length; i++) {
                imagen_c.write(imagen[i]);
            }
            imagen_c.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
