package com.example.chrcuc.good;

/**
 * Created by chrcuc on 20/10/17.
 */

public class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    public Persona(String tit, String sub,int fot){
        nombre = tit;
        apellido = sub;
        edad=fot;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public int getEdad(){
        return edad;
    }
}
