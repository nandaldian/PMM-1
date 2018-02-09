package com.example.chrcuc.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class options extends AppCompatActivity {
    Button buttonok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new Settings());
        ft.commit();


    }
}