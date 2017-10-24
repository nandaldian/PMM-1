package com.example.chrcuc.elquequerais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] semana = new String[]{"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner mispinner = (Spinner) this.findViewById(R.id.spinner);
        ArrayAdapter<String> miAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,semana);
        miAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mispinner.setAdapter(miAdapter);


        mispinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override

            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String message = "";
                message = "Item Clicked => " + semana[position];
                showToast(message);
            }
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }

    private void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
