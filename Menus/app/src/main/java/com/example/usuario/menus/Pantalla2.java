package com.example.usuario.menus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by usuario on 04/11/2017.
 */

public class Pantalla2 extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        TextView textView = (TextView)findViewById(R.id.msg);
        textView.setText("RelativeLayout");
    }
}

