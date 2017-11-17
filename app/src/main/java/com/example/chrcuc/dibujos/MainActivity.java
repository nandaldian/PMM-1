package com.example.chrcuc.dibujos;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public class MiDibujo extends View{
        public MiDibujo(Context context){
            super(context);
        }
    }
    protected void onDraw(Canvas lienzo){
        Paint miPincel = new Paint();
        miPincel.setColor(Color.RED);
        miPincel.setStyle(Paint.Style.STROKE);
        lienzo.drawCircle(500,500,300,miPincel);

        miPincel.setTextSize(60);
        lienzo.drawText("Mi circulo",500,900,miPincel);
        String men = "Ancho : "+getWallpaperDesiredMinimumWidth()+" Alto:" +getWallpaperDesiredMinimumHeight();
        lienzo.drawText(men,500,1000,miPincel);
        Toast.makeText(getApplicationContext(),"mas dibujos ",Toast.LENGTH_LONG);

        int x= 500;int y =1300;int ancho = 300;int alto =500;
        ShapeDrawable miDrawable = new ShapeDrawable(new OvalShape());
        miDrawable.getPaint().setColor(0xff0000f);
        miDrawable.setBounds(x,y,x+ancho,y+alto);
        miDrawable.draw(lienzo);


        Resources res = this.getResources();
        BitmapDrawable imagen =(BitmapDrawable)res.getDrawable(R.drawable.maxresdefault);
        imagen.draw(lienzo);

    }
}
