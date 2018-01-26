package com.example.chrcuc.accesodatos;

/**
 * Created by chrcuc on 16/01/18.
 */

public class Clientes {

    private String nombre;
    private String telefono;

    public Clientes( String nombr,String telef){
       nombre= nombr;
        telefono= telef;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }
}
