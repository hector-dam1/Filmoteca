package Filmoteca.Controlador;

import Filmoteca.Clases.Filmoteca;
import Filmoteca.Clases.Libro;
import Filmoteca.Clases.Pelicula;
import Filmoteca.Modelo.LecturaLibros;
import Filmoteca.Modelo.LecturaPeliculas;

import java.util.List;

public class LecturaCompleta {

    /**
     * Realiza las llamadas a lecturaPeliculas y lecturaLibros,
     * guardando todos los objetos leídos dentro del objeto Filmoteca.
     * 
     * @return Objeto Filmoteca cargado con todas las películas y libros.
     */
    public Filmoteca LecturaCompleta() {
        Filmoteca filmoteca = new Filmoteca();

        // Lectura y guardado de películas
        LecturaPeliculas lectorPeliculas = new LecturaPeliculas();
        List<Pelicula> peliculas = lectorPeliculas.lecturaPeliculas();
        filmoteca.setPeliculas(peliculas);

        // Lectura y guardado de libros
        LecturaLibros lectorLibros = new LecturaLibros();
        List<Libro> libros = lectorLibros.lecturaLibros();
        filmoteca.setLibros(libros);

        return filmoteca;
    }
}