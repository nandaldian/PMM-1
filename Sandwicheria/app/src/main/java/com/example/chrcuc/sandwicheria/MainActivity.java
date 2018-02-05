package com.example.chrcuc.sandwicheria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerCarta;
    private  ArrayList<Sandwiches> carta= new ArrayList<Sandwiches>();
    String[] columnas = new String[] {"id","ingredientes","nombre", "precio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Haz tu pedido");

        final Button botonpasar = findViewById(R.id.button);
        final RadioButton r1 = findViewById(R.id.rb1);
        final RadioButton r2 = findViewById(R.id.rb2);
        final CheckBox ch1 = findViewById(R.id.cb1);
        final CheckBox ch2 = findViewById(R.id.cb2);
        final CheckBox ch3 = findViewById(R.id.cb3);
        final EditText tvpizza = findViewById(R.id.editText);

        CartaHelper cartaHelper = new CartaHelper(this,"DBSandwich",null,1);
        spinnerCarta = findViewById(R.id.spinner);
        SQLiteDatabase sqLiteDatabase ;
        sqLiteDatabase = cartaHelper.getWritableDatabase();

        if (sqLiteDatabase != null){
            cartaHelper.fillCarta(sqLiteDatabase);
        }

        sqLiteDatabase = cartaHelper.getReadableDatabase();





        Cursor cursor = sqLiteDatabase.query("carta", columnas,null,null,null,null,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ingredientes = cursor.getString(2);
                float precio = cursor.getFloat(4);
                carta.add(new Sandwiches(id,nombre,ingredientes,precio));
            }while(cursor.moveToNext());
        }

        CartaArrayAdapter cartaArrayAdapter = new CartaArrayAdapter(this,carta);
        spinnerCarta.setAdapter(cartaArrayAdapter);


        spinnerCarta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String result ="ID:"+carta.get(position).getId()+
                        "\nNOMBRE:"+carta.get(position).getNombre()+
                        "\nPrecio"+ carta.get(position).getPrecio();
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });







//
//        botonpasar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String cantidad = tvpizza.getText().toString();
//                int canti = Integer.parseInt(cantidad);
//                int pos = spinnerCarta.getSelectedItemPosition();
//                //TODO insert pedido
//                int cont =0;
//                //TODO insert pedido
//                int paso1 = añadido(cont);
//                String comple = Integer.toString(paso1);
//              //TODO insert pedido
//                //double total = envio(paso2 + paso1);
//
//               // String resultado = Double.toString(total);
//                String tipoeenvio = envios();
//
//
//
//                Intent miIntent = new Intent(MainActivity.this, Resultado.class);
//                startActivity(miIntent);
//
//            }
//
//            public int añadido(int cont) {
//                if (ch1.isChecked())
//                    cont++;
//                if (ch2.isChecked())
//                    cont++;
//                if (ch3.isChecked())
//                    cont++;
//                return cont;
//            }
//
//            public double envio(int precio) {
//                double total = 0;
//                if (r2.isChecked()){
//                    total = precio + precio * 0.1;
//                    return total;}
//                else
//                    return precio;
//
//            }
//
//            public int cantidad(int numero, int precio) {
//                int total = numero * precio;
//                return total;
//            }
//
//            public String envios() {
//                String x = "";
//                if (r1.isChecked()) {
//                    x = "En el local";
//                } else if (r2.isChecked()) {
//                    x = "Envio domicilio";
//                }
//                return x;
//
//            }
 //       });
  }
    private void showMessage(int message){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.AcercaDe:
                Intent one = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(one);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
