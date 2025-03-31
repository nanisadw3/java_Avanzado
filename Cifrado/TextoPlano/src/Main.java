import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SecretKey clave;
        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");//Generador de claves
            keyGenerator.init(128);//le decimos que la clave sera de 128
            clave = keyGenerator.generateKey();// generamos la clave

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dame el texto que quieres cifrar");
        String texto = scanner.nextLine();
        //creamos un arreglo con el texto cifrado por que asi lo devuelbe el metodo
        byte[] texto_S = Cifrado.cifrar(texto, clave);
        //convertimos los bytes cifrados a Base64 para que sean legibles en formato de texto
        System.out.println("El texto cifrado es: " + Base64.getEncoder().encodeToString(texto_S));
        System.out.println("=================================================");
        //deciframos y lo mostramos en pantalla
        System.out.println("El texto descifrado es: "+Cifrado.descifrar(texto_S, clave));

    }
}
class Cifrado{
    //Hacemos los metodos Static para no tener que crear un objeto de la case en el main y solo usar los metodos
    public static byte[] cifrar(String texto, SecretKey clave){
        try {
            /*
            Cipher es una clase abstracta por lo que no se puede hacer un objeto de esta clase
            por lo que tendremos que tenemos que ocupar su metodo .getInstance("AES"); para generar una instancia (creamos un objeto)
            de esta clase
             */
            Cipher cipher = Cipher.getInstance("AES");
            //Configuramos el cifrador para que encripte (ENCRYPT_MODE) utilizando la clave que le pasamos (clave)
            cipher.init(Cipher.ENCRYPT_MODE,clave);
            //le pasamos el texto en Bytes para que los encripte
            byte[] textoCifrado = cipher.doFinal(texto.getBytes());
            //retornamos la cadena cifrada "esta cifrada en bytes"
            return textoCifrado;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String descifrar(byte[] texto_S, SecretKey clave){

        try {
            //creamos la misma instancia de Cipher
            Cipher cipher = Cipher.getInstance("AES");
            //ponemos el cifrador en modo de desencriptacion
            cipher.init(Cipher.DECRYPT_MODE,clave);
            //des encriptamos el texto mandando el arreflo de bytes
            byte[] textoDescifrado = cipher.doFinal(texto_S);
            //convertimos el arreglo de bytes desencriptado nuevamente a una cadena de texto
            return new String(textoDescifrado);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}