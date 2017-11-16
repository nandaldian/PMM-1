package com.example.chrcuc.ejemploexamen;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        TextView nom = (TextView)findViewById(R.id.TVzona);
        TextView tar = (TextView)findViewById(R.id.TVtarifa);
        TextView dec = (TextView)findViewById(R.id.TVdecoracion);
        TextView peso = (TextView)findViewById(R.id.TVpeso);
        TextView coste = (TextView)findViewById(R.id.TVcoste);
        ImageView img = (ImageView)findViewById(R.id.imageView);


        nom.setText(bundle.getString("ZONA"));
        tar.setText(bundle.getString("ADICIONAL"));
        dec.setText(bundle.getString("TARIFA"));
        peso.setText(bundle.getString("PESO"));
        coste.setText(bundle.getString("PRECIO"));
        Drawable drw = getResources().getDrawable("FOTO".);
        img.setImageDrawable(drw);



    }
}
