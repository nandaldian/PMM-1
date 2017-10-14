package com.example.usuario.ejercicio2;

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


        Button red = (Button) this.findViewById(R.id.buttonOne);

        Button yellow = (Button) this.findViewById(R.id.buttonTwo);

        Button blue = (Button) this.findViewById(R.id.buttonTres);

        Button limpiar = (Button) this.findViewById(R.id.buttonLimpiar);

        final TextView fondo = (TextView) this.findViewById(R.id.Fondo);


        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fondo.setBackgroundColor(getResources().getColor(R.color.ROJO));
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fondo.setBackgroundColor(getResources().getColor(R.color.AMARILLO));
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fondo.setBackgroundColor(getResources().getColor(R.color.AZUL));
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fondo.setBackgroundColor(getResources().getColor(R.color.BLANCO));
            }
        });
    }
}
