import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

    }
}
class Marco extends JFrame {
    public Marco() {

    }
}
class Lamina extends JPanel {
    public Lamina() {

    }
    private JPanel crearLaminaU() {
        JPanel lamina = new JPanel();
        lamina.setLayout(new BorderLayout());
        label_imagen = new JLabel("La imagen aparecera aca");
        lamina.add(label_imagen, BorderLayout.CENTER);
        return lamina;
    }
    private JPanel crearLaminaD() {
        JPanel lamina = new JPanel();
        lamina.setLayout(new BorderLayout());

        JButton btn_Selecciona = new JButton("Selecciona");
        JButton btn_Cifra = new JButton("Cifrar y mostrar");

        lamina.add(btn_Selecciona, BorderLayout.NORTH);
        lamina.add(btn_Cifra, BorderLayout.SOUTH);



        return lamina;
    }
    JLabel label_imagen;

}