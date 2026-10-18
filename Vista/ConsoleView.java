package Vista; 

public class ConsoleView{
    
    //opciones del menú principal
    //al ser constantes evitamos problemas con el controlador: muy sencillo
    //además por pura lógica deberían ser final
    public static final int OPCION_LIBROS = 1;
    public static final int OPCION_PELICULAS = 2;
    public static final int OPCION_SALIR = 3;

    //lo que se hace aquí: entrada es delegada a otra clase que se encarga de validaciones etc.; 
    private final EntradaUsuario entrada = new EntradaUsuario();
    //ambas vistas por separado
    private final VistaLibros vistaLibros;
    private final VistaPeliculas vistaPeliculas;
    
    //método muy importante -> objetos de las dos vistas creados a la vez con la entrada compartida
    /**
     * Constructor: instancias de ambas vistas son creadas con la entrada  de la clase EntradaUsuario establecida por defecto
     */
    public ConsoleView(){
        vistaLibros = new VistaLibros(entrada);
        vistaPeliculas = new VistaPeliculas(entrada);
    }
    /**
     * Se imprime por pantalla una interfaz primitiva con las opciones del usuario
     * @return opciones de servicios del programa
     */
    //nuestro menú principal
    public void mostrarMenuPrincipal(){
        System.out.println("---Filmoteca---");
        System.out.println("1.Libros");
        System.out.println("2.Peliculas");
        System.out.println("3.Salir");
    }
    /**
     * LLama al método leerEntero() del objeto EntradaUsuario entrada
     */
    public int leerOpcion(){
        return entrada.leerEntero("Elige una opción: ", OPCION_LIBROS, OPCION_SALIR);
    }
    /**Devuelve texto; interacciona con el controlador
     * @return String mensaje
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    /**La misma lógica pero para mensajes de error
     * @return String mesnaje
     */
    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }
    /**
     * Getter para el objeto de la clase VistaLibros
     * @return VistaLibro vistaLibros
     */
    public VistaLibros getVistaLibros() {
        return vistaLibros;
    }
    /**Lo mismo pero para pelis
     * @return VistaPeliculas vistaPeliculas
     */
    public VistaPeliculas getVistaPeliculas() {
        return vistaPeliculas;
    }
}

