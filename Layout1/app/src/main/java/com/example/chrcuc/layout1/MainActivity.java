package com.example.chrcuc.layout1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button botonprueba= (Button)findViewById(R.id.botonPrueba);
        final TextView etiqueta= (TextView)findViewById(R.id.etiquetaPrueba);

        botonprueba.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){

                etiqueta.setText("Hola");

            }
        });
    }
}
