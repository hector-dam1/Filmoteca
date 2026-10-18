package Modelo;

import Clases.Libro;

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
    public String errorMessage; // Variable para almacenar el mensaje de error
    /**
     * Lee únicamente los libros que se encuentran dentro del contenedor <libros> del XML,
     * crea los objetos Libro y los devuelve en una lista.
     * 
     * @return Lista de libros leídos desde el XML.
     */
    public List<Libro> lecturaLibros() {
        List<Libro> libros = new ArrayList<>();

        try {
            File archivo = new File(RUTA_XML);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();

            // Restricción: Localiza primero la sección contenedora <libros>
            NodeList contenedorLibros = doc.getElementsByTagName("libros");
            if (contenedorLibros.getLength() > 0) {
                Element elemLibros = (Element) contenedorLibros.item(0);
                NodeList listaLibros = elemLibros.getElementsByTagName("libro");

                // Itera sobre cada elemento <libro> dentro del contenedor <libros>
                for (int i = 0; i < listaLibros.getLength(); i++) {
                    Node nodo = listaLibros.item(i);

                    // Verifica que el nodo sea un elemento antes de procesarlo
                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemLibro = (Element) nodo;

                        // Extrae los atributos y elementos del libro
                        int id = Integer.parseInt(elemLibro.getAttribute("id"));
                        String titulo = getTextoEtiqueta(elemLibro, "titulo");
                        String categoria = getTextoEtiqueta(elemLibro, "categoria");
                        int numPaginas = Integer.parseInt(getTextoEtiqueta(elemLibro, "numPaginas"));
                        String escritor = getTextoEtiqueta(elemLibro, "escritor");

                        Libro libro = new Libro(id, titulo, categoria, numPaginas, escritor);
                        libros.add(libro);
                    }
                }
            }

        } catch (Exception e) {
            errorMessage = "Error al leer los libros del XML: " + e.getMessage();
        }

        return libros;
    }

    // Método auxiliar para obtener el texto de una etiqueta específica dentro de un elemento padre
    private String getTextoEtiqueta(Element elementoPadre, String nombreEtiqueta) {
        NodeList lista = elementoPadre.getElementsByTagName(nombreEtiqueta);
        if (lista.getLength() > 0 && lista.item(0) != null) {
            return lista.item(0).getTextContent().trim();
        }
        return "";
    }
}