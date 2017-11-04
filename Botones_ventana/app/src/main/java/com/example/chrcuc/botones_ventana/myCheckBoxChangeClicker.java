package com.example.chrcuc.botones_ventana;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * Created by chrcuc on 6/10/17.
 */

class myCheckBoxChangeClicker implements CheckBox.OnCheckedChangeListener {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        if (isChecked) {
            if (buttonView == chkBoxCyclingling) {
                showTextNotification("Cycling");
            }
            if (buttonView == chkBoxTeaching) {
                showTextNotification("Teaching");
            }
            if (buttonView == chkBoxBlogging) {
                showTextNotification("BlackBlogging");
            }

        }
    }

    public void showTextNotification(String msgToDisplay) {
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_LONG).show();
    }
}
