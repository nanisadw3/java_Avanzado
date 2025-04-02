public class Productos implements java.io.Serializable {
    public String nombre;
    public int cantidad;
    public Productos(String nombre, int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    public String toString(){
        return nombre + " " + cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
}
