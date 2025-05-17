import java.io.File;

public class UsoArray {
    public static void main(String[] args) {
        Array ar = new Array(6);
        ar.add("Hola");
        ar.add("Mundo");
        ar.add("soy");
        ar.add("Inaki");
        String nombre = (String) ar.get(3);
        System.out.println(nombre);//casteos

        ar.add(new File("pedidos.txt"));
        ar.add(new File("Ejemplo.txt"));
        File nombreArchivo = (File) ar.get(5);
        System.out.println(nombreArchivo);
    }
}
