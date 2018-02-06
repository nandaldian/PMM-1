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
    Sandwiches[] sandwiches;
    private TextView textViewPrecio, textViewNombre,textViewIngredientes,TVid;
    ImageView imageView ;



    public CartaArrayAdapter(Context context, Sandwiches[] sandwiches) {
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
        textViewPrecio = view.findViewById(R.id.Precio);
        textViewNombre =  view.findViewById(R.id.nombre);
        textViewIngredientes =  view.findViewById(R.id.ingredientes);
        TVid = view.findViewById(R.id.ID);

        imageView= view.findViewById(R.id.image);

        String cast = Float.toString(sandwiches[position].getPrecio());

        String cast2 = Integer.toString(sandwiches[position].getId());



        textViewPrecio.setText(cast);
        textViewNombre.setText(sandwiches[position].getNombre());
        textViewIngredientes.setText(sandwiches[position].getIngredientes());
        TVid.setText(cast2);

        switch (sandwiches[position].getId()){
            case 1:
                imageView.setImageResource(R.drawable.baconhuevo);
                break;
            case 2:
                imageView.setImageResource(R.drawable.cangrejo);
                break;
            case 3:
                imageView.setImageResource(R.drawable.champinyones);
                break;
            case 4:
                imageView.setImageResource(R.drawable.peperoni);
                break;
            case 5:
                imageView.setImageResource(R.drawable.pollo);
                break;
            case 6:
                imageView.setImageResource(R.drawable.queso_esparragos);
                break;
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }


}