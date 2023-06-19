package main.java.tp;

import java.util.ArrayList;

public class Biblioteca{
    private ArrayList<String> titulosDisponibles = new ArrayList<>();
    private ArrayList<Libro> librosDisponibles = new ArrayList<>();

    public Biblioteca(){
        inicializarCatalogo();
    }

    public void inicializarCatalogo(){
        while (librosDisponibles.size()==0) {
            Libro libro1 = new Libro("100 años de soledad", "Gabriel García Marquez");
            int ejemplaresLibro1 = 4;
            agregarLibroAlCatalogo(libro1,ejemplaresLibro1);

            Libro libro2 = new Libro("Rayuela", "Julio Cortazar");
            int ejemplaresLibro2 = 3;
            agregarLibroAlCatalogo(libro2, ejemplaresLibro2);

            Libro libro3 = new Libro("El tunel", "Ernesto Sábato");
            int ejemplaresLibro3 = 2;
            agregarLibroAlCatalogo(libro3,ejemplaresLibro3);
        }
    }

    public ArrayList<String> getTitulosDisponibles() {
        return titulosDisponibles;
    }

    public void setTitulosDisponibles(ArrayList<String> titulosDisponibles) {
        this.titulosDisponibles = titulosDisponibles;
    }

    public ArrayList<Libro> getLibrosDisponibles() {
        return librosDisponibles;
    }

    public void setLibrosDisponibles(ArrayList<Libro> librosDisponibles) {
        this.librosDisponibles = librosDisponibles;
    }

    public void agregarLibroAlCatalogo(Libro libro, int cantidad){
        //este método está asociado al administrador y permite agregar un libro al catálogo
        libro.setCantidad(cantidad);
        librosDisponibles.add(libro);
        String titulo = libro.getTitulo();
        titulosDisponibles.add(titulo);
    }

    public void quitarLibroDelCatalogo(String tituloBuscado){
        //este método está asociado al administrador y permite borrar un libro del catálogo
        boolean existe = titulosDisponibles.contains(tituloBuscado);
        if (existe) {
            int index = titulosDisponibles.indexOf(tituloBuscado);
            titulosDisponibles.remove(tituloBuscado);
            librosDisponibles.remove(index);
            System.out.println("El libro ha sido eliminado del catálogo.");
        } else {
            System.out.println("El título ingresado no existe en el catálogo");
        }
    }

    public void verTitulosDisponibles() {
        //este método devuelve una lista con los libros del catálogo
        System.out.println(titulosDisponibles);
    }
}
