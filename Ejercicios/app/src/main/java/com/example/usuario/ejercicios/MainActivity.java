package com.example.usuario.ejercicios;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnButton =(Button) this.findViewById(R.id.ButtonSet);
        final RadioButton red=(RadioButton) this.findViewById(R.id.red);
        final RadioButton white=(RadioButton)this.findViewById(R.id.white);
        final RadioButton blue=(RadioButton) this.findViewById(R.id.blue);
        final TextView bg=(TextView)this.findViewById(R.id.color);




        btnButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v){
                if (red.isChecked())
                    bg.setBackgroundColor(getResources().getColor(R.color.ROJO));
                else if(white.isChecked())
                    bg.setBackgroundColor(getResources().getColor(R.color.BLANCO));
                else if (blue.isChecked())
                    bg.setBackgroundColor(getResources().getColor(R.color.AZUL));
            }
        });
    }
}
