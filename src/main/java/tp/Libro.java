package main.java.tp;

import java.util.ArrayList;
import java.util.List;

// Clase Libro que representa un libro en la biblioteca

public class Libro {
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
