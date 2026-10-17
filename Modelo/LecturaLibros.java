package Filmoteca.Modelo;

import Filmoteca.Clases.Libro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaLibros {

    private static final String RUTA_XML = "Modelo/filmoteca.xml";

    /**
     * Lee los libros del XML, crea los objetos Libro y los devuelve en una lista.
     * 
     * @return Lista de libros leídos desde el XML.
     */
    public List<Libro> lecturaLibros() {
        List<Libro> libros = new ArrayList<>();

        try {
            File archivo = new File(RUTA_XML);

            if (!archivo.exists()) {
                archivo = new File("filmoteca.xml");
                System.err.println("No se encontró el archivo 'filmoteca.xml' para leer libros, se ha creado el archivo filmoteca.xml.");
                return libros;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node nodo = listaLibros.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemLibro = (Element) nodo;

                    int id = Integer.parseInt(elemLibro.getAttribute("id"));
                    String titulo = getTextoEtiqueta(elemLibro, "titulo");
                    String categoria = getTextoEtiqueta(elemLibro, "categoria");
                    int numPaginas = Integer.parseInt(getTextoEtiqueta(elemLibro, "numPaginas"));
                    String escritor = getTextoEtiqueta(elemLibro, "escritor");

                    int duracionValor = 0;
                    String duracionTipo = "";
                    NodeList nodosDuracion = elemLibro.getElementsByTagName("duracion");
                    if (nodosDuracion.getLength() > 0) {
                        Element elemDuracion = (Element) nodosDuracion.item(0);
                        duracionValor = Integer.parseInt(elemDuracion.getTextContent().trim());
                        duracionTipo = elemDuracion.getAttribute("tipo");
                    }

                    Libro libro = new Libro(id, titulo, categoria, numPaginas, duracionValor, duracionTipo, escritor);
                    libros.add(libro);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al leer los libros del XML: " + e.getMessage());
        }

        return libros;
    }

    private String getTextoEtiqueta(Element elementoPadre, String nombreEtiqueta) {
        NodeList lista = elementoPadre.getElementsByTagName(nombreEtiqueta);
        if (lista.getLength() > 0 && lista.item(0) != null) {
            return lista.item(0).getTextContent().trim();
        }
        return "";
    }
}