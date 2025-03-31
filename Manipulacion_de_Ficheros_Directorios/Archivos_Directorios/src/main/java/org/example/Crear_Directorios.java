package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Crear_Directorios {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //ponemos una ruta con nuevas carpetas que no existan
        try {

            File archivo = new File("CarpetaNuevaCreada");
            System.out.println(archivo.exists());
            archivo.mkdir();
            System.out.println(archivo.exists());

            File archivo2 = new File("FicheroNuevaCreado.txt");
            System.out.println(archivo2.exists());
            archivo2.createNewFile();
            System.out.println(archivo2.exists());
            String ruta = archivo2.getAbsolutePath();
            //ESCRIBIENDO DENTRO DE UN ARCHIVO
            Crear_Directorios c = new Crear_Directorios();
            c.escribirArchivo(ruta,"Hola a todo el mundo");

            c.eliminarArchivo(archivo);
            System.out.println(archivo.exists());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void eliminarArchivo(File file){
        file.delete();//eliminar archivos
    }
    private void escribirArchivo(String ruta, String mensaje) {

        try {
            FileWriter fw = new FileWriter(ruta, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mensaje);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}