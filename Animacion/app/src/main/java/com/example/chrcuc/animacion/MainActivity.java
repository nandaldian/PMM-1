package com.example.chrcuc.animacion;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable animacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animacion = (AnimationDrawable)getResources().getDrawable(R.drawable.animacion);

        ImageView imageView= new ImageView(this);
        imageView.setBackgroundColor(Color.WHITE);
        imageView.setImageDrawable(animacion);
        setContentView(imageView);
    }
    public boolean onTouchEvent(MotionEvent evento) {
        //Al realizar una pulsación en pantalla
        if (evento.getAction() == MotionEvent.ACTION_DOWN) {
            //Ponemos en marcha la animación
            animacion.start();
            return true;
        }
        return super.onTouchEvent(evento);
    }

}
