import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Lamina extends JPanel {
    Thread t1,t2,t3;
    private LaminaPelotas lamina;
    private ArrayList <Pelota> pelotas = new ArrayList<>();
    Thread thread;

    public Lamina(){
        setLayout(new BorderLayout());
        lamina = new LaminaPelotas();
        JPanel laminab = lamina_Bottones();
        add(lamina, BorderLayout.CENTER);
        add(laminab, BorderLayout.SOUTH);
    }
    JButton h1 = new JButton("Hilo 1");
    JButton h2 = new JButton("Hilo 2");
    JButton h3 = new JButton("Hilo 3");
    JButton detener1 = new JButton("Detener 1");
    JButton detener2 = new JButton("Detener 2");
    JButton detener3 = new JButton("Detener 3");
    private JPanel lamina_Bottones(){
        JPanel lamina = new JPanel();
        lamina.setLayout(new FlowLayout());
        h1.addActionListener(e -> eventoh1(e));
        h2.addActionListener(e -> eventoh2(e));
        h3.addActionListener(e-> eventoh3(e));
        detener1.addActionListener(e -> {detener(t1);});
        detener2.addActionListener(e -> {detener(t2);});
        detener3.addActionListener(e -> {detener(t3);});
        lamina.add(h1);
        lamina.add(h2);
        lamina.add(h3);
        lamina.add(detener1);
        lamina.add(detener2);
        lamina.add(detener3);
        return lamina;
    }

    private void detener(Thread t){
        t.interrupt();
    }
    private void eventoh1(ActionEvent e){
        comienza(e);
    }
    private void eventoh2(ActionEvent e){
        comienza(e);
    }
    private void eventoh3(ActionEvent e){
        comienza(e);
    }
    private void eventoCerrar() {
        System.exit(0);
    }
    public void comienza(ActionEvent e) {
       if(e.getSource() == h1){
           Pelota p = new Pelota();
           lamina.add(p);
           t1 = new Thread(() -> {
               while (!Thread.interrupted()){
                   p.MuevePelota(lamina.getBounds());
                   lamina.repaint();
                   try {
                       Thread.sleep(2);
                   } catch (InterruptedException ex) {
                       Thread.currentThread().interrupt();
                   }
               }
           });
           t1.start();
       } else if (e.getSource() == h2) {
           Pelota p = new Pelota();
           lamina.add(p);
           t2= new Thread(() -> {
               while (!Thread.interrupted()){
                   p.MuevePelota(lamina.getBounds());
                   lamina.repaint();
                   try {
                       Thread.sleep(2);
                   } catch (InterruptedException ex) {
                       Thread.currentThread().interrupt();
                   }
               }
           });
           t2.start();
       }else if (e.getSource() == h3) {
           Pelota p = new Pelota();
           lamina.add(p);
           t3= new Thread(() -> {
               while (!Thread.interrupted()){
                   p.MuevePelota(lamina.getBounds());
                   lamina.repaint();
                   try {
                       Thread.sleep(2);
                   } catch (InterruptedException ex) {
                       Thread.currentThread().interrupt();
                   }
               }
           });
           t3.start();
       }
    }
}
class LaminaPelotas extends JPanel{

    private ArrayList<Pelota> pelotas = new ArrayList<>();
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