package Clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "titulo", "categoria", "duracion", "director" })
public class PeliculaXml {

    private int id;
    private String titulo;
    private String categoria;
    private DuracionXml duracion;
    private String director;

    public PeliculaXml() {}

    public PeliculaXml(Pelicula p) {
        if (p != null) {
            this.id = p.getId();
            this.titulo = p.getTitulo();
            this.categoria = p.getCategoria();
            this.duracion = new DuracionXml(p.getDuracionValor(), p.getDuracionTipo());
            this.director = p.getDirector();
        }
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement(name = "categoria")
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @XmlElement(name = "duracion")
    public DuracionXml getDuracion() {
        return duracion;
    }

    public void setDuracion(DuracionXml duracion) {
        this.duracion = duracion;
    }

    @XmlElement(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Pelicula toPelicula() {
        int val = (duracion != null) ? duracion.getValor() : 0;
        String tip = (duracion != null) ? duracion.getTipo() : "";
        return new Pelicula(id, titulo, categoria, val, tip, director);
    }
}