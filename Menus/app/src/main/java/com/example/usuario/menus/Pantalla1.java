package com.example.usuario.menus;

/**
 * Created by usuario on 04/11/2017.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Pantalla1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        TextView textView = (TextView)findViewById(R.id.msg);
        textView.setText("LinearLayout");
    }
}