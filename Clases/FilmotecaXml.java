package Clases;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "filmoteca")
public class FilmotecaXml {

    private List<PeliculaXml> peliculas = new ArrayList<>();
    private List<LibroXml> libros = new ArrayList<>();

    public FilmotecaXml() {}

    @XmlElementWrapper(name = "peliculas")
    @XmlElement(name = "pelicula")
    public List<PeliculaXml> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<PeliculaXml> peliculas) {
        this.peliculas = peliculas;
    }

    @XmlElementWrapper(name = "libros")
    @XmlElement(name = "libro")
    public List<LibroXml> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroXml> libros) {
        this.libros = libros;
    }
}