package main.java.tp.usuarios;

import main.java.tp.Biblioteca;
import main.java.tp.Libro;

public class Administrador extends Usuario {

    public Administrador(String nombreAdministrador, String emailAdministrador) {
        super(nombreAdministrador, emailAdministrador);
        this.nombre = super.getNombre();
        this.email = super.getEmail();
    }
    //hereda metodos del usuario y además se agrega uno propio para actualizar el catálogo de libros

    public void agregarLibro(String nombreLibro, String autorLibro, int ejemplares, Biblioteca biblioteca){
        Libro libro = new Libro(nombreLibro, autorLibro);
        libro.setCantidad(ejemplares);
        biblioteca.agregarLibroAlCatalogo(libro,ejemplares);
        //esta opcion permite crear un nuevo libro y declarar la cantidad de ejemplares disponibles
    }

    public void quitarLibro(String tituloBuscado, Biblioteca biblioteca){
        //Acá se permite al administrador eliminar uno de los libros del catálogo. El libro se borra de los titulos disponibles (no se muestra mas cuando pidan los titulos disponibles) y de el objeto "libro" de la lista de libros disponibles
        biblioteca.quitarLibroDelCatalogo(tituloBuscado);
    }
}
