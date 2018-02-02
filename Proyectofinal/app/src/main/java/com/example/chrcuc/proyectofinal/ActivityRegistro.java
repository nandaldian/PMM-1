package com.example.chrcuc.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityRegistro extends AppCompatActivity {

    DatabaseHelper databaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        this.setTitle("Registro");



        databaseHelper = new DatabaseHelper(this);


        Button button = findViewById(R.id.buttonConfirmar);
        Button button1= findViewById(R.id.buttonSalir);
        final EditText editText = findViewById(R.id.editTextCrearContraseña);
        final EditText editText1 = findViewById(R.id.editTextCrearUsuario);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.open();
                databaseHelper.insert(editText.toString(),editText1.toString());
                databaseHelper.close();
                Intent intent = new Intent(ActivityRegistro.this,Imagen.class);
                startActivity(intent);


            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRegistro.this,ActivityLog.class);
                startActivity(intent);
            }
        });
    }
}
