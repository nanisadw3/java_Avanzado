public class Main {
    public static void main(String[] args) {
        String nombres[] = {"Inaki", "Luis", "Yaqui"};
        String elementos = MetodosGenericos.getElementos(nombres);
        System.out.println(elementos);

        String empleados[] = {new Empleado("Inaki", 25, 2000).Dame_datos(),
                new Empleado("Inaki", 25, 2000).Dame_datos(),
                new Empleado("Luis", 25, 2000).Dame_datos(),
                new Empleado("Yaqui", 25, 2000).Dame_datos()};

        System.out.println(MetodosGenericos.getElementos(empleados));

        System.out.println("El elemento emnor es " + MetodosGenericos.get_menor(nombres));
        System.out.println("El elemento emnor es " + MetodosGenericos.get_menor(empleados));
        //El elemento menor es desde el abesedario
    }
}