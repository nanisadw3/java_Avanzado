package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Buffer_Escritura {
    public void Escritura() {
        try {

            FileWriter fw = new FileWriter("Escritura.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < 15; i++) {
                bw.write(i + ") " + "Escritura " + System.currentTimeMillis() + "ms\n");//System.currentTimeMillis() cuanto tarda un proceso en ejecutarse
            }
            bw.close();
            /*
            * Los buffers nos permiten esvribir o leer archivos muchomas amplios que con un simple FileWriter/FileWraiter
            */

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
