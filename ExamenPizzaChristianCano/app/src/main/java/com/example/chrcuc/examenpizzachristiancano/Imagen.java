package com.example.chrcuc.examenpizzachristiancano;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Imagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {
        public EjemploView(Context contexto) {
            super(contexto);
            Resources res = contexto.getResources();

        }

        protected void onDraw(Canvas canvas) {

            Paint pincel = new Paint();
            pincel.setColor(Color.YELLOW);
            pincel.setStrokeWidth(30);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(530, 500, 150, pincel);
            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
            pincel.setColor(Color.RED);
            canvas.drawCircle(530, 500, 130, pincel);
            pincel.setStrokeWidth(3);
            pincel.setTextSize(50);
            pincel.setTypeface(Typeface.DEFAULT_BOLD);
            pincel.setColor(Color.BLACK);
            canvas.drawText("Creado Por Christian Cano",200,1400,pincel);
        }
    }
}
