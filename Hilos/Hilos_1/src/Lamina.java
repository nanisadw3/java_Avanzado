import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lamina extends JPanel {
    ArrayList <Pelota> pelotas = new ArrayList<>();
    ArrayList<Thread> hilos = new ArrayList<>(); // Lista para almacenar los hilos

    public Lamina() {
        setLayout(new BorderLayout());
        lamina = new LaminaPelotas();
        JPanel laminab = lamina_Bottones();
        add(lamina, BorderLayout.CENTER);
        add(laminab, BorderLayout.SOUTH);

    }
    JButton crear = new JButton("Crear");
    JButton cerrar = new JButton("Cerrar");
    JButton interrumpir = new JButton("Interrumpir");
    private JPanel lamina_Bottones(){
        JPanel lamina = new JPanel();
        lamina.setLayout(new FlowLayout());
        crear.addActionListener(e -> eventoCrear());
        cerrar.addActionListener(e -> eventoCerrar());
        interrumpir.addActionListener(e-> eventoInterrumpir());
        lamina.add(crear);
        lamina.add(cerrar);
        lamina.add(interrumpir);
        return lamina;
    }
    Thread thread;
    private void eventoInterrumpir(){
        for (Thread hilo : hilos) {
            hilo.interrupt(); // Interrumpe cada hilo
        }
    }
    private void eventoCrear() {
        comienza();
    }
    private void eventoCerrar() {
        System.exit(0);
    }

    LaminaPelotas lamina;
    public void comienza() {
        Pelota p = new Pelota();
        lamina.add(p);

         thread = new Thread(() -> {
            while (!Thread.interrupted()){
                p.MuevePelota(lamina.getBounds());
                lamina.repaint();
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                }

            }
        });
        hilos.add(thread); // Agrega el hilo a la lista
        thread.start();
    }
}
//class PelotaHilos implements Runnable{
//
//    public PelotaHilos(Pelota pelota, Component lamina){
//        this.pelota = pelota;
//        this.lamina = lamina;
//
//    }
//    @Override
//    public void run() {
//        for ( int i = 0; i < 3000; i++){
//            pelota.MuevePelota(lamina.getBounds());
//            lamina.paint(lamina.getGraphics());
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    private Pelota pelota;
//    private Component lamina;
//}
class   LaminaPelotas extends JPanel{

    private ArrayList <Pelota> pelotas = new ArrayList<>();
    public void add(Pelota p){
        pelotas.add(p);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Pelota p : pelotas) {
            g2.fill(p.getShape());
        }
    }
}