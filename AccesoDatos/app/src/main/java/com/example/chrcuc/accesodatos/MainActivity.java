package com.example.chrcuc.accesodatos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static class ViewHolder
    {
        TextView zona;
        TextView continente;
    }
    private Clientes[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner miSpinner = (Spinner) findViewById(R.id.spinnner);

        ClientesSQLiteHelper clientesSQLiteHelper = new ClientesSQLiteHelper(this, "DBClientes", null, 1);
        SQLiteDatabase sqLiteDatabase = clientesSQLiteHelper.getWritableDatabase();

        if (sqLiteDatabase != null) {
            for (int cont = 1; cont <= 3; cont++) {
                int codigo = cont;
                String nombre = "Cli" + cont;
                String telefono = cont + "XXXXXXXX";
                sqLiteDatabase.execSQL("INSERT INTO Clientes (codigo,nombre,telefono) " + "VALUES (" + codigo + ",'" + nombre + "', '" + telefono + "')");
            }
        }

        // sqLiteDatabase.execSQL("INSERT INTO Clientes (nombre,telefono) VALUES ('cli1','1111')");

        //sqLiteDatabase.execSQL("UPDATE Clientes SET telefono='00000' WHERE nombre='cli1' ");

        //sqLiteDatabase.execSQL("DELETE FROM  Clientes  WHERE nombre='cli1' ");

        //INSERT()

//        ContentValues contentValues = new ContentValues();
//        contentValues.put("nombre","cli10");
//        contentValues.put("telefono","121212");
//        sqLiteDatabase.insert("Clientes",null,contentValues);

        //ContentValues contentValues1 = new ContentValues();

        //contentValues1.put("telefono","123223423");
        String[] args = new String[]{"cli1"};
        // sqLiteDatabase.update("Clientes",contentValues1,"nombre=? OR nombre=?",args);

        String[] campos = new String[]{"nombre", "telefono"};
        Cursor cursor = sqLiteDatabase.query("Clientes", campos, "nombre=?", args, null, null, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String nomCli = cursor.getString(0);
                String teleCli = cursor.getString(1);
                datos[i] = new Clientes(nomCli, teleCli);
                i++;
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();

        AdaptadorSpinnerZona adaptadorSpin = new AdaptadorSpinnerZona(this);
        miSpinner.setAdapter(adaptadorSpin);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String mensaje = "Nombre: " + datos[i].getNombre() + " Telefono: " + datos[i].getTelefono();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

        class AdaptadorSpinnerZona extends ArrayAdapter {

            Activity context;

            AdaptadorSpinnerZona(Activity context) {
                super(context, R.layout.spinner_helper, datos);
                this.context = context;
            }

            public View getDropDownView(int posicion, View convertView, ViewGroup parent) {
                return getView(posicion, convertView, parent);
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                View item = convertView;
                ViewHolder holder;
                if (item == null)
                {
                    LayoutInflater inflater = context.getLayoutInflater();
                    item = inflater.inflate(R.layout.spinner_helper, null);

                    holder = new ViewHolder();
                    holder.zona = (TextView) item.findViewById(R.id.telefono);
                    holder.continente = (TextView) item.findViewById(R.id.nombre);


                    item.setTag(holder);


                } else {
                    holder = (ViewHolder) item.getTag();
                }
                holder.zona.setText(datos[position].getNombre());
                holder.continente.setText(datos[position].getTelefono());


                return (item);
            }

        }


    }

