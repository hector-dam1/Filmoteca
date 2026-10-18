package Filmoteca.Modelo;

import Filmoteca.Clases.Libro;

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

public class EscribirLibro {

    private static final String RUTA_XML = "Modelo/filmoteca.xml";
    public String errorMessage; // Almacena el mensaje de error en caso de fallo

    /**
     * Escribe o actualiza un libro en el archivo XML.
     * Si el ID existe dentro de <libros>, sobrescribe el registro.
     * Si no existe, lo añade al final de <libros>.
     * 
     * @param libro Objeto de tipo Libro que se desea guardar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean escribirLibro(Libro libro) {
        if (libro == null) {
            errorMessage = "El libro proporcionado no puede ser nulo.";
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

            // Localizar el contenedor <libros>
            NodeList contenedorLibros = doc.getElementsByTagName("libros");
            if (contenedorLibros.getLength() == 0) {
                errorMessage = "No se encontró la sección <libros> en el archivo XML.";
                return false;
            }

            Element elemLibros = (Element) contenedorLibros.item(0);
            NodeList listaLibros = elemLibros.getElementsByTagName("libro");

            // Buscar si ya existe un libro con el mismo ID
            Element elementoExistente = null;
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node nodo = listaLibros.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;
                    if (elem.getAttribute("id").equals(String.valueOf(libro.getId()))) {
                        elementoExistente = elem;
                        break;
                    }
                }
            }

            // Construcción del nuevo elemento <libro id="...">
            Element nuevoElemLibro = doc.createElement("libro");
            nuevoElemLibro.setAttribute("id", String.valueOf(libro.getId()));

            Element elemTitulo = doc.createElement("titulo");
            elemTitulo.setTextContent(libro.getTitulo());
            nuevoElemLibro.appendChild(elemTitulo);

            Element elemCategoria = doc.createElement("categoria");
            elemCategoria.setTextContent(libro.getCategoria());
            nuevoElemLibro.appendChild(elemCategoria);

            Element elemPaginas = doc.createElement("numPaginas");
            elemPaginas.setTextContent(String.valueOf(libro.getNumPaginas()));
            nuevoElemLibro.appendChild(elemPaginas);

            Element elemEscritor = doc.createElement("escritor");
            elemEscritor.setTextContent(libro.getEscritor());
            nuevoElemLibro.appendChild(elemEscritor);

            // Si existe, se reemplaza; si no, se añade al final
            if (elementoExistente != null) {
                elemLibros.replaceChild(nuevoElemLibro, elementoExistente);
            } else {
                elemLibros.appendChild(nuevoElemLibro);
            }

            // Guardar el documento XML actualizado en disco
            guardarXML(doc, archivo);
            return true;

        } catch (Exception e) {
            errorMessage = "Error al escribir el libro en el XML: " + e.getMessage();
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