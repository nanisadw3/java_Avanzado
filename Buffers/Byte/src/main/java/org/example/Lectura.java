package org.example;

import java.io.FileInputStream;
import java.io.IOException;

public class Lectura {
    public int[] leer() {
        try{

            int[] img = new int[121691];

            FileInputStream imagen = new FileInputStream("Umbreon.png");

            int codigo;
            int contador = 0;

            while ((codigo = imagen.read()) != -1) {

                img[contador] = codigo;//Almacenamos cada byte de la imagen
                System.out.println(img[contador]);
                contador++;

            }
            System.out.println("La imagen tiene " + contador + " bytes");

            return img;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
