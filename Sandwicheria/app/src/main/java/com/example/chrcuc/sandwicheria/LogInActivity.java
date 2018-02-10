package com.example.chrcuc.sandwicheria;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {
    EditText editText, editText1;
    Cursor cursor;
    TextView textView;
    Button button,button1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editText = findViewById(R.id.editTextUser);
        editText1 = findViewById(R.id.editTextPassword);
        textView = findViewById(R.id.textView4);
        button = findViewById(R.id.continuar);
        button1 = findViewById(R.id.salir);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ingresar() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this, "DBSandwich", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();


        String users = editText.getText().toString();
        String passwords = editText1.getText().toString();
        cursor = sqLiteDatabase.rawQuery("select nombre,contrasena from clientes where nombre = '" + users + "' and contrasena = '" + passwords + "'", null);


        if (cursor.moveToFirst() == true) {

            String user = cursor.getString(0);
            String password = cursor.getString(1);

            if (users.equals(user) && passwords.equals(password)) {
                Intent ven = new Intent(this, MainActivity.class);
                startActivity(ven);

                editText1.setText("");
                editText.setText("");
            }
        }
    }
}
