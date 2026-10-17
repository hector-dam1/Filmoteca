package Filmoteca.Modelo;

import Filmoteca.Clases.Pelicula;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaPeliculas {

    private static final String RUTA_XML = "Modelo/filmoteca.xml";

    /**
     * Lee las películas del XML, crea los objetos Pelicula y los devuelve en una lista.
     * 
     * @return Lista de películas leídas desde el XML.
     */
    public List<Pelicula> lecturaPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();

        try {
            File archivo = new File(RUTA_XML);

            if (!archivo.exists()) {
                archivo = new File("filmoteca.xml");
                System.err.println("No se encontró el archivo 'filmoteca.xml' para leer películas, se ha creado el archivo filmoteca.xml.");
                return peliculas;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList listaPeliculas = doc.getElementsByTagName("pelicula");
            for (int i = 0; i < listaPeliculas.getLength(); i++) {
                Node nodo = listaPeliculas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemPelicula = (Element) nodo;

                    int id = Integer.parseInt(elemPelicula.getAttribute("id"));
                    String titulo = getTextoEtiqueta(elemPelicula, "titulo");
                    String categoria = getTextoEtiqueta(elemPelicula, "categoria");
                    String director = getTextoEtiqueta(elemPelicula, "director");

                    int duracionValor = 0;
                    String duracionTipo = "";
                    NodeList nodosDuracion = elemPelicula.getElementsByTagName("duracion");
                    if (nodosDuracion.getLength() > 0) {
                        Element elemDuracion = (Element) nodosDuracion.item(0);
                        duracionValor = Integer.parseInt(elemDuracion.getTextContent().trim());
                        duracionTipo = elemDuracion.getAttribute("tipo");
                    }

                    Pelicula pelicula = new Pelicula(id, titulo, categoria, duracionValor, duracionTipo, director);
                    peliculas.add(pelicula);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al leer las películas del XML: " + e.getMessage());
        }

        return peliculas;
    }

    private String getTextoEtiqueta(Element elementoPadre, String nombreEtiqueta) {
        NodeList lista = elementoPadre.getElementsByTagName(nombreEtiqueta);
        if (lista.getLength() > 0 && lista.item(0) != null) {
            return lista.item(0).getTextContent().trim();
        }
        return "";
    }
}