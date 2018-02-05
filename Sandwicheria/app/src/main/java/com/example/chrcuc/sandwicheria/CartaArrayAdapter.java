package com.example.chrcuc.sandwicheria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
/**
 * Created by usuario on 05/02/2018.
 */

public class CartaArrayAdapter extends ArrayAdapter<Sandwiches>{
    Context context;
    List<Sandwiches> sandwiches;
    private TextView textViewPrecio, textViewNombre,textViewIngredientes,TVid ;



    public CartaArrayAdapter(Context context, List<Sandwiches> sandwiches) {
        super(context, R.layout.spinner_helper, sandwiches);
        this.context = context;
        this.sandwiches= sandwiches ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null){
            view = inflater.inflate(R.layout.spinner_helper,null);
        }
        textViewPrecio =  view.findViewById(R.id.Precio);
        textViewNombre =  view.findViewById(R.id.nombre);
        textViewIngredientes =  view.findViewById(R.id.ingredientes);
        TVid = view.findViewById(R.id.ID);


        textViewPrecio.setText(String.valueOf(sandwiches.get(position).getPrecio()));
        textViewNombre.setText(sandwiches.get(position).getNombre());
        textViewIngredientes.setText(sandwiches.get(position).getIngredientes());
        TVid.setText(sandwiches.get(position).getId());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }


}