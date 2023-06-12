package main.java.tp;

import main.java.tp.usuarios.Cliente;

// Clase Reserva que representa una reserva realizada por un usuario para un libro
public class Reserva {
    private Cliente cliente;
    private Libro libro;
    private Estado estado;

    public Reserva(Libro libro) {
        this.libro = libro;
        this.estado = Estado.PRESTADO;
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

    /*
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
    }*/

    /*public void calificar(Calificacion calificacion) {
        //para agregar una calificacion a un libro
        libro.calificar(calificacion);
        estado = Estado.DISPONIBLE;
    }*/
}