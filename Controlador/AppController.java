package Controlador;

import Clases.Filmoteca;
import Clases.Libro;
import Clases.Pelicula;
import Vista.ConsoleView;
import Vista.VistaLibros;
import Vista.VistaPeliculas;

import java.util.List;

/**
 * Controlador principal que conecta las Vistas con las clases 
 * controladoras especializadas (Lectura, Modificar, Borrar).
 */
public class AppController {

    private final ConsoleView vista = new ConsoleView();
    private final Lectura lecturaController = new Lectura();
    private final Modificar modificarController = new Modificar();
    private final Borrar borrarController = new Borrar();

    public AppController() {
        // Inicialización estándar
    }

    /** Bucle del menú principal: se repite hasta que el usuario elige salir. */
    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            vista.mostrarMenuPrincipal();
            switch (vista.leerOpcion()) {
                case ConsoleView.OPCION_LIBROS -> gestionarLibros();
                case ConsoleView.OPCION_PELICULAS -> gestionarPeliculas();
                case ConsoleView.OPCION_SALIR -> {
                    vista.mostrarMensaje("¡Hasta luego!");
                    salir = true;
                }
            }
        }
    }

    // ------------------------------------------------------------ LIBROS

    /** Submenú de libros. */
    private void gestionarLibros() {
        VistaLibros v = vista.getVistaLibros();
        boolean volver = false;
        while (!volver) {
            v.mostrarMenu();
            try {
                switch (v.leerOpcion()) {
                    case VistaLibros.LISTAR -> {
                        Filmoteca filmoteca = lecturaController.lecturaLibros();
                        v.mostrarLibros(filmoteca.getLibros());
                    }
                    case VistaLibros.ANADIR -> anadirLibro(v);
                    case VistaLibros.MODIFICAR -> modificarLibro(v);
                    case VistaLibros.BORRAR -> borrarLibro(v);
                    case VistaLibros.VOLVER -> volver = true;
                }
            } catch (IllegalStateException e) {
                vista.mostrarError(e.getMessage());
            }
        }
    }

    private void anadirLibro(VistaLibros v) {
        Libro nuevo = v.pedirDatosLibro();
        if (nuevo != null) {
            boolean exito = modificarController.modificarLibro(
                nuevo.getId(),
                nuevo.getTitulo(),
                nuevo.getCategoria(),
                nuevo.getNumPaginas(),
                nuevo.getEscritor()
            );
            if (exito) {
                vista.mostrarMensaje("Libro añadido con ID " + nuevo.getId());
            } else {
                vista.mostrarError(modificarController.errorMessage);
            }
        }
    }

    private void modificarLibro(VistaLibros v) {
        int id = v.pedirId("modificar");
        Libro actual = buscarLibro(id);
        if (actual == null) {
            vista.mostrarError("No existe ningún libro con ese ID.");
            return;
        }
        Libro modificado = v.pedirDatosModificacion(actual);
        if (modificado != null) {
            boolean exito = modificarController.modificarLibro(
                modificado.getId(),
                modificado.getTitulo(),
                modificado.getCategoria(),
                modificado.getNumPaginas(),
                modificado.getEscritor()
            );
            if (exito) {
                vista.mostrarMensaje("Libro modificado.");
            } else {
                vista.mostrarError(modificarController.errorMessage);
            }
        }
    }

    private void borrarLibro(VistaLibros v) {
        int id = v.pedirId("borrar");
        Libro actual = buscarLibro(id);
        if (actual == null) {
            vista.mostrarError("No existe ningún libro con ese ID.");
        } else if (v.confirmarBorrado(actual)) {
            boolean exito = borrarController.borrar(actual.getId(), "libro");
            if (exito) {
                vista.mostrarMensaje("Libro borrado.");
            } else {
                vista.mostrarError(borrarController.errorMessage);
            }
        } else {
            vista.mostrarMensaje("Operación cancelada.");
        }
    }

    private Libro buscarLibro(int id) {
        List<Libro> libros = lecturaController.lecturaLibros().getLibros();
        for (Libro l : libros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    // --------------------------------------------------------- PELÍCULAS

    /** Submenú de películas. */
    private void gestionarPeliculas() {
        VistaPeliculas v = vista.getVistaPeliculas();
        boolean volver = false;
        while (!volver) {
            v.mostrarMenu();
            try {
                switch (v.leerOpcion()) {
                    case VistaPeliculas.LISTAR -> {
                        Filmoteca filmoteca = lecturaController.lecturaPeliculas();
                        v.mostrarPeliculas(filmoteca.getPeliculas());
                    }
                    case VistaPeliculas.ANADIR -> anadirPelicula(v);
                    case VistaPeliculas.MODIFICAR -> modificarPelicula(v);
                    case VistaPeliculas.BORRAR -> borrarPelicula(v);
                    case VistaPeliculas.VOLVER -> volver = true;
                }
            } catch (IllegalStateException e) {
                vista.mostrarError(e.getMessage());
            }
        }
    }

    private void anadirPelicula(VistaPeliculas v) {
        Pelicula nueva = v.pedirDatosPelicula();
        if (nueva != null) {
            boolean exito = modificarController.modificarPelicula(
                nueva.getId(),
                nueva.getTitulo(),
                nueva.getCategoria(),
                nueva.getDuracionValor(),
                nueva.getDuracionTipo(),
                nueva.getDirector()
            );
            if (exito) {
                vista.mostrarMensaje("Película añadida con ID " + nueva.getId());
            } else {
                vista.mostrarError(modificarController.errorMessage);
            }
        }
    }

    private void modificarPelicula(VistaPeliculas v) {
        int id = v.pedirId("modificar");
        Pelicula actual = buscarPelicula(id);
        if (actual == null) {
            vista.mostrarError("No existe ninguna película con ese ID.");
            return;
        }
        Pelicula modificada = v.pedirDatosModificacion(actual);
        if (modificada != null) {
            boolean exito = modificarController.modificarPelicula(
                modificada.getId(),
                modificada.getTitulo(),
                modificada.getCategoria(),
                modificada.getDuracionValor(),
                modificada.getDuracionTipo(),
                modificada.getDirector()
            );
            if (exito) {
                vista.mostrarMensaje("Película modificada.");
            } else {
                vista.mostrarError(modificarController.errorMessage);
            }
        }
    }

    private void borrarPelicula(VistaPeliculas v) {
        int id = v.pedirId("borrar");
        Pelicula actual = buscarPelicula(id);
        if (actual == null) {
            vista.mostrarError("No existe ninguna película con ese ID.");
        } else if (v.confirmarBorrado(actual)) {
            boolean exito = borrarController.borrar(actual.getId(), "pelicula");
            if (exito) {
                vista.mostrarMensaje("Película borrada.");
            } else {
                vista.mostrarError(borrarController.errorMessage);
            }
        } else {
            vista.mostrarMensaje("Operación cancelada.");
        }
    }

    private Pelicula buscarPelicula(int id) {
        List<Pelicula> peliculas = lecturaController.lecturaPeliculas().getPeliculas();
        for (Pelicula p : peliculas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}