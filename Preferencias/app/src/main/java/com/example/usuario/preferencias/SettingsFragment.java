package com.example.usuario.preferencias;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by usuario on 05/02/2018.
 */

public class SettingsFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.oprions);
    }
}