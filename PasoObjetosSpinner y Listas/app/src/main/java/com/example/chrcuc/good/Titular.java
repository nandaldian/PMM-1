package com.example.chrcuc.good;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by chrcuc on 20/10/17.
 */

public class Titular implements Serializable{

    private String titulo;
    private String subtitulo;
    private int foto;
    public Titular(String tit, String sub,int fot){
        titulo = tit;
        subtitulo = sub;
        foto=fot;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getSubtitulo(){
        return subtitulo;
    }
    public int getFoto(){
        return foto;
    }
}
