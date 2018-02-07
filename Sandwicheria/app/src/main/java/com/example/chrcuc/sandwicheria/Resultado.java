package com.example.chrcuc.sandwicheria;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Resultado extends ListActivity {
    public static final int NEW_ITEM = 1;
    public static final int EDIT_ITEM = 2;
    public static final int SHOW_ITEM = 3;

    //elemento seleccionado
    private int mLastRowSelected = 0;
    public static DataBaseHelper mDbHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        mDbHelper = new DataBaseHelper(this);
        try {
            fillData();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
        registerForContextMenu(getListView());
    }

    private void showMessage(int message){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

//    protected void deleteEntry() {
//        try{
//            mDbHelper.open();
//            mDbHelper.delete(((ListEntry)getListAdapter().getItem(mLastRowSelected)).food);
//            mDbHelper.close();
//            fillData();
//        }catch (SQLException e){
//            e.printStackTrace();
//            showMessage(R.string.dataError);
//        }
//    }

    private void fillData() {

        mDbHelper.open();
        Cursor itemCursor = mDbHelper.getItems();
        ListEntry item = null;
        ArrayList<ListEntry> resultList = new ArrayList<ListEntry>();
        while (itemCursor.moveToNext()) {
            int cantidad = itemCursor.getInt(itemCursor.getColumnIndex(DataBaseHelper.SL_CANTIDAD));
            String extras = itemCursor.getString(itemCursor.getColumnIndex(DataBaseHelper.SL_EXTRAS));
            String envio = itemCursor.getString(itemCursor.getColumnIndex(DataBaseHelper.SL_ENVIO));
            float precio = itemCursor.getInt(itemCursor.getColumnIndex(DataBaseHelper.SL_PRICE));
            String food = itemCursor.getString(itemCursor.getColumnIndex(DataBaseHelper.SL_ID_FOOD));
            item = new ListEntry();
            item.cantidad = cantidad;
            item.extras = extras;
            item.envio = envio;
            item.precio= precio;
            item.food = food;

            resultList.add(item);
        }
        itemCursor.close();
        mDbHelper.close();

        TaskAdapter items = new TaskAdapter(this, R.layout.row_list, resultList, getLayoutInflater());

        setListAdapter(items);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_item:
                Intent intent = new Intent (this,MainActivity.class);
                startActivityForResult(intent, NEW_ITEM);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, MainActivity.class);
        int rowId = (Integer)v.findViewById(R.id.row_importance).getTag();
        i.putExtra(DataBaseHelper.SL_ID_FOOD, rowId);
        i.putExtra("action", SHOW_ITEM);
        startActivityForResult(i, SHOW_ITEM);
    }


    private class TaskAdapter extends ArrayAdapter<ListEntry> {
        private LayoutInflater mInflater;
        private List<ListEntry> mObjects;

        private TaskAdapter(Context context, int resource, List<ListEntry> objects, LayoutInflater mInflater) {
            super(context, resource, objects);
            this.mInflater = mInflater;
            this.mObjects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListEntry listEntry = mObjects.get(position);
            // obtención de la vista de la línea de la tabla
            View row = mInflater.inflate(R.layout.row_list, null);
            //rellenamos datos
            TextView extras = (TextView) row.findViewById(R.id.row_extras);
            TextView cantidad = (TextView) row.findViewById(R.id.row_cantidad);
            TextView envio = (TextView) row.findViewById(R.id.row_envio);
            TextView precio = (TextView) row.findViewById(R.id.row_precio);
            TextView sandwich = (TextView) row.findViewById(R.id.row_sandwich);
            extras.setText(listEntry.extras);
            cantidad.setText(Integer.toString(listEntry.cantidad));
            envio.setText(listEntry.envio);
            precio.setText(Float.toString(listEntry.precio));
            sandwich.setText(listEntry.food);

            // dependiendo de la importancia, se muestran distintos iconos
//            ImageView icon = (ImageView) row.findViewById(R.id.row_importance);
//            icon.setTag(listEntry.food);
//            switch (listEntry.importance) {
//                case 1:
//                    icon.setImageResource(R.drawable.ic_green);
//                    break;
//                case 2:
//                    icon.setImageResource(R.drawable.ic_yellow);
//                    break;
//                default:
//                    icon.setImageResource(R.drawable.ic_red);
//                    break;
//            }
            return row;
        }
    }
    private class ListEntry {
        String extras;
        String food;
        int  cantidad;
        float precio;
        String envio;

    }

}

