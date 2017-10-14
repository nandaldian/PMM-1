package com.example.usuario.ejerbuttonradio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView bg = (TextView) this.findViewById(R.id.background);
        ImageButton ibblue = (ImageButton) this.findViewById(R.id.imageButton2);
        ImageButton ibred = (ImageButton) this.findViewById(R.id.imageButton);

        ibblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(getResources().getColor(R.color.AZUL));
            }
        });
        ibred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(getResources().getColor(R.color.ROJO));
            }
        });
    }
}
