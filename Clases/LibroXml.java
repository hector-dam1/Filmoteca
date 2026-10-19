package Clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "titulo", "categoria", "numPaginas", "escritor" })
public class LibroXml {

    private int id;
    private String titulo;
    private String categoria;
    private int numPaginas;
    private String escritor;

    public LibroXml() {}

    public LibroXml(Libro l) {
        if (l != null) {
            this.id = l.getId();
            this.titulo = l.getTitulo();
            this.categoria = l.getCategoria();
            this.numPaginas = l.getNumPaginas();
            this.escritor = l.getEscritor();
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

    @XmlElement(name = "numPaginas")
    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    @XmlElement(name = "escritor")
    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public Libro toLibro() {
        return new Libro(id, titulo, categoria, numPaginas, escritor);
    }
}