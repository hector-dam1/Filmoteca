package Vista;

import java.util.List;

import Clases.Pelicula;
/**
 * Vista de las pelicuas con subenu. listado, etc.
 */
public class VistaPeliculas{
    /** Opciones del submenú de películas. */
   // staticos y constantes para facilitar el trabajo del conotrolador

    public static final int LISTAR = 1;
    public static final int ANADIR = 2;
    public static final int MODIFICAR = 3;
    public static final int BORRAR = 4;
    public static final int VOLVER = 5;

    private static final int MAX_MINUTOS = 1000;

    //nuestra importantísima entrada
    private final EntradaUsuario entrada;

     /**
     * @param entrada
     */
    public VistaPeliculas(EntradaUsuario entrada) {
        this.entrada = entrada;
    }
      /** Menú pelis. */
    public void mostrarMenu() {
        System.out.println("\n--- PELÍCULAS ---");
        System.out.println("1. Ver películas");
        System.out.println("2. Añadir película");
        System.out.println("3. Modificar película");
        System.out.println("4. Borrar película");
        System.out.println("5. Volver");
    }
     /**lee la opcion del menu */
     public int leerOpcion() {
        return entrada.leerEntero("Elige una opción: ", LISTAR, VOLVER);
    }

    /**
     * Muestra las pelis en una tabla
     */
     public void mostrarPeliculas(List<Pelicula> peliculas) {
        System.out.println("\n--- Listado de películas ---");
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas guardadas.");
        } else {
            System.out.printf("%-4s %-28s %-28s %-9s %s%n",
                    "ID", "Título", "Categoría", "Minutos", "Director");
            for (Pelicula p : peliculas) {
                System.out.printf("%-4d %-28s %-28s %-9d %s%n",
                        p.getId(), p.getTitulo(), p.getCategoria(),
                        p.getDuracionValor(), p.getDirector());
            }
        }
        entrada.esperarEnter();
    }
     /**
     * Para una peli nueva; el id queda a 0 porque lo gestiona el controlador
     *
     * @return película con los datos introducidos
     */
    public Pelicula pedirDatosPelicula() {
        System.out.println("\n--- Nueva película ---");
        String titulo = entrada.leerTexto("Título: ");
        String categoria = entrada.leerTexto("Categoría: ");
        int duracion = entrada.leerEntero("Duración (minutos): ", 1, MAX_MINUTOS);
        String director = entrada.leerTexto("Director: ");
        return new Pelicula(0, titulo, categoria, duracion, "minutos", director);
    }
    /**
     * Para modificacion; eneter no altera nada
     * 
     * @param actual la peli sin alterarla
     * @return pelicula con datos alterados y nuevo id
     */
      public Pelicula pedirDatosModificacion(Pelicula actual) {
        System.out.println("\n--- Modificar película (Enter para dejar el valor actual) ---");
        String titulo = entrada.leerTextoOpcional("Título", actual.getTitulo());
        String categoria = entrada.leerTextoOpcional("Categoría", actual.getCategoria());
        int duracion = entrada.leerEnteroOpcional("Duración (minutos)", actual.getDuracionValor(), 1, MAX_MINUTOS);
        String director = entrada.leerTextoOpcional("Director", actual.getDirector());
        return new Pelicula(actual.getId(), titulo, categoria, duracion, actual.getDuracionTipo(), director);
    }
    /**
     * Pide el id de la  peli q se trabaja
     */
    public int pedirId(String accion) {
        return entrada.leerEntero("ID de la película a " + accion + ": ", 1, Integer.MAX_VALUE);
    }

    /**confirmacion ates de elminar una pelicula
     * @pelicula que se elimina 
     * @retunr {@code true} si el usuario
     */
     public boolean confirmarBorrado(Pelicula pelicula) {
        String r = entrada.leerTexto("¿Borrar \"" + pelicula.getTitulo() + "\"? (s/n): ");
        return r.equalsIgnoreCase("s");
    }


    


}