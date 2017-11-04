package com.example.chrcuc.botones_ventana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CBEjercicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbejercicio);

        final CheckBox Cycling = (CheckBox) findViewById(R.id.chkBoxCycling);
        final CheckBox Teaching = (CheckBox) findViewById(R.id.chkBoxTeaching);
        final CheckBox Blogging = (CheckBox) findViewById(R.id.chkBoxBlogging);


        Button btnHobby = (Button)findViewById(R.id.botonHobby);
        final TextView txtHobby = (TextView)findViewById(R.id.txtHobby);

        btnHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strMessage = "";
                if(Cycling.isChecked())
                {
                    strMessage+="Cycling ";
                }
                if(Teaching.isChecked())
                {
                    strMessage+="Teaching ";
                }
                if(Blogging.isChecked())
                {
                    strMessage+="Blogging ";
                }
                showTextNotification(strMessage);
            }
            public void showTextNotification(String msgToDisplay)
            {
                txtHobby.setText(msgToDisplay);
//Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
