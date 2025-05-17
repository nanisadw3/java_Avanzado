public class MetodosGenericos {

    public static <T> String getElementos(T[] aregloGenerico){

        return "El arreglo tiene " + aregloGenerico.length;
    }//para no ocupar una instancia de esta clase para ocupar el metodo
    public static <T extends Comparable> T get_menor(T[] aregloGenerico){
        //todos los elementos que implememnten la interfaz comparable
        if(aregloGenerico == null || aregloGenerico.length == 0){
            return null;
        }else{
            T menor = aregloGenerico[0];
            for (int i = 1; i < aregloGenerico.length; i++) {
                if(((Comparable<T>) menor).compareTo(aregloGenerico[i]) > 0){
                    menor = aregloGenerico[i];
                }
                return menor;
            }
        }
        return null;
    }

}