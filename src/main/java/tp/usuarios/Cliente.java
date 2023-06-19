package main.java.tp.usuarios;

import main.java.tp.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    public List<String> librosReservados;
    public List<Reserva> reservas;

    public Cliente(String nombreCliente, String emailCliente) {
        super(nombreCliente, emailCliente);
        this.nombre = nombreCliente;
        this.email = emailCliente;
        this.librosReservados = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public List<String> getLibrosReservados() {
        return librosReservados;
        //este metodo es el que devuelve el historial de reservas, es decir, los titulos de los titulos que ha reservado. Esta lista no se ve afectada al devolver un libro (el titulo queda en el historial)
    }

    public List<Reserva> getReservas() {
        return reservas;
        //este metodo es el que devuelve el historial de reservas, es decir, los titulos de los titulos que ha reservado. Esta lista no se ve afectada al devolver un libro (el titulo queda en el historial)
    }

    public void reservar(Libro libro, Reserva reserva) {
        String titulo = libro.getTitulo();
        librosReservados.add(titulo);
        reservas.add(reserva);
        //Agregar el nombre del libro reservado al historial de reservas del usuario (no agrega el libro en sí a la lista "librosReservados" sino solo el titulo, porque de otra manera al pedir el historial de reservas lo que devuelve la consola es un objeto libro que no se entiende, o sea un ID, que no le interesaría para nada al usuario que está pidiendo su historial de reservas)
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
        //permite calificar el ultimo libro reservado.
    }

    public String ejecutarOperacionesDelCliente(Biblioteca biblioteca) {
        //Cliente cliente = new Cliente("Cliente123", "cliente@gmail.com");
        Menues.printMenuCliente();
        String userInput = Menues.waitForUserInput();
        ejecutarOpcionCliente(userInput, biblioteca);
        return userInput;
        //aca se pide imprimir el menu del cliente, que éste ingrese una opcion por input y se deriva al metodo que ejecuta las opciones
    }

    public void ejecutarOpcionCliente(String userInput, Biblioteca biblioteca) {
        if (Menues.EXIT_VALUE.equalsIgnoreCase(userInput)) {
            Menues.printSaludo();
            System.exit(0);
            return;
        } else if ("A".equalsIgnoreCase(userInput)) {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Usted es " + getNombre());
            //si aprieta A le devuelve el nombre
        } else if ("B".equalsIgnoreCase(userInput)) {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Su email es " + getEmail());
            //si aprieta B le devuelve el email
        } else if ("C".equalsIgnoreCase(userInput)) {
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Este es su historial de reservas:");
            System.out.println(getLibrosReservados());
            System.out.println(getReservas().toString()); //esto podría borrarse pero lo dejé acá para que se vea mejor que cuando haces una devolucion se elimina la ultima reserva de la lista reservas (que almacena las reservas activas)
            //devuelve su historial de reservas (los titulos de los libros reservados)
        } else if ("D".equalsIgnoreCase(userInput)) {
            //si aprieta D permite reservar un libro, pidiendole al cliente que ingrese por teclado el nombre y autor de ese libro. Verifica si hay unidades disponibles y asi determina si lo presta o no.
            System.out.println("Ingrese el nombre del libro");
            String nombreLibro =  Menues.waitForUserInput();
            boolean existe = biblioteca.getTitulosDisponibles().contains(nombreLibro);
            if (existe) {
                int index = biblioteca.getTitulosDisponibles().indexOf(nombreLibro);
                Libro libro = biblioteca.getLibrosDisponibles().get(index);
                int cantidad = libro.getCantidad();
                if (cantidad > 0) {
                    Reserva reserva = new Reserva(libro);
                    reservar(libro, reserva);
                    libro.prestar();
                    System.out.println("--");
                    System.out.println("Presione cualquier tecla para volver al menú.");
                    System.out.println("");
                    System.out.println("Se ha reservado una copia de " + nombreLibro + " a su nombre. Puede pasar a retirarla por la biblioteca en nuestros horarios de atención.");
                } else {
                    System.out.println("--");
                    System.out.println("Presione cualquier tecla para volver al menú.");
                    System.out.println("");
                    System.out.println("No hay ejemplares disponibles de este libro");
                }
            } else {
                System.out.println("--");
                System.out.println("Presione cualquier tecla para volver al menú.");
                System.out.println("");
                System.out.println("El título ingresado no existe en el catálogo");
            }
        } else if ("E".equalsIgnoreCase(userInput)) {
            //esta opcion permite devolver el ultimo libro reservado. Se borra la ultima reserva de la lista reservas
            devolver();
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Hemos efectuado la devolución de su última reserva. Puede devolver el libro a la biblioteca en nuestros horarios de atención.");
        } else if ("F".equalsIgnoreCase(userInput)) {
            //esta opcion permite calificar el ultimo libro reservado
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            System.out.println("Ingrese un comentario breve sobre el libro");
            String comentario =  Menues.waitForUserInput();
            System.out.println("Ingrese un puntaje del 1 al 5 para el libro");
            int puntuacion =  Integer.parseInt(Menues.waitForUserInput());
            Calificacion calificacion = new Calificacion(comentario, puntuacion);
            calificar(calificacion);
            System.out.println("Hemos guardado su calificación.");
        } else if ("G".equalsIgnoreCase(userInput)) {
            //esta opcion permite ver las calificaciones de un libro de la biblioteca
            System.out.println("Ingrese el nombre del libro");
            String nombreLibro =  Menues.waitForUserInput();
            boolean existe = biblioteca.getTitulosDisponibles().contains(nombreLibro);
            if (existe) {
                int index = biblioteca.getTitulosDisponibles().indexOf(nombreLibro);
                Libro libro = biblioteca.getLibrosDisponibles().get(index);
                System.out.println("--");
                System.out.println("Presione cualquier tecla para volver al menú.");
                System.out.println("--");
                System.out.println("Estas son las calificaciones hechas, con puntuaciones del 1 al 5: ");
                System.out.println("");
                System.out.println(libro.getCalificaciones());
            } else {
                System.out.println("--");
                System.out.println("Presione cualquier tecla para volver al menú.");
                System.out.println("");
                System.out.println("El título ingresado no existe en el catálogo");
            }
        } else if ("H".equalsIgnoreCase(userInput)) {
            //esta opcion permite ver los titulos que forman parte del catálogo de la biblioteca
            System.out.println("--");
            System.out.println("Presione cualquier tecla para volver al menú.");
            System.out.println("");
            biblioteca.verTitulosDisponibles();
        } else {
            Menues.printOpcionInvalida();
        }
        Menues.waitForUserInput();
    }

}

