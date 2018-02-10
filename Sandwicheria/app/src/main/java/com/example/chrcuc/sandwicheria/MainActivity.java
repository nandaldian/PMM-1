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
    SQLiteDatabase sqLiteDatabase,sqLiteDatabasepedido ;
    private Spinner spinnerCarta;
    Context context = this;
    private  Sandwiches[] carta;
    String[] columnaspedidos = new String[]{"extras","sandwich","cantidad","precio","envio"};
    String[] columnas = new String[] {"id","nombre","ingredientes","precio"};

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
        spinnerCarta = findViewById(R.id.spinner);


        CartaHelper cartaHelper = new CartaHelper(this,"DBSandwich",null,1);

        sqLiteDatabase = cartaHelper.getWritableDatabase();

        if (sqLiteDatabase == null){
            sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,precio) VALUES ('1','Bacon y Huevo','Bacon/Huevo','1','3') ");
            sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,precio) VALUES ('Cangrejo','Cangrejo/Mayonesa','2','3') ");
            sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,precio) VALUES ('Campiñones','Champiñones/Queso','3','4') ");
            sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,precio) VALUES ('Peperoni','Peperoni/Queso','4','2') ");
            sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,precio) VALUES ('Pollo','Pollo/Queso','5','5') ");
            sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,precio) VALUES ('Esparagos con queso','Esparragos/Queso','6','3') ");
        }



        Cursor cursor = sqLiteDatabase.query("carta", columnas,null,null,null,null,null);

        carta = new Sandwiches[cursor.getCount()];

        int i = 0;
        int a = cursor.getCount();

        System.out.println(a);


        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ingredientes = cursor.getString(2);
                float precio = cursor.getFloat(3);

               carta[i] = new Sandwiches(precio,nombre,ingredientes,id);

               i++;
            }while(cursor.moveToNext());
        }


        CartaArrayAdapter cartaArrayAdapter = new CartaArrayAdapter(this,carta);
        spinnerCarta.setAdapter(cartaArrayAdapter);


        spinnerCarta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String result ="ID:"+Integer.toString(carta[position].getId())+
                        "\nNOMBRE:"+carta[position].getNombre()+
                        "\nPrecio"+ Float.toString(carta[position].getPrecio());
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });




        botonpasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqLiteDatabasepedido.execSQL(PedidoHelper.DATABASE_CREATE_PEDIDO);

                String cantida = tvpizza.getText().toString();
                float canti = Float.parseFloat(cantida);
                int pos = spinnerCarta.getSelectedItemPosition();
                int catida = (int)canti;

                int cont =0;
                float cantidad = carta[pos].getPrecio()*canti;
                float a= añadido(cantidad);
                float b = envio(a);
                float total = b;

                int resultado = (int) total;
                String tipoeenvio = envios();
                String tipoExtra = tipoExtra();
                String sandwich  = carta[pos].getNombre();

                sqLiteDatabasepedido.execSQL("INSERT INTO pedidos (extras,sandwich,cantidad,precio,envio) VALUES ( '" +tipoExtra+"',' "+ sandwich +  "','" +catida +
                        "',' " +resultado+"','"+ tipoeenvio+"') ");

                //Intent miIntent = new Intent(MainActivity.this, Resultado.class);
               // startActivity(miIntent);

          }

            public float añadido(float cont) {
                if (ch1.isChecked())
                    cont++;
                if (ch2.isChecked())
                    cont++;
                if (ch3.isChecked())
                    cont++;
                return
                        cont;
            }

            public float envio(float precio) {
                float total = 0;
                if (r2.isChecked()){
                    total = (float) (precio + precio * 0.1);
                    return total;}
                else
                    return precio;

            }

            public float cantidad(float numero, float precio) {
                float total = numero * precio;
                return total;
            }

            public String envios() {
                String x = "";
                if (r1.isChecked()) {
                    x = "En el local";
                } else if (r2.isChecked()) {
                    x = "Envio domicilio";
                }
                return x;

            }
            String extra = " ";

            public String tipoExtra() {
                if (ch1.isChecked())
                   extra =extra+ "Salsa ";
                if (ch2.isChecked())
                    extra =extra+ "Referesco ";
                if (ch3.isChecked())
                    extra =extra+ "Patatas ";

                return extra;
            }
    });
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
