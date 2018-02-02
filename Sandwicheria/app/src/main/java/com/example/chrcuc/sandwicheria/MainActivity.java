package com.example.chrcuc.sandwicheria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
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
    DatabaseHelper mDbHelper;
    static class ViewHolder
    {
        TextView nombre;
        TextView precio;
        TextView ingre;
        ImageView img;
    }
    private Sandwiches[] pizza = new Sandwiches[]{
            new Sandwiches("Bacon y Huevo",12,"Bacon/Huevo",R.drawable.baconhuevo),
            new Sandwiches("Cangrejo",15,"Cangrejo/Mayonesa",R.drawable.cangrejo),
            new Sandwiches("Campiñones",14,"Champiñones/Queso",R.drawable.champinyones),
            new Sandwiches("Peperoni",13,"Peperoni/Queso",R.drawable.peperoni),
            new Sandwiches("Pollo",13,"Pollo/Queso",R.drawable.pollo),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Haz tu pedido");

        mDbHelper = new DatabaseHelper(this);
        try {
            fillData();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(R.string.dataError);
        }


        final Bundle paso_datos = new Bundle();
        final EditText tvpizza = findViewById(R.id.editText);
        final Spinner miSpinner = findViewById(R.id.spinner);

        class AdaptadorSpinnerSandwich extends ArrayAdapter {

            Activity context;

            AdaptadorSpinnerSandwich(Activity context) {
                super(context, R.layout.spinner_helper, pizza);
                this.context = context;
            }

            public View getDropDownView(int posicion, View convertView, ViewGroup parent) {
                return getView(posicion, convertView, parent);
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                View item = convertView;
                ViewHolder holder;

                if (item == null) {
                    LayoutInflater inflater = context.getLayoutInflater();
                    item = inflater.inflate(R.layout.spinner_helper, null);

                    holder = new ViewHolder();
                    holder.nombre = (TextView) item.findViewById(R.id.nombre);
                    holder.ingre = (TextView) item.findViewById(R.id.ingredientes);
                    holder.precio = (TextView) item.findViewById(R.id.Precio);
                    holder.img = (ImageView) item.findViewById(R.id.image);

                    item.setTag(holder);

                } else {
                    holder = (ViewHolder) item.getTag();
                }

                holder.nombre.setText(pizza[position].getNombre());
                holder.ingre.setText(pizza[position].getIngredientes());
                String a = Integer.toString(pizza[position].getPrecio());
                holder.precio.setText(a);
                holder.img.setImageResource(pizza[position].getFoto());

                return (item);
            }
        }
        AdaptadorSpinnerSandwich adaptadorSpin = new AdaptadorSpinnerSandwich(this);
        miSpinner.setAdapter(adaptadorSpin);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                paso_datos.putSerializable("PIZZA", pizza[miSpinner.getSelectedItemPosition()]);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final Button botonpasar = findViewById(R.id.button);
        final RadioButton r1 = findViewById(R.id.rb1);
        final RadioButton r2 = findViewById(R.id.rb2);
        final CheckBox ch1 = findViewById(R.id.cb1);
        final CheckBox ch2 = findViewById(R.id.cb2);
        final CheckBox ch3 = findViewById(R.id.cb3);

        botonpasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cantidad = tvpizza.getText().toString();
                paso_datos.putString("CANTIDAD", cantidad);
                int canti = Integer.parseInt(cantidad);
                int pos = miSpinner.getSelectedItemPosition();
                int precio = pizza[pos].getPrecio();
                int cont =0;

                int paso1 = añadido(cont);
                String comple = Integer.toString(paso1);
                paso_datos.putString("EXTRAS", comple);
                int paso2 = cantidad(canti, precio);
                double total = envio(paso2 + paso1);

                String resultado = Double.toString(total);
                String tipoeenvio = envios();
                paso_datos.putString("TOTAL", resultado);
                paso_datos.putString("ENVIO", tipoeenvio);


                Intent miIntent = new Intent(MainActivity.this, Resultado.class);
                miIntent.putExtras(paso_datos);
                startActivity(miIntent);

            }

            public int añadido(int cont) {
                if (ch1.isChecked())
                    cont++;
                if (ch2.isChecked())
                    cont++;
                if (ch3.isChecked())
                    cont++;
                return cont;
            }

            public double envio(int precio) {
                double total = 0;
                if (r2.isChecked()){
                    total = precio + precio * 0.1;
                    return total;}
                else
                    return precio;

            }

            public int cantidad(int numero, int precio) {
                int total = numero * precio;
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
