package com.example.chrcuc.ejemploexamen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class MainActivity extends AppCompatActivity {

    static class ViewHolder
    {
        TextView zona;
        TextView continente;
        TextView precio;
    }

    private Pais[] pais = new Pais[]{
            new Pais("Asia y oceania",20,"A", R.drawable.asiaoceania),
            new Pais("America",30,"B",R.drawable.america),
            new Pais("Europa",10,"C",R.drawable.europa)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle paso_datos = new Bundle();
        final EditText tvpeso = (EditText) findViewById(R.id.editText);


        final String mensaje;
        final Spinner miSpinner = (Spinner) findViewById(R.id.spinner);
        class AdaptadorSpinnerZona extends ArrayAdapter {

            Activity context;

            AdaptadorSpinnerZona(Activity context) {
                super(context, R.layout.spinner_helper, pais);
                this.context = context;
            }

            public View getDropDownView(int posicion, View convertView, ViewGroup parent) {//Cuando se despliega el spinner
                return getView(posicion, convertView, parent);
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                View item = convertView;
                ViewHolder holder;

                if (item == null)// para mejorar la ejecucion, si ya existe no infla nuevamente
                {
                    LayoutInflater inflater = context.getLayoutInflater();
                    item = inflater.inflate(R.layout.spinner_helper, null);

                    holder = new ViewHolder();
                    holder.zona = (TextView) item.findViewById(R.id.zona);
                    holder.continente = (TextView) item.findViewById(R.id.nombre);
                    holder.precio = (TextView) item.findViewById(R.id.Precio);

                    item.setTag(holder);

                } else {
                    holder = (ViewHolder) item.getTag();
                }

                holder.zona.setText(pais[position].getZona());
                holder.continente.setText(pais[position].getNombre());
                String a = Double.toString(pais[position].getPrecio());
                holder.precio.setText(a);

                return (item);
            }

        }
        AdaptadorSpinnerZona adaptadorSpin = new AdaptadorSpinnerZona(this);
        miSpinner.setAdapter(adaptadorSpin);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "Zona seleccionada: " + pais[position].getNombre();
                showToast(mensaje);
                paso_datos.putSerializable("PAIS",pais[miSpinner.getSelectedItemPosition()]);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final Button botonpasar = (Button) findViewById(R.id.button);
        final RadioButton r1 = (RadioButton) findViewById(R.id.normal);
        final RadioButton r2 = (RadioButton) findViewById(R.id.urgente);
        final CheckBox ch1 = (CheckBox) findViewById(R.id.checkBox);
        final CheckBox ch2 = (CheckBox) findViewById(R.id.checkBox2);

        botonpasar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double chosed;
                int pos = miSpinner.getSelectedItemPosition();
                double total;
                String peso = tvpeso.getText().toString();
                paso_datos.putString("PESO",peso);
                String añadido;String tarifa;
                double pe = Double.parseDouble(peso);

                    chosed = pais[pos].getPrecio();
                    total = comprobar(chosed);
                    chosed = pesotarifa(total);
                    String g=Double.toString(chosed);
                    añadido = adicional();
                    tarifa = tarifa();
                    paso_datos.putString("TARIFA",tarifa);
                    paso_datos.putString("ADICIONAL", añadido);
                    paso_datos.putString("PRECIO", g);

                Intent miIntent = new Intent(MainActivity.this, Main2Activity.class);
                miIntent.putExtras(paso_datos);
                startActivity(miIntent);
            }

            public String adicional() {
                String respuesta;
                if (ch1.isChecked() && !ch2.isChecked()) {
                    return respuesta = "Con caja regalo";
                } else if (ch2.isChecked() && !ch1.isChecked()) {
                    return respuesta = "Con Dedicatoria";
                } else if (ch2.isChecked() && ch1.isChecked()) {
                    return respuesta = "Con caja regalo y dedicatoria";
                } else
                    return respuesta = "Sin nada";

            }

            public double pesotarifa(double kg) {
                if (kg < 6.00) {
                    kg = kg * 1;

                } else if (kg >= 6.00 && kg <= 10.00) {
                    kg = kg * 1.5;
                } else {
                    kg = kg * 2.00;
                }
                return kg;
            }

            public double comprobar(double precio) {
                if (r2.isChecked()){
                    precio = precio + precio * 0.3;
                return precio;}
                else return precio;
            }
            public String tarifa(){
                String x="";
                if (r1.isChecked()){
                    x="Tarifa normal";
                }else if (r2.isChecked()){
                    x="Tarifa Urgente";
                }
                return x;
            }
        });
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
                case R.id.Dibujo:
                    Intent two = new Intent(MainActivity.this, Picture.class);
                    startActivity(two);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }



    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

   }

