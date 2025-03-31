import javax.swing.*;

public class Marco extends JFrame {
    public Marco(){
        setTitle("Practica");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Lamina lamina = new Lamina();
        add(lamina);
    }
}
