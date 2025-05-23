public class Main {
    public static void main(String[] args) {

    Banco b = new Banco();
    for(int i = 0; i < 100; i++){
        EjecusionT e = new EjecusionT(b, i, 2000);
        Thread t = new Thread(e);
        t.start();

        try {
            t.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    }
}