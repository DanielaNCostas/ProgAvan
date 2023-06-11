package main.java.tp.usuarios;

import main.java.tp.Biblioteca;

class Administrador extends Usuario {

    public Administrador(String nombreAdministrador, String emailAdministrador) {
        super(nombreAdministrador, emailAdministrador);
        this.nombre = nombreAdministrador;
        this.email = emailAdministrador;
    }

    //hereda metodos del usuario y además se agrega uno propio para actualizar el catálogo de libros
    private void actualizarCatalogo() {
        System.out.println("Por favor ingrese una de las siguientes opciones:");
        System.out.println();
        System.out.println("A + Enter: si usted quiere crear un nuevo libro");
        System.out.println("B + Enter: si usted quiere borrar un libro existente");
        System.out.println();
        System.out.println("Si desea salir ingrese 0 + Enter");
        System.out.println("--");
        String userInput = waitForUserInput();
        if (EXIT_VALUE.equalsIgnoreCase(userInput)) {
            printSaludo();
        } else if ("A".equalsIgnoreCase(userInput)) {
            System.out.println("Ingrese el nombre del libro");
            String nombreLibro =  waitForUserInput();
            System.out.println("Ingrese el autor del libro");
            String autorLibro =  waitForUserInput();
            Biblioteca.Libro libro = new Biblioteca.Libro(nombreLibro, autorLibro);
            libro.declararCantidad();
            System.out.println("Se ha agregado " + nombreLibro + " al catálogo de la biblioteca");
            libro.getCantidad();
            //esta opcion permite crear un nuevo libro y declarar la cantidad de ejemplares disponibles
        } else if ("B".equalsIgnoreCase(userInput)) {
            //Acá hay que agregar una manera de borrar libros. No se me ocurre como "llamar un libro" sin tener un catálogo de libros hechos
        } else {
            printOpcionInvalida();
        }
        waitForUserInput();
    }

    private String ejecutarOperacion2() {
        printMenuAdministrador();
        String userInput = waitForUserInput();
        ejecutarOpcionAdministrador(userInput);
        //acá se imprime el menu del administrador, se pide un input por parte del administrador que navega la pagina y deriva al método que ejecuta la opcion ingresada
        return userInput;
    }
    private void ejecutarOpcionAdministrador(String userInput) {
        //aca se ejecutan las opciones que puede elegir el administrador en su menu
        if (EXIT_VALUE.equalsIgnoreCase(userInput)) {
            return;
        } else if ("A".equalsIgnoreCase(userInput)) {
            Administrador administrador = new Administrador("Admin123", "admin@gmail.com");
            administrador.getNombre();
            //si aprieta A devuelve su nombre
        } else if ("B".equalsIgnoreCase(userInput)) {
            Administrador administrador = new Administrador("Admin123", "admin@gmail.com");
            administrador.getEmail();
            //si aprieta A devuelve su email
        } else if ("C".equalsIgnoreCase(userInput)) {
            //si aprieta C deriva al método actualizar catálogo
            Administrador administrador = new Administrador("Admin123", "admin@gmail.com");
            while (shouldExecuteAnotherOperation(userInput)) {
                administrador.actualizarCatalogo();
            }
            printSaludo();
        } else {
            printOpcionInvalida();
        }
        waitForUserInput();
    }
}
