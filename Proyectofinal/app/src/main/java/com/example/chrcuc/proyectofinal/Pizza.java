package com.example.chrcuc.proyectofinal;

import java.io.Serializable;

/**
 * Created by chrcuc on 17/11/17.
 */

public class Pizza implements Serializable {
    private String nombre;
    private int precio;
    private String ingredientes;
    private int foto;

    public Pizza(String nom, int pre, String ingr, int fo) {
        nombre = nom;
        precio = pre;
        ingredientes = ingr;
        foto = fo;

    }

    public int getFoto() {
        return foto;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }
}

