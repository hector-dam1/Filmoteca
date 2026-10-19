//main incorporado al develop

import Controlador.AppController;
/**
 Main. Crea el controlador y arranca el menú principal. Su lógica estará en appcontroller
 */
public class Main {
    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.iniciar();
    }
}