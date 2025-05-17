public class Empleado {
    private String nombrel;
    private int edad;
    private double salario;

    public String Dame_datos(){
        return "Nombre: " + nombrel + "\nEdad: " + edad + "\nSalario: " + salario;
    }

    public Empleado(String nombrel, int edad, double salario) {
        this.nombrel = nombrel;
        this.edad = edad;
        this.salario = salario;
    }
}
