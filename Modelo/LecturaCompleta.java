package Filmoteca.Modelo;

import Filmoteca.Clases.Filmoteca;
import Filmoteca.Clases.Libro;
import Filmoteca.Clases.Pelicula;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaCompleta {

    // Ruta directa al archivo filmoteca.xml dentro de la carpeta Modelo
    private static final String RUTA_XML = "Modelo/filmoteca.xml";

    /**
     * Método público que lee el XML directamente desde 'Modelo/filmoteca.xml',
     * crea los objetos Pelicula y Libro y los almacena en el objeto Filmoteca.
     * 
     * @return Objeto Filmoteca cargado con los datos del XML.
     */
    public Filmoteca LecturaCompleta() {
        Filmoteca filmoteca = new Filmoteca();

        try {
            File archivo = new File(RUTA_XML);

            // Verificación por si el programa se ejecuta desde dentro del paquete Modelo
            if (!archivo.exists()) {
                archivo = new File("filmoteca.xml");
            }

            if (!archivo.exists()) {
                System.err.println("No se encontró el archivo 'filmoteca.xml' en la carpeta Modelo.");
                return filmoteca;
            }

            // Análisis del archivo XML usando DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();

            // 1. LECTURA Y CREACIÓN DE PELÍCULAS
            NodeList listaPeliculas = doc.getElementsByTagName("pelicula");
            for (int i = 0; i < listaPeliculas.getLength(); i++) {
                Node nodo = listaPeliculas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemPelicula = (Element) nodo;

                    int id = Integer.parseInt(elemPelicula.getAttribute("id"));
                    String titulo = getTextoEtiqueta(elemPelicula, "titulo");
                    String categoria = getTextoEtiqueta(elemPelicula, "categoria");
                    String director = getTextoEtiqueta(elemPelicula, "director");

                    // Lectura de la etiqueta <duracion tipo="...">
                    int duracionValor = 0;
                    String duracionTipo = "";
                    NodeList nodosDuracion = elemPelicula.getElementsByTagName("duracion");
                    if (nodosDuracion.getLength() > 0) {
                        Element elemDuracion = (Element) nodosDuracion.item(0);
                        duracionValor = Integer.parseInt(elemDuracion.getTextContent().trim());
                        duracionTipo = elemDuracion.getAttribute("tipo");
                    }

                    // Creación del objeto Pelicula y guardado en Filmoteca
                    Pelicula pelicula = new Pelicula(id, titulo, categoria, duracionValor, duracionTipo, director);
                    filmoteca.getPeliculas().add(pelicula);
                }
            }

            // 2. LECTURA Y CREACIÓN DE LIBROS
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

                    // Duración para Libro (0 y cadena vacía en caso de no existir en el XML)
                    int duracionValor = 0;
                    String duracionTipo = "";
                    NodeList nodosDuracion = elemLibro.getElementsByTagName("duracion");
                    if (nodosDuracion.getLength() > 0) {
                        Element elemDuracion = (Element) nodosDuracion.item(0);
                        duracionValor = Integer.parseInt(elemDuracion.getTextContent().trim());
                        duracionTipo = elemDuracion.getAttribute("tipo");
                    }

                    // Creación del objeto Libro y guardado en Filmoteca
                    Libro libro = new Libro(id, titulo, categoria, numPaginas, duracionValor, duracionTipo, escritor);
                    filmoteca.getLibros().add(libro);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al realizar la lectura del XML: " + e.getMessage());
        }

        return filmoteca;
    }

    /**
     * Método auxiliar para obtener el texto interior de una etiqueta.
     */
    private String getTextoEtiqueta(Element elementoPadre, String nombreEtiqueta) {
        NodeList lista = elementoPadre.getElementsByTagName(nombreEtiqueta);
        if (lista.getLength() > 0 && lista.item(0) != null) {
            return lista.item(0).getTextContent().trim();
        }
        return "";
    }
}