package org.example;

import javax.swing.*;
import java.io.File;
import java.sql.Array;
import java.util.ArrayList;

public class AccesoFicheros {
    public static void main(String[] args) {

        File ruta = new File("Carpeta");
        System.out.println("Ruta: " + ruta.getAbsolutePath());
        String[] nombres = ruta.list();
        for (int i = 0; i < nombres.length; i++) {
            File f = new File(ruta, nombres[i]);
            System.out.println(f.getName() + ": " + f.isDirectory());
            if (f.isDirectory()) {
                String[] subCarpeta = f.list();
                for (int j = 0; j < subCarpeta.length; j++) {
                    File f1 = new File(ruta, subCarpeta[j]);
                    System.out.println("------" + f1.getName() + ": " + f1.exists());
                }
            }
        }
    }
}
