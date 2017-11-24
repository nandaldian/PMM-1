package com.example.chrcuc.figurasaleatorias;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void launchDrawShapes1(View clickedButton) {
            Intent activityIntent =
                    new Intent(this, DrawShape1.class);
            startActivity(activityIntent);
        }

        public void launchDrawShapes2(View clickedButton) {
            Intent activityIntent =
                    new Intent(this, DrawShapes2.class);
            startActivity(activityIntent);
        }
    }

