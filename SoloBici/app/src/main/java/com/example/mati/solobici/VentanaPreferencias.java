package com.example.chrcuc.solobici;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VentanaPreferencias extends AppCompatActivity {
    private Button btnPreferencias;
    private Button btnObtenerPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_preferencias);

        btnPreferencias = (Button)findViewById(R.id.boton_preferencias);
        btnObtenerPreferencias = (Button)findViewById(R.id.boton_obtener_preferencias);

        btnPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VentanaPreferencias.this,PantallaOpciones.class));
            }
        });

        btnObtenerPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref =
                        PreferenceManager.getDefaultSharedPreferences(
                                VentanaPreferencias.this);
                String preferen1="desactivada";
                if(pref.getBoolean("opcion1", false)) preferen1="activada";
                Toast.makeText(getApplicationContext(),preferen1,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),pref.getString("opcion2",""),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),pref.getString("opcion3",""),Toast.LENGTH_SHORT).show();
            }
        });
    }

}

