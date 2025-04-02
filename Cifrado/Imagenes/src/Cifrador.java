import javax.crypto.KeyGenerator;
import java.security.Key;

public class Cifrador {
    public Cifrador() {
        try {
            KeyGenerator generador = KeyGenerator.getInstance("AES");
            generador.init(128);
            Key llave = generador.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void cifrar() {

    }
    private void descifrar() {

    }
}
