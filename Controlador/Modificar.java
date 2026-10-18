package Filmoteca.Controlador;

import Filmoteca.Clases.Libro;
import Filmoteca.Clases.Pelicula;
import Filmoteca.Modelo.ModificarLibro;
import Filmoteca.Modelo.ModificarPelicula;

public class Modificar {

    public String errorMessage; // Almacena el mensaje de error en caso de fallo

    /**
     * Recibe los datos necesarios de un libro, instancia el objeto Libro 
     * y lo envía a la clase EscribirLibro para guardarlo o actualizarlo en el XML.
     * 
     * @param id         Identificador del libro.
     * @param titulo     Título del libro.
     * @param categoria  Categoría o género literario.
     * @param numPaginas Número de páginas.
     * @param escritor   Nombre del autor/escritor.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean modificarLibro(int id, String titulo, String categoria, int numPaginas, String escritor) {
        Libro nuevoLibro = new Libro(id, titulo, categoria, numPaginas, escritor);
        ModificarLibro modeloEscribir = new ModificarLibro();
        boolean resultado = modeloEscribir.modificarLibro(nuevoLibro);

        if (!resultado) {
            this.errorMessage = modeloEscribir.errorMessage;
        }

        return resultado;
    }

    /**
     * Recibe los datos necesarios de una película, instancia el objeto Pelicula 
     * y lo envía a la clase EscribirPelicula para guardarla o actualizarla en el XML.
     * 
     * @param id            Identificador de la película.
     * @param titulo        Título de la película.
     * @param categoria     Categoría o género cinematográfico.
     * @param duracionValor Valor numérico de la duración.
     * @param duracionTipo  Unidad de duración (por ejemplo, "minutos").
     * @param director      Nombre del director.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean modificarPelicula(int id, String titulo, String categoria, int duracionValor, String duracionTipo, String director) {
        Pelicula nuevaPelicula = new Pelicula(id, titulo, categoria, duracionValor, duracionTipo, director);
        ModificarPelicula modeloEscribir = new ModificarPelicula();
        boolean resultado = modeloEscribir.modificarPelicula(nuevaPelicula);

        if (!resultado) {
            this.errorMessage = modeloEscribir.errorMessage;
        }

        return resultado;
    }
}