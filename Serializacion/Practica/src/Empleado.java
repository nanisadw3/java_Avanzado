public class Empleado implements java.io.Serializable{
    private String nombre;
    private Double sueldo;
    private String fecha_Contrato;

    public Empleado(String nombre, Double sueldo, String fecha_Contrato) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.fecha_Contrato = fecha_Contrato;
    }
    public String getNombre() {
        return nombre;
    }
    public Double getSueldo() {
        return sueldo;
    }
    public String getFecha_Contrato() {
        return fecha_Contrato;
    }
    @Override
    public String toString() {
        return nombre + " " + sueldo + " " + fecha_Contrato;
    }

}
