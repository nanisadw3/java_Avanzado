package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;

public class Lamina extends JPanel {
    private SecretKey secretKey;
    private IvParameterSpec iv;
    JTextArea txt_info;

    public Lamina() {
        setLayout(new BorderLayout());
        generarClaveAES(); // Generar clave al iniciar
        add(generarLamina_txtFile(), BorderLayout.CENTER);
        add(generarLamina_botones(), BorderLayout.SOUTH);
    }

    private JPanel generarLamina_txtFile() {
        JPanel panel = new JPanel(new BorderLayout());
        txt_info = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txt_info);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel generarLamina_botones() {
        JPanel panel = new JPanel();
        JButton btn_guardar = new JButton("Guardar");
        JButton btn_importar = new JButton("Importar");

        btn_guardar.addActionListener(e -> accion_guardar());
        btn_importar.addActionListener(e -> accion_exportar());

        panel.add(btn_guardar);
        panel.add(btn_importar);
        return panel;
    }

    private void generarClaveAES() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            secretKey = keyGen.generateKey();

            byte[] ivBytes = new byte[16];
            new SecureRandom().nextBytes(ivBytes);
            iv = new IvParameterSpec(ivBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void accion_guardar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo");

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".enc")) {
                archivo = new File(archivo.getAbsolutePath() + ".enc");
            }

            try (FileOutputStream fos = new FileOutputStream(archivo)) {
                String contenido = txt_info.getText();
                byte[] encryptedData = cifrar(contenido);

                fos.write(iv.getIV());  // Guardamos IV al inicio del archivo
                fos.write(encryptedData);
                fos.flush();

                JOptionPane.showMessageDialog(this, "Archivo cifrado guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void accion_exportar() {
        txt_info.setText("");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Cargar archivo");

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            try (FileInputStream fis = new FileInputStream(archivo)) {
                byte[] ivBytes = new byte[16];
                fis.read(ivBytes); // Leer IV del archivo
                iv = new IvParameterSpec(ivBytes);

                byte[] encryptedData = fis.readAllBytes();
                String contenidoDescifrado = descifrar(encryptedData);

                txt_info.setText(contenidoDescifrado);

                JOptionPane.showMessageDialog(this, "Archivo descifrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private byte[] cifrar(String texto) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(texto.getBytes());
    }

    private String descifrar(byte[] datosCifrados) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decryptedBytes = cipher.doFinal(datosCifrados);
        return new String(decryptedBytes);
    }
}
