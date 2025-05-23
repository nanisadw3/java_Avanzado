import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        Banco b = new Banco();
        for(int i = 0; i < 3; i++){
            EjecutarTransferencia e = new EjecutarTransferencia(b, i, 2000, 2000);
            Thread t = new Thread(e);
            t.start();

            try {
                t.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
class Cuentas{
    private double saldo;
    private int id;
    public Cuentas(int id, double saldo){
        this.id = id;
        this.saldo = saldo;
    }
    public double getSaldo(){
        return saldo;
    }
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    public int getId(){
        return id;
    }
}
class Banco{
    ArrayList <Cuentas> c = new ArrayList<>();
    private Lock bloqueo = new ReentrantLock();
    private Condition saldosuficiente = bloqueo.newCondition();

    public Banco(){
        for (int i = 0; i < 3; i++) {
            c.add(new Cuentas(i, 2000));
        }
    }
    public void Transferencia(int origen, int destino, double cantidad){
        try {
            bloqueo.lock();
            if (c.get(origen).getSaldo() >= cantidad){
                c.get(origen).setSaldo(c.get(origen).getSaldo()-cantidad);
                c.get(destino).setSaldo(c.get(origen).getSaldo()+cantidad);
                System.out.println("Se han transferido " + cantidad + "$ de la cuenta " + origen + " a la cuenta " + destino);
                System.out.println("El hilo " + Thread.currentThread() + " ha terminado");
                saldosuficiente.signalAll();
            }else{
                System.out.println("La cuenta " + origen + " quiere transferir " + cantidad + "$ a la cuenta " + destino + " pero no tiene suficiente dinero");
                System.out.println("Esperando a que la cuenta " + origen + " tenga suficiente dinero");
                System.out.println("El hilo " + Thread.currentThread() + " espera");
                saldosuficiente.await();//espera a que se desbloquee el codigo

                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            bloqueo.unlock();
        }
    }
}
class EjecutarTransferencia implements Runnable{
    private Banco b;
    private int origen;
    private int destino;
    private double cantidad;
    public EjecutarTransferencia(Banco b, int origen, int destino, double cantidad){
        this.b = b;
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }
    @Override
    public void run() {
        while (true){
            int para = (int) (Math.random() * 3);
            double cantidad_t = Math.round((Math.random() * cantidad) * 100.0) / 100.0;
            b.Transferencia(origen, para, cantidad_t);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}