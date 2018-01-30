package com.example.chrcuc.prueba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Pantalla2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView otroSaludo=(TextView) findViewById(R.id.saludo2);

        Bundle recoger=getIntent().getExtras();
        otroSaludo.setText((recoger.getString("TEXTO")));

    }
}
