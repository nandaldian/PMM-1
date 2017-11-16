package com.example.chrcuc.ejemploexamen;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by chrcuc on 14/11/17.
 */

public class Pais implements Serializable{
    private String nombre;
    private  double precio;
    private String zona;
    private int foto;

    public  Pais(String nom,double pre,String zo,int fo){
        nombre=nom;
        precio=pre;
        zona=zo;
        foto=fo;

    }

    public int getFoto() {
        return foto;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
