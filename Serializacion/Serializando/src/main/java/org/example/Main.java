package org.example;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {//Implementamos la clase "Serializable"
    //esto es para que cada uno de los objetos que se creem puedan ser "Exportados en bytes"
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        Administrador jefe = new Administrador("Inaki", 1000, 2002, 12, 18);
        jefe.setInsentivo(5000);

        Empleado[] empleados = new Empleado[3];
        empleados[0] = jefe;
        empleados[1] = new Empleado("Yaqui", 1000, 2000, 02, 20);
        empleados[2] = new Empleado("Gabriel", 1000, 2009, 10, 03);

        //nuevo
        //Escribimos
        try {
            FileOutputStream fo = new FileOutputStream("empleados.dat");//el archivo que se creara que tendra el flujo de datos
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(empleados);
            fo.close();
            oos.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //leemos
        try {
            FileInputStream fi = new FileInputStream("empleados.dat");
            ObjectInputStream ois = new ObjectInputStream(fi);

            Empleado[] recuperacion = (Empleado[]) ois.readObject();
            fi.close();
            ois.close();

            for (int i = 0; i < recuperacion.length; i++) {
                System.out.println(recuperacion[i]);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
class Empleado implements Serializable{
    private String nombre;
    private double sueldo;
    private int anio;
    private int mes;
    private int dia;
    Date fecha_contrato;
    public Empleado(String nombre, double sueldo, int anio, int mes, int dia) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        GregorianCalendar calendar = new GregorianCalendar(anio, mes, dia);
        fecha_contrato = calendar.getTime();
    }

    public String getNombre() {
        return nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public Date getFecha_contrato() {
        return fecha_contrato;
    }

    public void subirSueldo(double porsentaje) {
        double aumento = sueldo * porsentaje/100;
        sueldo += aumento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "fecha_contrato=" + fecha_contrato +
                ", sueldo=" + sueldo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

class Administrador extends Empleado {

    int insentivo;

    public Administrador(String nombre, double sueldo, int anio, int mes, int dia) {
        super(nombre, sueldo, anio, mes, dia);

        insentivo = 0;

    }

    public void setInsentivo(int insentivo) {
        this.insentivo = insentivo;
    }

    public double getSueldo() {
        double sueldo_base = super.getSueldo();
        sueldo_base += insentivo;
        return sueldo_base;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "insentivo=" + insentivo +
                '}';
    }
}