package com.example.chrcuc.good;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by chrcuc on 24/10/17.
 */

public class Pantalla2 extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.window2);

        final TextView otroSaludo= (TextView)findViewById(R.id.texto);
        final ImageView img = (ImageView)findViewById(R.id.imagen);

        Bundle mibundel = getIntent().getExtras();
        Titular t = (Titular) mibundel.getSerializable("TitulaKey");
        otroSaludo.setText(t.toString());



    }
}
