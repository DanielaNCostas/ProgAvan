package main.java.tp.usuarios;

import main.java.tp.Biblioteca;

import java.util.ArrayList;
import java.util.List;

class Cliente extends Usuario {
    public List<String> librosReservados;
    public List<Biblioteca.Reserva> reservas;

    public Cliente(String nombreCliente, String emailCliente) {
        super(nombreCliente, emailCliente);
        this.nombre = nombreCliente;
        this.email = emailCliente;
        this.librosReservados = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void reservar(Biblioteca.Libro libro) {
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

    private List<Biblioteca.Reserva> getReservas() {
        return reservas;
        //esto lo definimos para poder usarlo en devolver()
    }

    public void devolver() {
        Biblioteca.Reserva ultimaReserva = reservas.get(reservas.size() -1 );//pido el ultimo elemento de la lista de reservas
        ultimaReserva.devolver(); // Llamar al método devolver de la reserva
        reservas.remove(ultimaReserva); // Eliminar la reserva de la lista de reservas activas del usuario (no del historial de reservas, esa es otra lista - librosReservados -)
    }

    public void calificar(Biblioteca.Calificacion calificacion) {
        Biblioteca.Reserva ultimaReserva = reservas.get(reservas.size() -1 );
        Biblioteca.Libro libro = ultimaReserva.getLibro();
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
            Biblioteca.Cliente cliente = new Biblioteca.Cliente("Cliente123", "cliente@gmail.com");
            cliente.getNombre();
            //si aprieta A le devuelve el nombre
        } else if ("B".equalsIgnoreCase(userInput)) {
            Biblioteca.Cliente cliente = new Biblioteca.Cliente("Cliente123", "cliente@gmail.com");
            cliente.getEmail();
            //si aprieta B le devuelve el email
        } else if ("C".equalsIgnoreCase(userInput)) {
            Biblioteca.Cliente cliente = new Biblioteca.Cliente("Cliente123", "cliente@gmail.com");
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            getLibrosReservados();
            //devuelve su historial de reservas (los titulos de los libros reservados)
        } else if ("D".equalsIgnoreCase(userInput)) {
            //si aprieta D permite reservar un libro, pidiendole al cliente que ingrese por teclado el nombre y autor de ese libro. Verifica si hay unidades disponibles y asi determina si lo presta o no.
            Biblioteca.Cliente cliente = new Biblioteca.Cliente("Cliente123", "cliente@gmail.com");
            System.out.println("Ingrese el nombre del libro");
            String nombreLibro =  waitForUserInput();
            System.out.println("Ingrese el autor del libro");
            String autorLibro =  waitForUserInput();
            Biblioteca.Libro libro = new Biblioteca.Libro(nombreLibro, autorLibro);
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            if (libro.cantidad > 0) {
                reservar(libro);
                Biblioteca.Reserva reserva = new Biblioteca.Reserva(libro);
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
            Biblioteca.Calificacion calificacion = new Biblioteca.Calificacion(comentario, puntuacion);
            Biblioteca.Cliente cliente = new Biblioteca.Cliente("Cliente123", "cliente@gmail.com");
            cliente.calificar(calificacion);//ver esto que no corre
            System.out.println("Hemos guardado su calificación.");
        } else {
            printOpcionInvalida();
        }
        waitForUserInput();
    }
}

