package com.example.chrcuc.ejemploexamen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Pais[] pais = new Pais[]{
            new Pais("Asia y oceania",20),
            new Pais("America",30),
            new Pais("Europa",10)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle paso_datos = new Bundle();
        final EditText tvpeso = (EditText) findViewById(R.id.editText);



        String mensaje;
        Spinner miSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<Pais> adaptador = new ArrayAdapter<Pais>(this,
                android.R.layout.simple_spinner_dropdown_item, pais);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(adaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "Zona seleccionada: " + pais[position].getNombre();
                showToast(mensaje);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final Button botonpasar = (Button) findViewById(R.id.button);

        botonpasar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String mensajePeso = "El peso introducido es: " + tvpeso.getText();
                paso_datos.putString("TEXTO", mensajePeso);
                Intent miIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(miIntent);
            }
        });
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


   }

