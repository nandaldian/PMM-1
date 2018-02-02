package com.example.chrcuc.proyectofinal;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Imagen extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView textView;
    TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        textView = findViewById(R.id.aaaaa);
        textView1 = findViewById(R.id.bbbbb);


        databaseHelper = new DatabaseHelper(this);

        databaseHelper.open();
        fillData();
        databaseHelper.close();
    }
    private void fillData(){
        databaseHelper.open();
        Cursor cursor = databaseHelper.getItems();
        Users users = null;

        while (cursor.moveToNext()){
            String user = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SL_ID));
            String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SL_ITEM));

            users = new Users();

            users.user= user;
            users.password=password;


        }

        textView.setText(users.user);
        textView1.setText(users.password);
    }
    private class Users{
        String user;
        String password;
    }
}
