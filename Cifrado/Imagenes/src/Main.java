import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        Marco marco = new Marco();
        marco.setVisible(true);
        marco.setTitle("Cifrado de imagenes");
        marco.setResizable(false);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLocationRelativeTo(null);
    }
}
class Marco extends JFrame {
    public Marco() {

        setSize(600, 400);
        Lamina lamina = new Lamina();
        add(lamina);
    }

    }

    class Lamina extends JPanel {
        public Lamina() {

            setLayout(new BorderLayout());
            JPanel lamina_U = crearLaminaUL();
            add(lamina_U, BorderLayout.CENTER);
            JPanel lamina_D = crearLaminaD();
            add(lamina_D, BorderLayout.SOUTH);
            JPanel lamina_R = crearLaminaUR();
            add(lamina_R, BorderLayout.EAST);
        }

        private JPanel crearLaminaUL() {
            JPanel lamina = new JPanel();
            lamina.setLayout(new BorderLayout());
            label_imagen = new JLabel("La imagen aparecera aca");
            label_imagen.setHorizontalAlignment(JLabel.CENTER);
            lamina.add(label_imagen, BorderLayout.CENTER);
            return lamina;
        }
        private JPanel crearLaminaUR() {
            JPanel lamina = new JPanel();
            lamina.setLayout(new BorderLayout());
            txt_cifrado = new JTextArea(10,10);
            txt_cifrado.setEditable(false);
            JScrollPane scroll = new JScrollPane(txt_cifrado);
            lamina.add(scroll, BorderLayout.CENTER);
            return lamina;
        }

        private JPanel crearLaminaD() {
            JPanel lamina = new JPanel();
            lamina.setLayout(new BorderLayout());

            JButton btn_Selecciona = new JButton("Seleccionar imagen");
            JButton btn_Cifra = new JButton("Cifrar y mostrar");

            btn_Selecciona.addActionListener(e -> imagen = accion_Seleccionar());
            btn_Cifra.addActionListener(e -> accion_Cifrar(imagen));

            JPanel lamina_botones = new JPanel();
            lamina_botones.setLayout(new FlowLayout(FlowLayout.CENTER));
            lamina_botones.add(btn_Selecciona);
            lamina_botones.add(btn_Cifra);
            lamina.add(lamina_botones, BorderLayout.CENTER);

            return lamina;
        }
        private void accion_Cifrar(File imagen) {
            if (imagen != null) {
                try {
                    // Leer la imagen como objeto BufferedImage
                    BufferedImage bfr = ImageIO.read(imagen);

                    if (bfr != null) {
                        // Convertir la imagen en un arreglo de bytes
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(bfr, "png", baos);
                        baos.flush();
                        byte[] imageBytes = baos.toByteArray();
                        baos.close();

                        // Cifrar los bytes de la imagen
                        Cifrador cifrador = new Cifrador();
                        byte[] encryptedBytes = cifrador.cifrar(imageBytes);

                        // Convertir a Base64 para mostrar
                        txt_cifrado.setText(Base64.getEncoder().encodeToString(encryptedBytes));
                    } else {
                        JOptionPane.showMessageDialog(this, "El archivo seleccionado no es una imagen válida", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al cifrar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private File accion_Seleccionar() {
            JFileChooser fch = new JFileChooser();
            fch.setDialogTitle("Seleccionar imagen");
            fch.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "png"));
            if(fch.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                File archivo = fch.getSelectedFile();
                if(archivo.exists()){
                    label_imagen.setText("");
                    label_imagen.setIcon(new ImageIcon(archivo.getAbsolutePath()));
                    return archivo;
                }else{
                    JOptionPane.showMessageDialog(this, "El archivo no existe");
                    return null;
                }

            }
            return null;

        }
        JTextArea txt_cifrado;
        JLabel label_imagen;
        File imagen;

    }
