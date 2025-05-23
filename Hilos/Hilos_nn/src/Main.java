import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Diario d = new Diario("Inaki"); // misma instancia compartida

        Ejecutar_Diario e = new Ejecutar_Diario(d);
        Despertador despertador = new Despertador(d);

        Thread t = new Thread(e);
        Thread t2 = new Thread(despertador);

        t.start();

        // Esperamos 5 segundos y luego lanzamos el hilo que lo despierta
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        t2.start(); // ¡Despierta a Iñaki!
    }
}

class Personas {
    private String nombre;

    public Personas(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

class Diario {
    ArrayList<Personas> personas = new ArrayList<>();

    public Diario(String nombre) {
        personas.add(new Personas(nombre));
    }

    public void accionProgramar() {
        for (var persona : personas) {
            System.out.println(persona.getNombre() + " está programando");
        }
    }

    public void acciopnEstudiar() {
        for (var persona : personas) {
            System.out.println(persona.getNombre() + " está estudiando");
        }
    }

    public synchronized void accionDormir() {//synchronized para que solo un hilo pueda acceder a el
        for (var persona : personas) {
            System.out.println(persona.getNombre() + " está durmiendo " + Thread.currentThread());
        }
        try {
            wait(); // Aquí se duerme
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Despertando a " + personas.get(0).getNombre());
    }

    public synchronized void despertar() {//synchronized para que solo un papa pueda acceder a el
        System.out.println("Papá dice: ¡A levantarse!");
        notifyAll(); // Aquí lo despiertas
    }
}

// Clase que ejecuta el diario de Iñaki
class Ejecutar_Diario implements Runnable {
    Diario d;

    public Ejecutar_Diario(Diario d) {
        this.d = d;
    }

    @Override
    public void run() {
            d.accionProgramar();
            esperar();

            d.acciopnEstudiar();
            esperar();

            d.accionDormir();
            esperar();

    }

    private void esperar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Clase que despierta a Iñaki
class Despertador implements Runnable {
    Diario d;

    public Despertador(Diario d) {
        this.d = d;
    }

    @Override
    public void run() {
        System.out.println("este es el hilo " + Thread.currentThread());
        d.despertar(); // Despierta a Iñaki
    }
}
