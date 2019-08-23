package com.example.mirke.agenda;

public class Contacto {
    String nombre;
    String numero;
    String direccion;

    public Contacto(String nombre, String numero, String direccion) {
        this.nombre = nombre;
        this.numero = numero;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
