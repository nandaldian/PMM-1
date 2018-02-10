package com.example.chrcuc.sandwicheria;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {

    EditText editText, editText1;
    Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editText = findViewById(R.id.editTextUserRegitre);
        editText1 = findViewById(R.id.editTextPasswordRegistre);
        button = findViewById(R.id.Registrar);
        button1 = findViewById(R.id.Volver);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar(view);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver(view);
            }
        });


    }
    public void registrar(View view){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this, "DBSandwich", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();


        String users = editText.getText().toString();
        String passwords = editText1.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",users);
        contentValues.put("contrasena",passwords);

        sqLiteDatabase.insert("clientes",null,contentValues);
        sqLiteDatabase.close();

        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
    public  void  volver(View view){
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
