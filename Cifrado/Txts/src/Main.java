import javax.crypto.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            // Generación de la clave
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey llave = keyGenerator.generateKey();

            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Seleccione el archivo a cifrar");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Documentos (txt)", "txt");
            jfc.setFileFilter(filtro);

            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File archivo = jfc.getSelectedFile();
                if (archivo.exists()) {
                    // Leer archivo
                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    StringBuilder texto = new StringBuilder();
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        texto.append(linea).append("\n");
                    }
                    br.close();

                    System.out.println("El contenido del documento es: " + texto);

                    // Cifrar contenido
                    byte[] textoCifrado = Cifrado.cifrar(texto.toString(), llave);
                    String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado);
                    System.out.println("El texto cifrado es: " + textoCifradoBase64);

                    // Guardar en archivo cifrado
                    File archivoCifrado = new File(archivo.getAbsolutePath() + ".cifrado");
                    FileWriter fw = new FileWriter(archivoCifrado);
                    fw.write(textoCifradoBase64);
                    fw.close();

                    System.out.println("Archivo cifrado guardado como: " + archivoCifrado.getAbsolutePath());

                    // Descifrar contenido
                    String textoDescifrado = Cifrado.descifrar(textoCifradoBase64, llave);
                    System.out.println("Texto descifrado: " + textoDescifrado);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Cifrado {
    public static byte[] cifrar(String texto, SecretKey clave) {
        try {
            Cipher cifrado = Cipher.getInstance("AES");
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
            return cifrado.doFinal(texto.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error en el cifrado", e);
        }
    }

    public static String descifrar(String textoCifradoBase64, SecretKey clave) {
        try {
            Cipher descifrado = Cipher.getInstance("AES");
            descifrado.init(Cipher.DECRYPT_MODE, clave);
            byte[] textoCifrado = Base64.getDecoder().decode(textoCifradoBase64);
            return new String(descifrado.doFinal(textoCifrado));
        } catch (Exception e) {
            throw new RuntimeException("Error en el descifrado", e);
        }
    }
}
