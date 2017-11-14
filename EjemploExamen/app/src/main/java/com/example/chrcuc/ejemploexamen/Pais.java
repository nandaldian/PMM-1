package com.example.chrcuc.ejemploexamen;

/**
 * Created by chrcuc on 14/11/17.
 */

public class Pais {
    private String nombre;
    private  int precio;

    public  Pais(String nom,int pre){
        nombre=nom;
        precio=pre;
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
