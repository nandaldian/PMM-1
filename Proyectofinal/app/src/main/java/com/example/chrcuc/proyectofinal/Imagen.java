package com.example.chrcuc.proyectofinal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Imagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        TextView textView = findViewById(R.id.aaaaa);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        databaseHelper.open();
       textView.setText(databaseHelper.getItems().toString());
       databaseHelper.close();
    }

      }
