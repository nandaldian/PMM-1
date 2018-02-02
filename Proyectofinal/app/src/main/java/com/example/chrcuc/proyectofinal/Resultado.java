package com.example.chrcuc.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    private Sandwich sandwich;
    TextView nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        this.setTitle("Tu pedido");

        Bundle bundle = getIntent().getExtras();
        TextView extras = (TextView)findViewById(R.id.TVextra);
        TextView Envio = (TextView)findViewById(R.id.TVEnvio);
        TextView cantidad = (TextView)findViewById(R.id.TVunidades);
        TextView coste = (TextView)findViewById(R.id.TVcoste);
        TextView preciobase = (TextView)findViewById(R.id.TVprecio);
        ImageView img = (ImageView)findViewById(R.id.imageView);
        nom =(TextView)findViewById(R.id.TVpizza);
        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        final DigitalClock dc = (DigitalClock)findViewById(R.id.reloj);
        sandwich = (Sandwich) bundle.getSerializable("PIZZA");
        String a = Integer.toString(sandwich.getPrecio());
        nom.setText(sandwich.getNombre());
        preciobase.setText(a);
        extras.setText(bundle.getString("EXTRAS"));
        cantidad.setText(bundle.getString("CANTIDAD"));
        Envio.setText(bundle.getString("ENVIO"));
        coste.setText(bundle.getString("TOTAL"));
        img.setImageResource(sandwich.getFoto());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()){
                    dc.setVisibility(View.VISIBLE);
                }else {
                    dc.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}
