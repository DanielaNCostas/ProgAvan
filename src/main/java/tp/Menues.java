package main.java.tp;

import main.java.tp.usuarios.Administrador;
import main.java.tp.usuarios.Cliente;
import java.util.Scanner;

public class Menues {

    public static void main(String[] args) {
        String userInput = "";
        while (shouldExecuteAnotherOperation(userInput)) {
            userInput = ejecutarNuevaOperacion();
            //este método inicia los menues interactivos por consola
        }
        printSaludo();
    }

    public static final String EXIT_VALUE = "0";// defino un valor de salida para los menues

    private static boolean shouldExecuteAnotherOperation(String userInput) {
        return !EXIT_VALUE.equals(userInput);
    }

    public static String ejecutarNuevaOperacion() {
        printMenuPrincipal();
        String userInput = waitForUserInput();
        ejecutarOpcion(userInput);
        //este método imprime el primer menu, pide un input y llama al método que ejecuta la opción ingresada por input
        return userInput;
    }

    public static void printMenuPrincipal() {
        System.out.println("--");
        System.out.println("Bienvenido a la biblioteca.");
        System.out.println("Por favor ingrese una de las siguientes opciones:");
        System.out.println();
        System.out.println("A + Enter: si usted es administrador");
        System.out.println("B + Enter: si usted es cliente de la biblioteca");
        System.out.println();
        System.out.println("Si desea salir ingrese 0 + Enter");
        System.out.println("--");
        //este es el menu principal
    }

    public static void printMenuAdministrador() {
        System.out.println("--");
        System.out.println("Bienvenido administrador");
        System.out.println("Por favor ingrese una de las siguientes opciones:");
        System.out.println();
        System.out.println("A + Enter: para ver su nombre de usuario");
        System.out.println("B + Enter: para ver su email asociado");
        System.out.println("C + Enter: para actualizar el catálogo");
        System.out.println();
        System.out.println("Si desea salir ingrese 0 + Enter");
        System.out.println("--");
    }

    public static void printMenuCliente() {
        System.out.println("--");
        System.out.println("Bienvenido cliente");
        System.out.println("Por favor ingrese una de las siguientes opciones:");
        System.out.println();
        System.out.println("A + Enter: para ver su nombre de usuario");
        System.out.println("B + Enter: para ver su email asociado");
        System.out.println("C + Enter: para ver su historial de reservas");
        System.out.println("D + Enter: para reservar un libro");
        System.out.println("E + Enter: para devolver el último libro reservado");
        System.out.println("F + Enter: para calificar el último libro reservado");
        System.out.println("G + Enter: para ver calificaciones de un libro");
        System.out.println("H + Enter: para ver nuestros títulos disponibles");
        System.out.println();
        System.out.println("Si desea salir ingrese 0 + Enter");
        System.out.println("--");
    }

    public static String waitForUserInput() {
        return new Scanner(System.in).nextLine();
    }

    private static void ejecutarOpcion(String userInput) {
        //este método ejecuta la opcion ingresada por el usuario en el menu principal
        if (EXIT_VALUE.equalsIgnoreCase(userInput)) {
            return;
        } else if ("A".equalsIgnoreCase(userInput)) {
            Administrador administrador = new Administrador("Admin123", "admin@gmail.com");
            while (shouldExecuteAnotherOperation(userInput)) {
                userInput = administrador.ejecutarOperacion2();
            }
            printSaludo();
            //si el usuario ingresó A deriva al menu del administrador
        } else if ("B".equalsIgnoreCase(userInput)) {
            Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
            while (shouldExecuteAnotherOperation(userInput)) {
                userInput = cliente.ejecutarOpcion3();
            }
            printSaludo();
            //si el usuario ingresó B deriva al menu del cliente
        } else {
            printOpcionInvalida();
        }
        waitForUserInput();
    }

    public static void printOpcionInvalida() {
        System.out.println("--");
        System.out.println("La opción ingresada es incorrecta");
        System.out.println("--");
        System.out.println("Presione cualquier tecla para volver al menú.");
        //esto es por si ingresan un valor no definido en el menu
    }

    public static void printSaludo() {
        System.out.println("--");
        System.out.println("Gracias por usar la página de esta biblioteca. Vuelva pronto");
        System.out.println("--");
        //al principio se definio 0 como valor de salida, por lo que, al apretar ese valor se sale de la pagina de la biblioteca
    }
}

