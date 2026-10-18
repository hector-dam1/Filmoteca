package vista;

import java.util.List;

import Modelo.Pelicula;

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
     * @param EntradaUsuario entrada
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
     /**llama a leerEntero!!! */
     public int leerOpcion() {
        return entrada.leerEntero("Elige una opción: ", LISTAR, VOLVER);
    }
     public void mostrarPeliculas(List<Pelicula> peliculas) {
        System.out.println("--- Listado de películas ---");
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas guardadas.");
        } else {
            System.out.printf("%-4s %-28s %-28s %-9s %s%n",
                    "ID", "Título", "Categoría", "Minutos", "Director");
            for (Pelicula p : peliculas) {
                System.out.printf("%-4d %-28s %-28s %-9d %s%n",
                        p.getId(), p.getTitulo(), p.getCategoria(),
                        p.getDuracion(), p.getDirector());
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
        return new Pelicula(0, titulo, categoria, duracion, director);
    }

}