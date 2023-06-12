package main.java.tp;

import java.util.ArrayList;

public class Biblioteca{
    public static ArrayList<String> titulosDisponibles = new ArrayList<>();
    public static ArrayList<Libro> librosDisponibles = new ArrayList<>();

    public Biblioteca(){
        librosEnCatalogo();
    }

    public static void librosEnCatalogo(){
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

    public static void quitarLibroDelCatalogo(String tituloBuscado){

        boolean existe = titulosDisponibles.contains(tituloBuscado);
        if (existe==true) {
            int index = titulosDisponibles.indexOf(tituloBuscado);
            titulosDisponibles.remove(tituloBuscado);
            librosDisponibles.remove(index);
            System.out.println("El libro ha sido eliminado del catálogo.");
        } else {
            System.out.println("El título ingresado no existe en el catálogo");
        }
    }

    public static void agregarLibroAlCatalogo(Libro libro, int cantidad){
        libro.setCantidad(cantidad);
        librosDisponibles.add(libro);
        String titulo = libro.getTitulo();
        titulosDisponibles.add(titulo);
    }

    public static void getTitulosDisponibles() {
        librosEnCatalogo();
        //Collections.sort(titulosDisponibles);
        System.out.println(titulosDisponibles);
    }
}
