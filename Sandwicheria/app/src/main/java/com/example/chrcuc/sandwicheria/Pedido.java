package com.example.chrcuc.sandwicheria;

import java.io.Serializable;

/**
 * Created by chrcuc on 7/02/18.
 */

public class Pedido implements Serializable{

    private int cantidad ;
    private String sandwich,envio,extras;
    private float precio;

    public Pedido(float pre,String ext,int cant,String sand,String envi){

        this.extras = ext;
        this.cantidad = cant;
        this.sandwich = sand;
        this.envio = envi;
        this.precio = pre;

    }

    public float getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getExtras() {
        return extras;
    }

    public String getEnvio() {
        return envio;
    }

    public String getSandwich() {
        return sandwich;
    }
}
