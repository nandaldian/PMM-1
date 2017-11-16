package com.example.chrcuc.ejemploexamen;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private Pais pais;
    TextView nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        TextView tar = (TextView)findViewById(R.id.TVtarifa);
        TextView dec = (TextView)findViewById(R.id.TVdecoracion);
        TextView peso = (TextView)findViewById(R.id.TVpeso);
        TextView coste = (TextView)findViewById(R.id.TVcoste);
        ImageView img = (ImageView)findViewById(R.id.imageView);
        nom =(TextView)findViewById(R.id.TVzona);

        pais = (Pais)bundle.getSerializable("PAIS");

        nom.setText(pais.getZona()+" : " +pais.getNombre());
        dec.setText(bundle.getString("ADICIONAL"));
        tar.setText(bundle.getString("TARIFA"));
        peso.setText(bundle.getString("PESO"));
        coste.setText(bundle.getString("PRECIO"));
        img.setImageResource(pais.getFoto());

        registerForContextMenu(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.showContextMenu();
            }  });

    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle(pais.getNombre());
        inflater.inflate(R.menu.menu_ctx, menu);
    }
    public boolean onContextItemSelected(MenuItem itemMnuContex) {
        nom=(TextView)findViewById(R.id.TVzona);
        switch (itemMnuContex.getItemId()) {
            case R.id.Pais1:
                Intent intentone= new Intent(Main2Activity.this,Asia.class);
                startActivity(intentone);
                return true;
            case R.id.Pais2:
                Intent intent= new Intent(Main2Activity.this,America.class);
                startActivity(intent);
                return true;
            case R.id.Pais3:
                Intent intent3= new Intent(Main2Activity.this,Europe.class);
                startActivity(intent3);
                return true;

            default:
                return super.onContextItemSelected(itemMnuContex);
        }
    }



    }

