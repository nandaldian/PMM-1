package com.example.chrcuc.botones_ventana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CBEjercicio3 extends AppCompatActivity {

        CheckBox chkBoxCycling;
        CheckBox chkBoxTeaching;
        CheckBox chkBoxBlogging;
        Button btnHobby;
        TextView txtHobby;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cbejercicio2);
            initialUISetup();

        }


    public void initialUISetup()
    {
        chkBoxCycling = (CheckBox) findViewById(R.id.chkBoxCycling);
        chkBoxTeaching = (CheckBox) findViewById(R.id.chkBoxTeaching);
        chkBoxBlogging = (CheckBox) findViewById(R.id.chkBoxBlogging);
        btnHobby = (Button)findViewById(R.id.botonHobby);
        txtHobby = (TextView)findViewById(R.id.txtHobby);



        chkBoxCycling.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxTeaching.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxBlogging.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
    }


//Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();

    }
}


