package main.java.tp;

// Clase Calificacion que representa una calificación realizada por un usuario para un libro
public class Calificacion {
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