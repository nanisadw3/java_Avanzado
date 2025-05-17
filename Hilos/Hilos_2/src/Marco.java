import javax.swing.*;

public class Marco extends JFrame {
    public Marco() {
        setTitle("Marco");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Lamina l = new Lamina();
        add(l);
    }
}
