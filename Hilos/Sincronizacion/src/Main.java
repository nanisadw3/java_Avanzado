public class Main {
    public static void main(String[] args) {

//        Hilos h1 = new Hilos();
//        Hilos h2 = new Hilos();
//        h2.start();
//        try {
//            h2.join();//hasta que el hilo 2 termine
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        h1.start();
//        try {
//            h1.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("El hilo 1 ha terminado");
        Hilos hilo1 = new Hilos();
        HilosVarios hilo2 = new HilosVarios(hilo1);
        hilo1.start();
        hilo2.start();
        System.out.println("Prinera instruccon Main");
    }
}
class Hilos extends Thread{

    @Override
    public void run() {
        for (int i=0;i<15;i++){
            System.out.println(getName() + " -> " + i);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class HilosVarios extends Thread{
    private Thread hilo;
    public HilosVarios(Thread hilo){
        this.hilo = hilo;
    }
    @Override
    public void run() {
        try {
            hilo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i=0;i<15;i++){
            System.out.println(hilo.getName() + " (HV)-> " + i);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}