package com.example.chrcuc.good;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //PARA LISTAS
    private Titular[] datos = new Titular[]{
            new Titular("Titulo 1", "Subtitulo 1", R.drawable.descarga),
            new Titular("Titulo 2", "Subtitulo 2", R.drawable.aaa),
            new Titular("Titulo 3", "Subtitulo 3", R.drawable.images)
    };

    //PARA SPINERS
//    public Spinner miSpinner1;
//    private Persona[] personas = new Persona[]{
//            new Persona ("Angeles","Campos",20), new Persona ("Consuelo","Martin",20),
//            new Persona("Fernando","Molina",26)
//
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//PARA LISTAS
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        ListView itsOpciones = (ListView) findViewById(R.id.LstOpciones);
        itsOpciones.setAdapter(adaptador);

        itsOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int posicion, long id) {


                Titular T =new Titular(datos[posicion].getTitulo(),datos[posicion].getSubtitulo(),datos[posicion].getFoto());

                Intent pantalla = new Intent(MainActivity.this,Pantalla2.class);
                Bundle mibundle=new Bundle();
                mibundle.putSerializable("TitularKey",T);
                pantalla.putExtras(mibundle);
                startActivity(pantalla);

//En el transcurso de pantallas peta la aplicacion

               // Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });

//PARA SPINNERS
//        miSpinner1 = (Spinner)findViewById(R.id.miSpinner);
//        AdaptadorPersona miAdapter = new AdaptadorPersona(this);
//        miSpinner1.setAdapter(miAdapter);
//
//
//        miSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
//                String mssg = " ";
//                mssg = "Item Clicked => "+personas[i];
//                showToast(mssg);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    //PARA LISTAS
    class AdaptadorTitulares extends ArrayAdapter {
        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.listitem_titular, datos);
            this.context = context;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public View getView(int i, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolder holder ;
            if(item == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.listitem_titular, null);

                holder = new ViewHolder();
                holder.titulo = (TextView)item.findViewById(R.id.LblTitulo);
                holder.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
                holder.imagen = (ImageView)item.findViewById(R.id.ivlImage);

                item.setTag(holder);
            }
            else {holder = (ViewHolder) item.getTag();}

                holder.titulo.setText(datos[i].getTitulo());

                holder.subtitulo.setText(datos[i].getSubtitulo());

                holder.imagen.setBackground(getDrawable(datos[i].getFoto()));


            return (item);
        }
    }
    //PARA LISTAS
    static  class  ViewHolder{
        TextView titulo;
        TextView subtitulo;
        ImageView imagen;
    }



    //PARA SPINNERS
 //   static char ViewHolderSpinner{
 //       TextView nombre;
 //       TextView apellido;
 //       TextView edad;
 //   }
//class AdaptadorPersona extends  ArrayAdapter<Persona>{
//    public  Activity myActivity;
//
//    public  AdaptadorPersona(Activity laActividad){
//        super(laActividad, R.layout.desmilista,personas);
//        this.myActivity =  laActividad;
//    }
//
//    public View getDropDownView(int position, View convertview ,ViewGroup parent){
//        View vistaDesp = getView(position, convertview, parent);
//        return vistaDesp;
//    }
//    public View getView(int position,View convertView,ViewGroup parent)
//      View item = convertView;
//      ViewHolderSpinner holder;
//      if(item == null){
//        LayoutInflater inflater = myActivity.getLayoutInflater();
//        item = inflater.inflate(R.layout.desmilista,null);

//        holder = new ViewHolder();
//        holder.titulo = (TextView)item.findViewById(R.id.nombre);
//        holder.subtitulo = (TextView)item.findViewById(R.id.apellido);
//        holder.imagen = (ImageView)item.findViewById(R.id.edad);

//        item.setTag(holder);
//}
// else{holder = (ViewHolderSpinner) item.getTag();}
//
//        holder.nombre.setText(personas[position].getNombre());

//        holder.apellido.setText(personas[position].getApellido());

//        holder.edad.setText(personas[position].getEdad());
//
//
//        return  item;
//    }
//}
}