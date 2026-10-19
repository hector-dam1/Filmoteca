package Modelo;

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

public class BorrarRegistro {

    private static final String RUTA_XML = "Modelo/filmoteca.xml";
    public String errorMessage; // Almacena el mensaje de error en caso de fallo

    /**
     * Elimina un elemento del XML utilizando directamente los nombres de las etiquetas.
     * 
     * @param id            Identificador numérico del elemento a eliminar.
     * @param contenedorTag Nombre del nodo contenedor ("peliculas" o "libros").
     * @param elementoTag   Nombre del nodo del elemento ("pelicula" o "libro").
     * @return true si el registro fue encontrado y borrado con éxito, false en caso contrario.
     */
    public boolean borrarRegistro(int id, String contenedorTag, String elementoTag) {
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

            // Acceso directo al contenedor indicado (<peliculas> o <libros>)
            NodeList contenedorList = doc.getElementsByTagName(contenedorTag);
            if (contenedorList.getLength() == 0) {
                errorMessage = "No se encontró la sección <" + contenedorTag + "> en el archivo XML.";
                return false;
            }

            Element elemContenedor = (Element) contenedorList.item(0);
            NodeList listaElementos = elemContenedor.getElementsByTagName(elementoTag);

            // Búsqueda del nodo por atributo ID
            Node nodoAEliminar = null;
            for (int i = 0; i < listaElementos.getLength(); i++) {
                Node nodo = listaElementos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;
                    if (elem.getAttribute("id").equals(String.valueOf(id))) {
                        nodoAEliminar = nodo;
                        break;
                    }
                }
            }

            // Si el ID no se encuentra dentro del contenedor
            if (nodoAEliminar == null) {
                errorMessage = "No se encontró ninguna " + elementoTag + " con el ID " + id + ".";
                return false;
            }

            // Eliminación del nodo
            elemContenedor.removeChild(nodoAEliminar);

            // Guardar cambios en el archivo XML
            guardarXML(doc, archivo);
            return true;

        } catch (Exception e) {
            errorMessage = "Error al eliminar el registro del XML: " + e.getMessage();
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