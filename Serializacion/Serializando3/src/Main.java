import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Marco marco = new Marco();
        marco.setVisible(true);
        marco.setLocationRelativeTo(null);
    }
}
class Marco extends JFrame {
    public Marco(){

        setTitle("Serializando 3");
        setSize(550,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Lamina lamina = new Lamina();
        add(lamina);

    }
}
class Lamina extends JPanel {
    public Lamina(){
        setLayout(new BorderLayout());
        add(lamina_d(),BorderLayout.EAST);
        add(lamina_h(),BorderLayout.WEST);

    }
    private JPanel lamina_d() {
        // Crear la lámina con BorderLayout
        JPanel lamina = new JPanel(new BorderLayout());

        // Crear el área de texto con scroll
        txt_inventario = new JTextArea(10, 30); // Especificar tamaño inicial
        txt_inventario.setLineWrap(true); // Ajuste de línea
        txt_inventario.setWrapStyleWord(true); // Evita cortar palabras
        txt_inventario.setEditable(false);
        JScrollPane scroll = new JScrollPane(txt_inventario);

        // Crear botones
        JButton btn_guardar = new JButton("Guardar");
        JButton btn_cargar = new JButton("Cargar");

        //poner a la escucha
        btn_guardar.addActionListener(e -> accion_guardar());
        btn_cargar.addActionListener(e -> accion_cargar());

        // Panel de botones
        JPanel panel_botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel_botones.add(btn_guardar);
        panel_botones.add(btn_cargar);

        lamina.add(scroll, BorderLayout.CENTER);
        lamina.add(panel_botones, BorderLayout.SOUTH);

        return lamina;
    }
    private void accion_cargar(){
        txt_inventario.setText("");
        try {
            if (archivo.exists()) {
                FileInputStream fw = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fw);
                inventario = (ArrayList<Productos>) ois.readObject();
                ois.close();
                fw.close();
                String texto_inventario;
                for ( Productos producto : inventario){
                    texto_inventario = producto.toString() + "\n";
                    txt_inventario.append(texto_inventario);
                }
                JOptionPane.showMessageDialog(null, "Inventario Cargado");
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun archivo para cargar");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void accion_guardar(){
        try {
            FileOutputStream fw = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fw);
            oos.writeObject(inventario);
            oos.close();
            fw.close();
            JOptionPane.showMessageDialog(this, "Inventario Guardado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        txt_inventario.setText("");

    }

    private JPanel lamina_h(){
        JPanel lamina = new JPanel();
        //Creamos la disposicion
        lamina.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //creamos los componentes
        txt_nombre = new JTextField(10);
        txt_cantidad = new JTextField(10);

        JLabel nombre = new JLabel("Nombre");
        JLabel cantidad = new JLabel("Cantidad");
        JButton agregar = new JButton("Agregar");

        agregar.addActionListener(e -> accion_agregar());

        gbc.gridx = 0;
        gbc.gridy = 0;
        lamina.add(nombre, gbc);
        gbc.gridx = 1;
        lamina.add(txt_nombre, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        lamina.add(cantidad, gbc);
        gbc.gridx = 1;
        lamina.add(txt_cantidad, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        lamina.add(agregar, gbc);


        return lamina;
    }
    private void accion_agregar(){
        String nombre = txt_nombre.getText();
        int cantidad = Integer.parseInt(txt_cantidad.getText());
        Productos p = new Productos(nombre, cantidad);
        inventario.add(p);
        txt_inventario.setText("");
        txt_nombre.setText("");
        txt_cantidad.setText("");
        String texto_inventario;
        for ( Productos producto : inventario){
            texto_inventario = producto.toString() + "\n";
            txt_inventario.append(texto_inventario);
        }
    }
    private final File archivo = new File("productos.ser");
    private ArrayList<Productos> inventario = new ArrayList<>();
    private JTextField txt_nombre;
    private JTextField txt_cantidad;
    private JTextArea txt_inventario;
}
