package com.example.usuario.dibujos;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private BitmapDrawable miImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {
        public EjemploView(Context contexto) {
            super(contexto);
            Resources res = contexto.getResources();
            miImagen = (BitmapDrawable) res.getDrawable(R.drawable.aaaaaaaaa);
            miImagen.setBounds(new Rect(50, 50, 290, 200));
        }

        @Override
        protected void onDraw(Canvas canvas) {

            Paint pincel = new Paint();
            pincel.setColor(Color.YELLOW);
            pincel.setStrokeWidth(20);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(1000, 1500, 80, pincel);
            pincel.setColor(Color.DKGRAY);
            for (int i = 0; i<=500;i=i+50) {
                canvas.drawCircle(540, 800, 20+i, pincel);
            }
            miImagen.draw(canvas);
            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(1);
            for (int i = 0; i<=500;i=i+5){
                canvas.drawCircle(540, 800, 15+i, pincel);
            }
            canvas.drawLine(150, 800, 930, 800, pincel);
            canvas.drawLine(540, 1190, 540, 410, pincel);
        }
    }
}