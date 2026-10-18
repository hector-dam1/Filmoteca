package Filmoteca.Modelo;

import Filmoteca.Clases.Pelicula;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModificarPelicula {

    private static final String RUTA_XML = "Modelo/filmoteca.xml";
    public String errorMessage; // Almacena el mensaje de error en caso de fallo

    /**
     * Escribe o actualiza una película en el archivo XML.
     * Si el ID existe dentro de <peliculas>, sobrescribe el registro.
     * Si no existe, lo añade al final de <peliculas>.
     * 
     * @param pelicula Objeto de tipo Pelicula que se desea guardar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean modificarPelicula(Pelicula pelicula) {
        if (pelicula == null) {
            errorMessage = "La película proporcionada no puede ser nula.";
            return false;
        }

        try {
            File archivo = new File(RUTA_XML);
            if (!archivo.exists()) {
                errorMessage = "No se encontró el archivo XML en " + RUTA_XML;
                return false;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();

            // Localizar el contenedor <peliculas>
            NodeList contenedorPeliculas = doc.getElementsByTagName("peliculas");
            if (contenedorPeliculas.getLength() == 0) {
                errorMessage = "No se encontró la sección <peliculas> en el archivo XML.";
                return false;
            }

            Element elemPeliculas = (Element) contenedorPeliculas.item(0);
            NodeList listaPeliculas = elemPeliculas.getElementsByTagName("pelicula");

            // Buscar si ya existe una película con el mismo ID
            Element elementoExistente = null;
            for (int i = 0; i < listaPeliculas.getLength(); i++) {
                Node nodo = listaPeliculas.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;
                    if (elem.getAttribute("id").equals(String.valueOf(pelicula.getId()))) {
                        elementoExistente = elem;
                        break;
                    }
                }
            }

            // Construcción del nuevo elemento <pelicula id="...">
            Element nuevoElemPelicula = doc.createElement("pelicula");
            nuevoElemPelicula.setAttribute("id", String.valueOf(pelicula.getId()));

            Element elemTitulo = doc.createElement("titulo");
            elemTitulo.setTextContent(pelicula.getTitulo());
            nuevoElemPelicula.appendChild(elemTitulo);

            Element elemCategoria = doc.createElement("categoria");
            elemCategoria.setTextContent(pelicula.getCategoria());
            nuevoElemPelicula.appendChild(elemCategoria);

            Element elemDuracion = doc.createElement("duracion");
            elemDuracion.setAttribute("tipo", pelicula.getDuracionTipo() != null && !pelicula.getDuracionTipo().isEmpty() ? pelicula.getDuracionTipo() : "minutos");
            elemDuracion.setTextContent(String.valueOf(pelicula.getDuracionValor()));
            nuevoElemPelicula.appendChild(elemDuracion);

            Element elemDirector = doc.createElement("director");
            elemDirector.setTextContent(pelicula.getDirector());
            nuevoElemPelicula.appendChild(elemDirector);

            // Si existe, se reemplaza; si no, se añade al final
            if (elementoExistente != null) {
                elemPeliculas.replaceChild(nuevoElemPelicula, elementoExistente);
            } else {
                elemPeliculas.appendChild(nuevoElemPelicula);
            }

            // Guardar el documento XML actualizado en disco
            guardarXML(doc, archivo);
            return true;

        } catch (Exception e) {
            errorMessage = "Error al escribir la película en el XML: " + e.getMessage();
            return false;
        }
    }

    private void guardarXML(Document doc, File archivo) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(archivo);
        transformer.transform(source, result);
    }
}