package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Lamina extends JPanel {
    public Lamina() {
        setLayout(new BorderLayout());
        JPanel panel = generarLamina();
        add(panel, BorderLayout.CENTER);
    }
    private JPanel generarLamina(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Visor y Copiador de Imágenes", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton btn_Abrir = new JButton("Abrir");
        JButton btn_Copiar = new JButton("Copiar");
        label_imagen = new JLabel("No hay imagen seleccionada");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btn_Abrir, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(btn_Copiar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;// el botón ocupará 2 columnas para centrarse
        panel.add(label_imagen, gbc);

        btn_Abrir.addActionListener(e -> accion_abrir());
        btn_Copiar.addActionListener(e -> accion_copiar());

        return panel;
    }
    private void accion_copiar(){
        if (archivo == null) {
            JOptionPane.showMessageDialog(this, "Primero selecciona una imagen", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona la uvicacion donde quieres guardar la imagen");
            fileChooser.setSelectedFile(new File("copia_" + archivo.getName()));

            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivoCopia = fileChooser.getSelectedFile();

                try {

                    FileInputStream imagen_original = new FileInputStream(archivo);
                    FileOutputStream imagen_copia = new FileOutputStream(archivoCopia);
                    ArrayList<Integer> bytesImagen = new ArrayList<>();
                    int byteLeido;

                    //Leer todos los bytes del archivo original y guardarlos en el ArrayList
                    while ((byteLeido = imagen_original.read()) != -1) {
                        bytesImagen.add(byteLeido);
                    }
                    //Escribir los bytes almacenados en el ArrayList al archivo de destino
                    for (int byteActual : bytesImagen) {
                        imagen_copia.write(byteActual);
                    }
                    imagen_copia.close();
                    JOptionPane.showMessageDialog(this, "Imagen copiada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al copiar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }
                }
            }
    }
    private void accion_abrir() {
        JFileChooser fileChooser = new JFileChooser();
        //nombre del file chooser
        fileChooser.setDialogTitle("Seleccione una imagen");
        //filtro
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivo = fileChooser.getSelectedFile();
            // Verificar si realmente es una imagen
            String nombreArchivo = archivo.getName().toLowerCase();
            if (nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".png")) {

                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                label_imagen.setIcon(new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
                label_imagen.setText("");

            }else{
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un archivo de imagen válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
    private JLabel label_imagen;
    private File archivo;
}
