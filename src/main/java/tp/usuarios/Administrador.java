package main.java.tp.usuarios;

import main.java.tp.Biblioteca;
import main.java.tp.Libro;
import main.java.tp.Menues;

public class Administrador extends Usuario {

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
        System.out.println("C + Enter: para ver nuestros títulos disponibles");
        System.out.println();
        System.out.println("Si desea salir ingrese 0 + Enter");
        System.out.println("--");
        String userInput = Menues.waitForUserInput();
        if (Menues.EXIT_VALUE.equalsIgnoreCase(userInput)) {
            Menues.printSaludo();
        } else if ("A".equalsIgnoreCase(userInput)) {
            System.out.println("Ingrese el nombre del libro");
            String nombreLibro =  Menues.waitForUserInput();
            System.out.println("Ingrese el autor del libro");
            String autorLibro =  Menues.waitForUserInput();
            Libro libro = new Libro(nombreLibro, autorLibro);
            System.out.println("Ingrese la cantidad de ejemplares disponibles");
            String ejemplaresString = Menues.waitForUserInput();
            int ejemplaresInteger = Integer.parseInt(ejemplaresString);
            Biblioteca.agregarLibroAlCatalogo(libro,ejemplaresInteger);
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Se ha agregado " + nombreLibro + " al catálogo de la biblioteca");
            //esta opcion permite crear un nuevo libro y declarar la cantidad de ejemplares disponibles
        } else if ("B".equalsIgnoreCase(userInput)) {
            //Acá hay que agregar una manera de borrar libros. No se me ocurre como "llamar un libro" sin tener un catálogo de libros hechos
            System.out.println("Ingrese el nombre del libro que quiere eliminar");
            String tituloBuscado = Menues.waitForUserInput();
            Biblioteca.quitarLibroDelCatalogo(tituloBuscado);
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
        } else if ("C".equalsIgnoreCase(userInput)) {
            //esta opcion permite ver los titulos que forman parte del catálogo de la biblioteca
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            Biblioteca.getTitulosDisponibles();
        } else {
            Menues.printOpcionInvalida();
        }
        Menues.waitForUserInput();
    }

    public String ejecutarOperacion2() {
        Menues.printMenuAdministrador();
        String userInput = Menues.waitForUserInput();
        ejecutarOpcionAdministrador(userInput);
        //acá se imprime el menu del administrador, se pide un input por parte del administrador que navega la pagina y deriva al método que ejecuta la opcion ingresada
        return userInput;
    }

    private boolean shouldExecuteAnotherOperation(String userInput) {
        return !Menues.EXIT_VALUE.equals(userInput);
    }

    public void ejecutarOpcionAdministrador(String userInput) {
        //aca se ejecutan las opciones que puede elegir el administrador en su menu
        if (Menues.EXIT_VALUE.equalsIgnoreCase(userInput)) {
            return;
        } else if ("A".equalsIgnoreCase(userInput)) {
            getNombre();
            //si aprieta A devuelve su nombre
        } else if ("B".equalsIgnoreCase(userInput)) {
            getEmail();
        //si aprieta B devuelve su email
        } else if ("C".equalsIgnoreCase(userInput)) {
            while (shouldExecuteAnotherOperation(userInput)) {
                actualizarCatalogo();
            }
            Menues.printSaludo();
        } else {
            Menues.printOpcionInvalida();
        }
        Menues.waitForUserInput();
    }
}
