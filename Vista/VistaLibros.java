package Vista;

import java.util.List;

import Clases.Libro;

/**
 * Vista de libros, menu y formularios por pantalla

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
    public VistaLibros(EntradaUsuario entrada) {
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

            /**
     * para crear un libro nuevo
     * @return libro con los datos
     */
    public Libro pedirDatosLibro() {
        //el codigo aquí es bastante optimo diría yo
        System.out.println("\n--- Nuevo libro ---");
        String titulo = entrada.leerTexto("Título: ");
        String categoria = entrada.leerTexto("Categoría: ");
        int paginas = entrada.leerEntero("Número de páginas: ", 1, MAX_PAGINAS);
        String escritor = entrada.leerTexto("Escritor: ");
        return new Libro(0, titulo, categoria, paginas, escritor);
    }

        public Libro pedirDatosModificacion(Libro actual) {
        System.out.println("\n--- Modificar libro (Enter para dejar el valor actual) ---");
        String titulo = entrada.leerTextoOpcional("Título", actual.getTitulo());
        String categoria = entrada.leerTextoOpcional("Categoría", actual.getCategoria());
        int paginas = entrada.leerEnteroOpcional("Páginas", actual.getNumPaginas(), 1, MAX_PAGINAS);
        String escritor = entrada.leerTextoOpcional("Escritor", actual.getEscritor());
        return new Libro(actual.getId(), titulo, categoria, paginas, escritor);
    }
        /**
         * Se PIDE AL usuario el id del libbro sobre el cual se va actuar 
         * @param accion CRUD
         * @return el id 
         */
        //se pide aquí la acción para reutilziarse código con muchos metodos diferentes y para la vista del usuario
        public int pedirId(String accion) {
        return entrada.leerEntero("ID del libro a " + accion + ": ", 1, Integer.MAX_VALUE);
        }
    /**
     * confirmación antes de borrar igual que vistapeli
     *
     * @param libro  libro que se borra
     * @return {@code true} si el usuario confirma
     */
    public boolean confirmarBorrado(Libro libro) {
        String r = entrada.leerTexto("¿Borrar \"" + libro.getTitulo() + "\"? (s/n): ");
        return r.equalsIgnoreCase("s");
    }
}