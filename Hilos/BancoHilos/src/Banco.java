import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {

    ArrayList<Double> cuentas;
    //private Lock bloqueo = new ReentrantLock();
    //private Condition saldosuficiente = bloqueo.newCondition();
    public Banco(){
        //creamis las 100 cuentas con 2000.0$
        cuentas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            cuentas.add(2000.0);
        }
        //saldosuficiente = bloqueo.newCondition();
    }
    public synchronized void Transferencia(int origen, int destino, double cantidad){//metodo snchronizado para que solo un hilo pueda acceder a el

        //bloqueo.lock();bloqueamos el codigo para que otro hilo no pueda acceder a el
        try {
            if (cuentas.get(origen) >= cantidad){//verificamos que la cuenta origen tenga suficiente dinero
                System.out.println(Thread.currentThread());//imprime el hilo que hace la transferencia
                cuentas.set(origen, cuentas.get(origen) - cantidad);//resta la cantidad de la cuenta origen
                cuentas.set(destino, cuentas.get(destino) + cantidad);//suma la cantidad a la cuenta destino
                System.out.println("Se han transferido " + cantidad + "$ de la cuenta " + origen + " a la cuenta " + destino);
                System.out.println("El saldo total es " + saldoTotal() + "$");
                masDinero();
                //saldosuficiente.signalAll();//desbloquea el codigo para que otro hilo pueda acceder a el
                notifyAll();
                System.out.println("El hilo " + Thread.currentThread() + " ha terminado");
            }
            while (cuentas.get(origen)<= cantidad){//si la cuenta origen no tiene suficiente dinero, espera
                System.out.println("La cuenta " + origen + " no tiene suficiente dinero");
                System.out.println("Esperando a que la cuenta " + origen + " tenga suficiente dinero");
                System.out.println("El hilo " + Thread.currentThread() + " espera");
                //saldosuficiente.await();//espera a que se desbloquee el codigo
                wait();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }//finally {
//            bloqueo.unlock();//desbloqueamos el codigo para que otro hilo pueda acceder a el
//        }
    }
    private void masDinero(){
        double max = 0;
        for (var cuentas : cuentas) {
            if (cuentas > max){
                max = cuentas;
            }
        }
        System.out.println("La cuenta con mas dinero tiene " + max + "$" + " y es la cuenta " + cuentas.indexOf(max));
    }
    public double saldoTotal(){
        double total = 0;
        for (var cuentas : cuentas) {
            total += cuentas;
        }
        return total;
    }
}

class EjecusionT implements Runnable{

    private Banco b;
    private int origen;
    private double cantidad;
    public EjecusionT(Banco b, int origen, double cantidad){
        this.b = b;
        this.origen = origen;
        this.cantidad = cantidad;
    }
    @Override
    public void run() {

        while (true){
            int para = (int) (Math.random() * 100);
            double cantidad_t = Math.round((Math.random() * cantidad) * 100.0) / 100.0;
            b.Transferencia(origen, para, cantidad_t);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}