package vista;

import java.util.List;

import modelo.Pelicula;

/**
 * Vista de peliculas, menu y formularios por pantalla

 */
public class VistaLibros{

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



}