package com.example.chrcuc.botones_ventana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button boton1=(Button)findViewById(R.id.botonuno);
        final Button boton2=(Button)findViewById(R.id.botondos);
        final Button boton3=(Button)findViewById(R.id.botontres);
        final Button boton4=(Button)findViewById(R.id.botoncuatro);
        final Button boton5=(Button)findViewById(R.id.botoncinco);
        final Button boton6=(Button)findViewById(R.id.botonseis);
        final Button boton7=(Button)findViewById(R.id.botonsiete);



        boton1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent =new Intent(MainActivity.this, Linear.class);
                startActivity(miIntent);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent =new Intent(MainActivity.this, Table.class);
                startActivity(miIntent);
            }
        });
        boton3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent =new Intent(MainActivity.this, Relative.class);
                startActivity(miIntent);
            }
        });
        boton4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent =new Intent(MainActivity.this, Grid.class);
                startActivity(miIntent);
            }
        });
        boton5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent =new Intent(MainActivity.this, CBEjercicio.class);
                startActivity(miIntent);
            }
        });
        boton6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent =new Intent(MainActivity.this, CBEjercicio2.class);
                startActivity(miIntent);
            }
        });
        boton7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent =new Intent(MainActivity.this, CBEjercicio3.class);
                startActivity(miIntent);
            }
        });
    }
}
