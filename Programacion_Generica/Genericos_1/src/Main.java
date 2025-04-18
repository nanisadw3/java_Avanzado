import java.io.File;

public class Main {
    public static void main(String[] args) {
        Pareja<File> p = new Pareja();
        p.set(new File("Ejemplo.txt"));
        File archivo =p.get();// sin tener que castear por que ya le estamos diciendo de que tipo va a ser con <File>
        //la clase se adapta al tipo de objeto que nosotros queremos llamar desde la creacion del objeto
        System.out.println(archivo);

        Persona per = new Persona("Inaki");
        Pareja<Persona> pareja = new Pareja<Persona>();
        pareja.set(per);
        System.out.println(pareja.get().toString());


    }
}
class Persona{
    public Persona(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    private String nombre;
}