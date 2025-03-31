package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Empleado lista[] = new Empleado[3];
        lista[0] = new Empleado("inaki", 22,2000);
        lista[1] = new Empleado("yaqui", 20,1000);
        lista[2] = new Empleado("luis", 32,5000);
        for (int i = 0; i < lista.length; i++) {
            System.out.println(lista[i].get_datos());
        }

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
