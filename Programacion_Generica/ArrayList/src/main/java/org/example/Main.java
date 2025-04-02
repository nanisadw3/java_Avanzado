package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /*
        Array normal
        Empleado lista[] = new Empleado[3];
        lista[0] = new Empleado("inaki", 22,2000);
        lista[1] = new Empleado("yaqui", 20,1000);
        lista[2] = new Empleado("luis", 32,5000);
        for (int i = 0; i < lista.length; i++) {
            System.out.println(lista[i].get_datos());
        }
        */
        //Los array list no pueden ser de datos primitivos
        ArrayList<Empleado> empleados = new ArrayList<>();
        // Aseguramos que el ArrayList tenga suficiente capacidad para al menos 11 elementos,
        // evitando redimensionamientos automáticos al agregar estos elementos posteriormente.
        empleados.ensureCapacity(11);

        empleados.add(new Empleado("inaki", 22,2000));//1
        empleados.add(new Empleado("yaqui", 20,1000));//2
        empleados.add(new Empleado("luis", 32,5000));//3
        empleados.add(new Empleado("inaki", 22,2000));//4
        empleados.add(new Empleado("yaqui", 20,1000));//5
        empleados.add(new Empleado("luis", 32,5000));//6
        empleados.add(new Empleado("inaki", 22,2000));//7
        empleados.add(new Empleado("yaqui", 20,1000));//8
        empleados.add(new Empleado("luis", 32,5000));//9
        empleados.add(new Empleado("inaki", 22,2000));//10
        // Los ArrayList tienen una capacidad inicial predeterminada de 10 elementos.
        // Cuando se supera esta capacidad, el ArrayList aumenta automáticamente
        // su tamaño para adaptarse a más elementos.
        empleados.add(new Empleado("inaki", 22,2000));//11

        empleados.trimToSize();//cortar el exeso de basura

        for (Empleado empleado : empleados) {
            System.out.println(empleado.get_datos());
        }
        System.out.println("La lista tiene: " + empleados.size() + " elementos");
    }
}
class Empleado{
    private String nombre;
    private int edad;
    private double salario;

    public Empleado(String nombre, int edad, double salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }
    public String get_datos() {
        return "El empleado se llama " + nombre + ", tiene " + edad + " anios y gana $" + salario;
    }
}
