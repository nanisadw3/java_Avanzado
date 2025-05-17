public class Array {
    public Array(int z){
    lista = new Object[z];//lista generica

    }
    public Object get(int i){
        return lista[i];
    }
    public void add(Object o){
        lista[i] = o;
        i++;
    }
    public Object[] lista;
    private int i = 0;
}

