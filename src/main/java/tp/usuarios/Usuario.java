package main.java.tp.usuarios;

public class Usuario {
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