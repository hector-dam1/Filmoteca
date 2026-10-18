package Controlador;

import Clases.Filmoteca;
import Clases.Libro;
import Clases.Pelicula;
import Modelo.LecturaLibros;
import Modelo.LecturaPeliculas;

import java.util.List;

public class Lectura {

    /**
     * Realiza las llamadas a lecturaPeliculas y lecturaLibros,
     * guardando todos los objetos leídos dentro del objeto Filmoteca.
     * 
     * @return Objeto Filmoteca cargado con todas las películas y libros.
     */
    public Filmoteca lecturaLibros() {
        Filmoteca filmoteca = new Filmoteca();

        // Lectura y guardado de libros
        LecturaLibros lectorLibros = new LecturaLibros();
        List<Libro> libros = lectorLibros.lecturaLibros();
        filmoteca.setLibros(libros);

        return filmoteca;
    }

    public Filmoteca lecturaPeliculas(){
        Filmoteca filmoteca = new Filmoteca();

        // Lectura y guardado de películas
        LecturaPeliculas lectorPeliculas = new LecturaPeliculas();
        List<Pelicula> peliculas = lectorPeliculas.lecturaPeliculas();
        filmoteca.setPeliculas(peliculas);
        
        return filmoteca;
    }
}