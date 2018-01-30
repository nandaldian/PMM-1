package com.example.chrcuc.comoquieras2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spiner1 = (Spinner) findViewById(R.id.spinner1);
        spiner1.setOnItemSelectedListener(new SpinnerInfo());

    private void showToast(String text) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    private class SpinnerInfo implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> spinner, View selectedView,
                                int selectedIndex, long id) {
            String selection = spinner.getItemAtPosition(selectedIndex).toString();
            showToast(selection);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }


    }
    public void onNothingSelected(AdapterView<?> lista) {
        // Won’t be invoked unless you programmatically remove entries
    }
    }
