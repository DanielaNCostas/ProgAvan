package main.java.tp.usuarios;

public class Usuario {
    public String nombre;
    public String email;

    Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
        //devuelve el nombre del usuario (heredado a clientes y administradores)
    }

    public String getEmail() {
        return this.email;
        //devuelve el email del usuario (heredado a clientes y administradores)
    }
}