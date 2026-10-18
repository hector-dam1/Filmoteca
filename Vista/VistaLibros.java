package Vista;

import java.util.List;

import Modelo.Pelicula;

/**
 * Vista de peliculas, menu y formularios por pantalla

 */
public class VistaLibros{

    /** Opciones del submenú de libros. */
    public static final int LISTAR = 1;
    public static final int ANADIR = 2;
    public static final int MODIFICAR = 3;
    public static final int BORRAR = 4;
    public static final int VOLVER = 5;
 
    private static final int MAX_PAGINAS = 100000;
 
    private final EntradaUsuario entrada;
    //naturalmente  uy parecido a vistapeliculas
    public VistaLibros(Entrada entrada) {
        this.entrada = entrada;
    }
/** submenú de libros. */
    public void mostrarMenu() {
        System.out.println("\n--- LIBROS ---");
        System.out.println("1. Ver libros");
        System.out.println("2. Añadir libro");
        System.out.println("3. Modificar libro");
        System.out.println("4. Borrar libro");
        System.out.println("5. Volver");
    }
    /**
     * Lee la opción del submenú.
     * @return un número entre 1 y 5
     */
    public int leerOpcion() {
        return entrada.leerEntero("Elige una opción: ", LISTAR, VOLVER);
    }
        public void mostrarLibros(List<Libro> libros) {
        System.out.println("\n--- Listado de libros ---");
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados.");
        } else {
            //eso no sabía hacerlo en java ni nde coña; lo miré en internet
            System.out.printf("%-4s %-30s %-26s %-8s %s%n",
                    "ID", "Título", "Categoría", "Páginas", "Escritor");
            for (Libro l : libros) {
                System.out.printf("%-4d %-30s %-26s %-8d %s%n",
                        l.getId(), l.getTitulo(), l.getCategoria(),
                        l.getNumPaginas(), l.getEscritor());
            }
        }
        entrada.esperarEnter();
    }



 
 

}