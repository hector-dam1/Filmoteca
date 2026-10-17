package Filmoteca.Clases;

public class Pelicula {

    private int id;
    private String titulo;
    private String categoria;
    private int duracionValor;
    private String duracionTipo;
    private String director;

    public Pelicula() {}

    public Pelicula(int id, String titulo, String categoria, int duracionValor, String duracionTipo, String director) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.duracionValor = duracionValor;
        this.duracionTipo = duracionTipo;
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

    public int getDuracionValor() {
        return duracionValor;
    }

    public void setDuracionValor(int duracionValor) {
        this.duracionValor = duracionValor;
    }

    public String getDuracionTipo() {
        return duracionTipo;
    }

    public void setDuracionTipo(String duracionTipo) {
        this.duracionTipo = duracionTipo;
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
               ", Duración=" + duracionValor + " " + duracionTipo + ", Director=" + director + "]";
    }
}