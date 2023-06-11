package main.java.tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.iniciarPantalla();
        //clase y método principal
    }

    public void iniciarPantalla() {
        String userInput = "";
            while (shouldExecuteAnotherOperation(userInput)) {
            userInput = ejecutarNuevaOperacion();
            //este método inicia los menues interactivos por consola
        }
        printSaludo();
    }

    private static final String EXIT_VALUE = "0";// defino un valor de salida para los menues

    private boolean shouldExecuteAnotherOperation(String userInput) {
        return !EXIT_VALUE.equals(userInput);
    }

    private String ejecutarNuevaOperacion() {
        printMenu();
        String userInput = waitForUserInput();
        ejecutarOpcion(userInput);
        //este método imprime el primer menu, pide un input y llama al método que ejecuta la opción ingresada por input
        return userInput;
    }

    public static void printMenu() {
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
        System.out.println();
        System.out.println("Si desea salir ingrese 0 + Enter");
        System.out.println("--");
    }

    private static String waitForUserInput() {
        return new Scanner(System.in).nextLine();
    }

    private void ejecutarOpcion(String userInput) {
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

    private static void printOpcionInvalida() {
        System.out.println("--");
        System.out.println("La opción ingresada es incorrecta");
        System.out.println("--");
        System.out.println("Presione cualquier tecla para volver al menú.");
        //esto es por si ingresan un valor no definido en el menu
    }

    private static void printSaludo() {
        System.out.println("--");
        System.out.println("Gracias por usar la página de esta biblioteca. Vuelva pronto");
        System.out.println("--");
        //al principio se definio 0 como valor de salida, por lo que, al apretar ese valor se sale de la pagina de la biblioteca
    }

    class Usuario {
        public String nombre;
        public String email;

        Usuario(String nombre, String email) {
            this.nombre = nombre;
            this.email = email;
        }

        public String getNombre() {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Usted es " + this.nombre);
            return this.nombre;
            //devuelve el nombre del usuario (heredado a clientes y administradores)
        }

        public String getEmail() {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Su email es " + this.email);
            return this.email;
            //devuelve el email del usuario (heredado a clientes y administradores)
        }
    }

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
                Libro libro = new Libro(nombreLibro, autorLibro);
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

    class Cliente extends Usuario {
        public List<String> librosReservados;
        public List<Reserva> reservas;

        public Cliente(String nombreCliente, String emailCliente) {
            super(nombreCliente, emailCliente);
            this.nombre = nombreCliente;
            this.email = emailCliente;
            this.librosReservados = new ArrayList<>();
            this.reservas = new ArrayList<>();
        }

        public void reservar(Libro libro) {
            String titulo = libro.getTitulo();
            librosReservados.add(titulo); // Agregar el nombre del libro reservado al historial de reservas del usuario (no agrega el libro en sí a la lista "librosReservados" sino solo el titulo, porque de otra manera al pedir el historial de reservas lo que devuelve la consola es un objeto libro que no se entiende, o sea un ID, que no le interesaría para nada al usuario que está pidiendo su historial de reservas)
        }

        private List<String> getLibrosReservados() {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Este es su historial de reservas:");
            System.out.println(librosReservados.toString());
            System.out.println(reservas.toString()); //esto podría borrarse pero lo dejé acá para que se vea mejor que cuando haces una devolucion se elimina la ultima reserva de la lista reservas (que almacena las reservas activas)
            return librosReservados;
            //este metodo es el que devuelve el historial de reservas, es decir, los titulos de los titulos que ha reservado. Esta lista no se ve afectada al devolver un libro (el titulo queda en el historial)
        }

        private List<Reserva> getReservas() {
            return reservas;
            //esto lo definimos para poder usarlo en devolver()
        }

        public void devolver() {
            Reserva ultimaReserva = reservas.get(reservas.size() -1 );//pido el ultimo elemento de la lista de reservas
            ultimaReserva.devolver(); // Llamar al método devolver de la reserva
            reservas.remove(ultimaReserva); // Eliminar la reserva de la lista de reservas activas del usuario (no del historial de reservas, esa es otra lista - librosReservados -)
        }

        public void calificar(Calificacion calificacion) {
            Reserva ultimaReserva = reservas.get(reservas.size() -1 );
            Libro libro = ultimaReserva.getLibro();
            libro.calificar(calificacion);
            ultimaReserva.calificar(calificacion);
            //permite calificar el ultimo libro reservado. Esto hay que mirarlo, todavía no pude hacerlo correr
        }

        private String ejecutarOpcion3() {
            printMenuCliente();
            String userInput = waitForUserInput();
            ejecutarOpcionCliente(userInput);
            return userInput;
            //aca se pide imprimir el menu del cliente, que éste ingrese una opcion por input y se deriva al metodo que ejecuta las opciones
        }

        private void ejecutarOpcionCliente(String userInput) {
            if (EXIT_VALUE.equalsIgnoreCase(userInput)) {
                return;
            } else if ("A".equalsIgnoreCase(userInput)) {
                Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
                cliente.getNombre();
                //si aprieta A le devuelve el nombre
            } else if ("B".equalsIgnoreCase(userInput)) {
                Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
                cliente.getEmail();
                //si aprieta B le devuelve el email
            } else if ("C".equalsIgnoreCase(userInput)) {
                Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
                System.out.println("--");
                System.out.println("Presione cualquier tecla para volver al menú.");
                System.out.println("");
                getLibrosReservados();
                //devuelve su historial de reservas (los titulos de los libros reservados)
            } else if ("D".equalsIgnoreCase(userInput)) {
                //si aprieta D permite reservar un libro, pidiendole al cliente que ingrese por teclado el nombre y autor de ese libro. Verifica si hay unidades disponibles y asi determina si lo presta o no.
                Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
                System.out.println("Ingrese el nombre del libro");
                String nombreLibro =  waitForUserInput();
                System.out.println("Ingrese el autor del libro");
                String autorLibro =  waitForUserInput();
                Libro libro = new Libro(nombreLibro, autorLibro);
                System.out.println("--");
                System.out.println("Presione cualquier tecla para volver al menú.");
                System.out.println("");
                if (libro.cantidad > 0) {
                    reservar(libro);
                    Reserva reserva = new Reserva(libro);
                    reservas.add(reserva);
                    System.out.println("Se ha reservado una copia de " + nombreLibro + " a su nombre. Puede pasar a retirarla por la biblioteca en nuestros horarios de atención.");
                } else {
                    System.out.println("No hay ejemplares disponibles de este libro");
                }
            } else if ("E".equalsIgnoreCase(userInput)) {
                //esta opcion permite devolver el ultimo libro reservado.
                devolver();
                System.out.println("--");
                System.out.println("Presione cualquier tecla para volver al menú.");
                System.out.println("");
                System.out.println("Hemos efectuado la devolución de su última reserva. Puede devolver el libro a la biblioteca en nuestros horarios de atención.");
            } else if ("F".equalsIgnoreCase(userInput)) {
                //esta opcion debería permitir calificar el ultimo libro reservado. No lo puedo hacer correr.
                System.out.println("--");
                System.out.println("Presione cualquier tecla para volver al menú.");
                System.out.println("");
                System.out.println("Ingrese un comentario breve sobre el libro");
                String comentario =  waitForUserInput();
                System.out.println("Ingrese un puntaje del 1 al 5 para el libro");
                int puntuacion =  Integer.parseInt(waitForUserInput());
                Calificacion calificacion = new Calificacion(comentario, puntuacion);
                Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
                cliente.calificar(calificacion);//ver esto que no corre
                System.out.println("Hemos guardado su calificación.");
            } else {
                printOpcionInvalida();
            }
            waitForUserInput();
        }
    }

    // Enumeración que representa los posibles estados de un libro en la biblioteca
    enum Estado {
        DISPONIBLE, PRESTADO, RETRASO
    }

    // Clase Libro que representa un libro en la biblioteca
    class Libro {
        private final String titulo;
        private final String autor;
        public final List<String> calificaciones;

        public Libro(String titulo, String autor) {
            this.titulo = titulo;
            this.autor = autor;
            this.calificaciones = new ArrayList<>(); // Lista de calificaciones realizadas para el libro
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        private int cantidad=1;

        public int declararCantidad() {
            //es un método que permite declarar la cantidad de ejemplares de un libro creado en el sistema
            System.out.println("Ingrese la cantidad de ejemplares disponibles");
            cantidad = Integer.parseInt(waitForUserInput());
            return cantidad;
        }
        public int getCantidad() {
            //devuelve cantidad de ejemplares de un libro
            System.out.println("--");
            System.out.println("Hay " + cantidad + " ejemplares disponibles.");
            return cantidad;
        }

        public List<String> getCalificaciones() {
            //este método permite ver las calificaciones de un libro. No lo pude hacer correr porque quería emparejarlo con la opcion F (calificar) del menu del cliente.
            System.out.println("--");
            System.out.println("Estas son las calificaciones hechas, con puntuaciones del 1 al 5: ");
            return calificaciones;
        }

        public void prestar() {
            if (cantidad > 0) {
                cantidad--; // Decrementar la cantidad de libros disponibles al prestar uno
            }
        }

        public void devolver() {
            cantidad++; // Incrementar la cantidad de libros disponibles al devolver uno
        }

        public void calificar(Calificacion calificacion) {
            String coment = calificacion.getComentario();
            int punt = calificacion.getPuntuacion();
            String puntua = String.valueOf(punt);
            String calif = coment + puntua;
            System.out.println(calif);
            calificaciones.add(calif); // Agregar la calificación a la lista de calificaciones del libro
        }
    }

    // Clase Calificacion que representa una calificación realizada por un usuario para un libro
    class Calificacion {
        private final String comentario;
        private final int puntuacion;

        public Calificacion(String comentario, int puntuacion) {
            this.comentario = comentario;
            this.puntuacion = puntuacion;
        }

        public String getComentario() {
            //me devuelve solo el comentario de la calificación
            System.out.println("");
            return comentario;
        }

        public int getPuntuacion() {
            //me devuelve solo la puntuacion de la calificación
            System.out.println("");
            return puntuacion;
        }

    }

    // Clase Reserva que representa una reserva realizada por un usuario para un libro
    class Reserva {
        private Cliente cliente;
        private Libro libro;
        private Estado estado;

        public Reserva(Libro libro) {
            this.libro = libro;
            this.estado = Estado.PRESTADO;
            libro.prestar(); // Llamar al método prestar del libro al realizar la reserva
        }

        public Cliente getCliente() {
            return cliente;
        }

        public Libro getLibro() {
            return libro;
        }

        public Estado getEstado() {
            return estado;
        }

        public void devolver() {
            if (estado == Estado.PRESTADO) {
                estado = Estado.DISPONIBLE;
                libro.devolver();
            } else if (estado == Estado.RETRASO) {
                estado = Estado.DISPONIBLE;
                libro.devolver();
                cliente.reservar(libro);
            }
        }

        public void cancelar() {
            //este método no supe en que usarlo.
            if (estado == Estado.PRESTADO) {
                estado = Estado.DISPONIBLE;
                libro.devolver();
            } else if (estado == Estado.RETRASO) {
                estado = Estado.DISPONIBLE;
                libro.devolver();
            }
            cliente.getReservas().remove(this.libro);
        }

        public void calificar(Calificacion calificacion) {
            //para agregar una calificacion a un libro
            libro.calificar(calificacion);
            estado = Estado.DISPONIBLE;
        }
    }
}
