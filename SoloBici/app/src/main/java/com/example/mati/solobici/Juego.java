package com.example.chrcuc.solobici;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Juego extends AppCompatActivity {

    private VistaJuego vj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        vj=(VistaJuego)findViewById(R.id.VistaJuego);

    }

    @Override
    protected void onDestroy() {
        vj.getHilo().detener();
        super.onDestroy();
    }
    @Override
        protected void onPause() {
            super.onPause();
            vj.getHilo().pausar();
        }
    @Override
    protected void onResume() {
            super.onResume();
            vj.getHilo().reanudar();
        }
}
