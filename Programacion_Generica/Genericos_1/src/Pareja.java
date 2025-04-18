public class Pareja <T>{//Clase generica
    private T generico_1;
    public Pareja(){
        generico_1 = null;
    }
    public void set(T objeto){
        generico_1 = objeto;
    }
    public T get(){
        return generico_1;
    }
}
