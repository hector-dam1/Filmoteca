package Vista;
//imprescindible aquí
import java.util.Scanner;

/**
 * Lectura por consola.
 * Al centrar un unico objeto Scanner sobre System.in, evitamos flujos de datos errónes, se centraliza la validación, se modulariza de forma inteligente el código.
 */
//me pareció buen diseño

public class EntradaUsuario{

    //nuestro único scanner de todo el programa
    //SI NO ES PRIVADO NO TIENE SENTIDO NINGUNO!!!!!
    private static final Scanner sc = new Scanner(System.in);

    /**pide texto; bucle; no pouede estar vacío
     * 
     * @param mensaje texto que se mostra al usuario
     * @return el texto introducido sin espacios, trimmed
     */

    //sólo hace unaa cosa: lee. Esto es ideal; puedo usarlo para infinitas cosas sin comprometer la estrucutra de mi código. Esto es óptimo
     public String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("  El valor no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }
       
    //el mismo principo de amtes aplicado a enteros además está más chulo porque al tener min y  aximos claramente definidos aquí, puedo añadir más opciones futuras a mi código   
     /**
     * Pide un entero que esté dentro de un rango determinado por el programador; es un bucle
     * @param mensaje a mostrarse al usuario
     * @param min valor minimo 
     * @param max valor maximo
     * @return en entero introducido
     */
    public int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("  Introduce un número entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  Eso no es un número válido.");
            }
        }
    }
          /**
     * Se pide texto para modificacion; Enter no altera nada;
     * @param mensaje nombre del campo - escritor, nombre pelicula, etc.
     * @param actual  valor actual
     * @return el texto nuevo 
     */
    public String leerTextoOpcional(String mensaje, String actual) {
        System.out.print(mensaje + " [" + actual + "]: ");
        String texto = sc.nextLine().trim(); // trim importantísimo siempre
        return texto.isEmpty() ? actual : texto;
    }
    
    /**
     * La misma lóica aquì se aplica a enteros
     * @fparam mensaje esl el campo
     * @param actual valor actual
     * @param min valor minimo
     * @param max es el vaor max 
     * @return el nuevo entero
     */
    public int leerEnteroOpcional(String mensaje, int actual, int min, int max) {
        while (true) {
            System.out.print(mensaje + " [" + actual + "]: ");
            String texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                return actual;
            }
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("  Introduce un número entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  Eso no es un número válido.");
            }
        }
    }
 /** Espera entrada de eneter por el usuario */
    public void esperarEnter() {
        System.out.print("\nPulsa Enter para continuar...");
        sc.nextLine();
    }   
    }
