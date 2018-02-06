package com.example.chrcuc.solobici;

import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bAcercaDe;
    private Button bJuego;
    private Button bconfigurar;
    private Button bSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Boton y escuchador para la pantalla "Juego"
        bJuego = (Button) findViewById(R.id.Boton01);
        bJuego.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarJuego();
            }
        });

        //Boton y escuchador para la pantalla "Acerca de"
        bAcercaDe = (Button) findViewById(R.id.Boton03);
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarAcercaDe();
            }
        });

        bSalir = (Button) findViewById(R.id.Boton04);
        bSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarSalir();
            }
        });

        bconfigurar = (Button) findViewById(R.id.Boton02);
        bconfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento1 = new Intent(MainActivity.this, VentanaPreferencias.class);
                startActivity(intento1);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferencias:
                Intent intento1 = new Intent(MainActivity.this, PantallaOpciones.class);
                startActivity(intento1);
                return true;
            case R.id.obtenerpreferencias:
                Intent intento2 = new Intent(MainActivity.this, SettingsFragment.class);
                startActivity(intento2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

            //Metodo que activa la pantalla Juego
        public void lanzarJuego () {
            Intent i = new Intent(this, Juego.class);
            startActivity(i);
        }
        //Metodo que activa la pantalla AcercaDe
        public void lanzarAcercaDe () {
            Intent i = new Intent(this, AcercaDe.class);
            startActivity(i);
        }
        //Metodo que activa la pantalla AcercaDe
        public void lanzarSalir () {
            finish();
        }
}






