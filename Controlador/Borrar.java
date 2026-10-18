package Filmoteca.Controlador;

import Filmoteca.Modelo.BorrarRegistro;

public class Borrar {

    private int id;
    private String tipo;
    public String errorMessage; // Mensaje de error para la vista o llamadas posteriores

    /**
     * Constructor del controlador que solicita el ID y el tipo de elemento a borrar.
     * 
     * @param id   Identificador numérico del elemento.
     * @param tipo Cadena que indica el tipo ("pelicula" o "libro").
     */
    public boolean borrar(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;

    /**
     * Realiza las comprobaciones de tipo, determina las etiquetas objetivo y llama
     * a la capa de modelo para borrar el registro del XML.
     * 
     * @return true si se borró con éxito, false en caso de error o validación fallida.
     */
        // Validación del parámetro tipo
        if (tipo == null || tipo.trim().isEmpty()) {
            errorMessage = "Debe especificar un tipo válido: 'pelicula' o 'libro'.";
            return false;
        }

        String tipoLimpio = tipo.trim().toLowerCase();
        String contenedorTag;
        String elementoTag;

        // Determinación de las etiquetas objetivo según la selección
        if (tipoLimpio.equals("pelicula") || tipoLimpio.equals("película")) {
            contenedorTag = "peliculas";
            elementoTag = "pelicula";
        } else if (tipoLimpio.equals("libro")) {
            contenedorTag = "libros";
            elementoTag = "libro";
        } else {
            errorMessage = "Tipo no reconocido. Indique 'pelicula' o 'libro'.";
            return false;
        }

        // Llamada a la capa del Modelo para realizar la eliminación en el XML
        BorrarRegistro modeloBorrar = new BorrarRegistro();
        boolean resultado = modeloBorrar.borrarRegistro(this.id, contenedorTag, elementoTag);

        if (!resultado) {
            this.errorMessage = modeloBorrar.errorMessage;
        }

        return resultado;
    }
}