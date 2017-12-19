package com.example.chrcuc.fragments;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragmento_ejemplo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup group,Bundle bundle){
        return layoutInflater.inflate(R.layout.activity_fragmento_ejemplo,group,false);
    }
}
