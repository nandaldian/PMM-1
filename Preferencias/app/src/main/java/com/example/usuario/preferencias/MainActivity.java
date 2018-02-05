package com.example.usuario.preferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPreferencias;
    private Button btnObtenPreferencias;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPreferencias = (Button)findViewById(R.id.BtnPreferencias);
        btnObtenPreferencias = (Button)findViewById(R.id.BtnObtenPreferencias);
        btnPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Options.class));
            }
        });
        btnObtenPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String preferen1 = "desactivada";
                if(pref.getBoolean("opcion1", false)) preferen1="activada";
                Toast.makeText(getApplicationContext(), preferen1,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), pref.getString("opcion2",""),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), pref.getString("opcion3",""),Toast.LENGTH_SHORT).show();
            }
        });


    }
}