package main.java.tp;

import main.java.tp.usuarios.Administrador;
import main.java.tp.usuarios.Cliente;

import java.util.Scanner;

public class Menues {

    private final static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        String userInput = "";
        while (ejecutarOtraOperacion(userInput)) {
            userInput = ejecutarNuevoMenu();
            //este método inicia los menues interactivos por consola
        }
        printSaludo();
    }

    public static String waitForUserInput() {
        return new Scanner(System.in).nextLine();
    }

    public static final String EXIT_VALUE = "0";// defino un valor de salida para los menues

    public static boolean ejecutarOtraOperacion(String userInput) {
        return !EXIT_VALUE.equals(userInput);
        //Si el input es distinto de 0 (valor de salida) ejecuta las operaciones siguientes
    }

    public static String ejecutarNuevoMenu() {
        printMenuPrincipal();
        String userInput = waitForUserInput();
        ejecutarOpcionMenuPrincipal(userInput);
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

    public static void ejecutarOpcionMenuPrincipal(String userInput) {
        //este método ejecuta la opcion ingresada por el usuario en el menu principal
        if (EXIT_VALUE.equalsIgnoreCase(userInput)) {
            return;
        } else if ("A".equalsIgnoreCase(userInput)) {
            while (ejecutarOtraOperacion(userInput)){
                ejecutarOperacionesDelAdministrador();
            }
            //si el usuario ingresó A deriva al menu del administrador
        } else if ("B".equalsIgnoreCase(userInput)) {
            Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
            while (ejecutarOtraOperacion(userInput)){
                cliente.ejecutarOperacionesDelCliente(biblioteca);
            }
            //si el usuario ingresó B deriva al menu del cliente
        } else {
            printOpcionInvalida();
        }
        waitForUserInput();
    }

    public static String ejecutarOperacionesDelAdministrador() {
        Administrador administrador = new Administrador("Admin123", "admin@gmail.com");
        printMenuAdministrador();
        String userInput = waitForUserInput();
        ejecutarOpcionAdministrador(userInput,administrador);
        //acá se imprime el menu del administrador, se pide un input por parte del administrador que navega la pagina y deriva al método que ejecuta la opcion ingresada
        return userInput;
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

    public static void ejecutarOpcionAdministrador(String userInput, Administrador administrador) {
        //aca se ejecutan las opciones que puede elegir el administrador en su menu
        if (EXIT_VALUE.equalsIgnoreCase(userInput)) {
            printSaludo();
            System.exit(0);
            return;
        } else if ("A".equalsIgnoreCase(userInput)) {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Usted es " + administrador.getNombre());
            //si aprieta A devuelve su nombre
        } else if ("B".equalsIgnoreCase(userInput)) {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Su email es " + administrador.getEmail());
            //si aprieta B devuelve su email
        } else if ("C".equalsIgnoreCase(userInput)) {
            while (ejecutarOtraOperacion(userInput)){
                ejecutarActualizarCatalogo(administrador);
                waitForUserInput();
            }
        } else {
            printOpcionInvalida();
        }
        waitForUserInput();
    }

    public static String ejecutarActualizarCatalogo(Administrador administrador) {
        menuActualizarCatalogo();
        String userInput = waitForUserInput();
        ejecutarOpcionActualizarCatalogo(userInput,administrador);
        //acá se imprime el menu del administrador, se pide un input por parte del administrador que navega la pagina y deriva al método que ejecuta la opcion ingresada
        return userInput;
    }

    public static void menuActualizarCatalogo() {
        System.out.println("Por favor ingrese una de las siguientes opciones:");
        System.out.println();
        System.out.println("A + Enter: si usted quiere crear un nuevo libro");
        System.out.println("B + Enter: si usted quiere borrar un libro existente");
        System.out.println("C + Enter: para ver nuestros títulos disponibles");
        System.out.println();
        System.out.println("Si desea salir ingrese 0 + Enter");
        System.out.println("--");
    }

    public static void ejecutarOpcionActualizarCatalogo(String userInput, Administrador administrador){
        if (EXIT_VALUE.equalsIgnoreCase(userInput)) {
            printSaludo();
            System.exit(0);
            return;
        } else if ("A".equalsIgnoreCase(userInput)) {
            System.out.println("Ingrese el nombre del libro");
            String nombreLibro =  waitForUserInput();
            System.out.println("Ingrese el autor del libro");
            String autorLibro =  waitForUserInput();
            System.out.println("Ingrese la cantidad de ejemplares disponibles");
            String ejemplaresString = waitForUserInput();
            int ejemplaresInteger = Integer.parseInt(ejemplaresString);
            administrador.agregarLibro(nombreLibro, autorLibro, ejemplaresInteger, biblioteca);
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Se ha agregado " + nombreLibro + " al catálogo de la biblioteca");
            //esta opcion permite crear un nuevo libro y declarar la cantidad de ejemplares disponibles
        } else if ("B".equalsIgnoreCase(userInput)) {
            //Acá se permite al administrador eliminar uno de los libros del catálogo. El libro se borra de los titulos disponibles (no se muestra mas cuando pidan los titulos disponibles) y de el objeto "libro" de la lista de libros disponibles
            System.out.println("Ingrese el nombre del libro que quiere eliminar");
            String tituloBuscado = waitForUserInput();
            administrador.quitarLibro(tituloBuscado, biblioteca);
            System.out.println("Se ha quitado el libro del catálogo");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
        } else if ("C".equalsIgnoreCase(userInput)) {
            //esta opcion permite ver los titulos que forman parte del catálogo de la biblioteca
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            biblioteca.verTitulosDisponibles();
        } else {
            printOpcionInvalida();
        }
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

