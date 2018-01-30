package com.example.chrcuc.transiccionyanimacion;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
        ImageView imageView;
    TransitionDrawable transitionDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.transicion);
        transitionDrawable = (TransitionDrawable)getResources().getDrawable(R.drawable.transicion);

        imageView.setImageDrawable(transitionDrawable);
    }
    public  boolean onTouchEvent(MotionEvent motionEvent){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            transitionDrawable.startTransition(3000);

            return true;
        }
        return  super.onTouchEvent(motionEvent);
    }
}
