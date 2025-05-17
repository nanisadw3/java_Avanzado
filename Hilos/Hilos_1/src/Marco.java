import javax.swing.*;

public class Marco extends JFrame {
    Marco(){
        setTitle("Marco");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Lamina lamina = new Lamina();
        add(lamina);
        setVisible(true);
    }
}
