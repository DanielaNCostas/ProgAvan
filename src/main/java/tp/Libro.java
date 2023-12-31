package main.java.tp;

import java.util.ArrayList;
import java.util.List;

// Clase Libro que representa un libro en la biblioteca
public class Libro {
    private final String titulo;
    private final String autor;
    private final List<String> calificaciones;
    private int cantidad;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.calificaciones = new ArrayList<>(); // Lista de calificaciones realizadas para un libro
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public List<String> getCalificaciones() {
        //este método permite ver las calificaciones de un libro.
        return calificaciones;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int numeroDeEjemplares) {
        this.cantidad = numeroDeEjemplares;
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
        String comentarioAgregado = calificacion.getComentario();
        int puntuacionAgregada = calificacion.getPuntuacion();
        String puntuacionAgregadaEscrita = String.valueOf(puntuacionAgregada);
        String calificacionImprimible = "¨" + puntuacionAgregadaEscrita + " - " + comentarioAgregado + "¨";
        calificaciones.add(calificacionImprimible); // Agregar la calificación a la lista de calificaciones del libro
    }
}
