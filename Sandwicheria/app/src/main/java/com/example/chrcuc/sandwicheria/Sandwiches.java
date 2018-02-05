package com.example.chrcuc.sandwicheria;

import java.io.Serializable;

/**
 * Created by chrcuc on 2/02/18.
 */

public class Sandwiches implements Serializable{
    private String nombre;
    private int precio;
    private String ingredientes;
    int id;

    public Sandwiches(int nom, String pre, String ingr, float id) {
        nombre = pre;
        precio = nom;
        ingredientes = ingr;


    }


    public String getIngredientes() {
        return ingredientes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = (int) precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
