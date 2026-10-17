package Filmoteca.Clases;

public class Libro {

    private int id;
    private String titulo;
    private String categoria;
    private int numPaginas;
    private String escritor;

    public Libro() {}

    public Libro(int id, String titulo, String categoria, int numPaginas, String escritor) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.numPaginas = numPaginas;
        this.escritor = escritor;
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

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    @Override
    public String toString() {
        return "Libro [ID=" + id + ", Título=" + titulo + ", Categoría=" + categoria +
               ", Páginas=" + numPaginas + ", Escritor=" + escritor + "]";
    }
}