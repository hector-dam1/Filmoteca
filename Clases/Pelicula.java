package Filmoteca.Clases;

public class Pelicula {

    private int id;
    private String titulo;
    private String categoria;
    private Duracion duracion;
    private String director;

    public Pelicula() {}

    public Pelicula(int id, String titulo, String categoria, Duracion duracion, String director) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.duracion = duracion;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Duracion getDuracion() {
        return duracion;
    }

    public void setDuracion(Duracion duracion) {
        this.duracion = duracion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Película [ID=" + id + ", Título=" + titulo + ", Categoría=" + categoria +
               ", Duración=" + duracion + ", Director=" + director + "]";
    }
}