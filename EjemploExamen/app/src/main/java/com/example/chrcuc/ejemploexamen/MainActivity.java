package com.example.chrcuc.ejemploexamen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pais Asia_Oceania = new Pais("Asia y oceania",20);
        Pais America = new Pais("America",30);
    }
}
