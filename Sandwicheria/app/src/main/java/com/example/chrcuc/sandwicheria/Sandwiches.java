package com.example.chrcuc.sandwicheria;

import java.io.Serializable;

/**
 * Created by chrcuc on 2/02/18.
 */

public class Sandwiches implements Serializable{
    private String nombre;
    private float precio;
    private String ingredientes;
    int ids;

    public Sandwiches(float pre, String nom, String ingr, int id) {
        ids=id;
        nombre = nom;
        precio = pre;
        ingredientes = ingr;


    }


    public String getIngredientes() {
        return ingredientes;
    }


    public float getPrecio() {
        return precio;
    }


    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return ids;
    }

}
