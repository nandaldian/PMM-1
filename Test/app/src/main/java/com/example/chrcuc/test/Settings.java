package com.example.chrcuc.test;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by chrcuc on 9/02/18.
 */

public class Settings extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.options);
    }
}