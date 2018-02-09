package com.example.chrcuc.test;

import android.app.Fragment;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chrcuc on 9/02/18.
 */

public class SimpleFragment extends Fragment {
    int rows;
    TextView items = null;
    TextView lugar = null;
    TextView descripcion = null;
    TextView importancia = null;
    TextView id = null;

    public static SimpleFragment newInstance(int row) {
        SimpleFragment f = new SimpleFragment();
        Bundle bundel = new Bundle();

        bundel.putInt("row", row);

        f.setArguments(bundel);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rows = getArguments().getInt("row");


    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;

        v = layoutInflater.inflate(R.layout.detail_item, container, false);
        id = v.findViewById(R.id.identificator);
        items = v.findViewById(R.id.item);
        lugar = v.findViewById(R.id.place);
        importancia = v.findViewById(R.id.importance);
        descripcion = v.findViewById(R.id.description);

        populateFieldsFromDB(rows);

        return v;
    }

    private void populateFieldsFromDB(int rowes) {
        try {
            MainActivity.mDbHelper.open();
            Cursor c = MainActivity.mDbHelper.getItem(rowes);
            if (c.moveToFirst()) {

                items.setText(c.getString(c.getColumnIndexOrThrow(DataBaseHelper.SL_ITEM)));

                lugar.setText(c.getString(c.getColumnIndex(DataBaseHelper.SL_PLACE)));
                descripcion.setText(c.getString(c.getColumnIndex(DataBaseHelper.SL_DESCRIPTION)));

                importancia.setText((c.getString(c.getColumnIndex(DataBaseHelper.SL_IMPORTANCE))));

                id.setText((c.getString(c.getColumnIndex(DataBaseHelper.SL_ID))));

                populateFieldsFromDB(rows);
            }
            c.close();
            MainActivity.mDbHelper.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }


    }
}
