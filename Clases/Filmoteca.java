package Clases;

/*
 * Clase que representa la filmoteca.
 */

import java.util.ArrayList;
import java.util.List;

public class Filmoteca {

    private List<Pelicula> peliculas = new ArrayList<>();
    private List<Libro> libros = new ArrayList<>();

    public Filmoteca() {}

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}