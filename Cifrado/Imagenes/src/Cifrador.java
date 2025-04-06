import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

class Cifrador {
    private static final String ALGORITMO = "AES";
    private SecretKey clave;

    public Cifrador() {
        try {
            // Generar una clave AES de 128 bits
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO);
            keyGen.init(128);
            clave = keyGen.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] cifrar(byte[] datos) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            return cipher.doFinal(datos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] descifrar(byte[] datosCifrados) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, clave);
            return cipher.doFinal(datosCifrados);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String claveEnTexto() {
        return Base64.getEncoder().encodeToString(clave.getEncoded());
    }
}
