package com.example.mathemaking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivty extends AppCompatActivity {

    private Switch sw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activty);
        getSupportActionBar().setHomeButtonEnabled(true);

        sw1 = findViewById(R.id.switch1);

        if(sw1.isEnabled()){
            Toast.makeText(getApplicationContext(), "Enaabled",Toast.LENGTH_LONG);
        }

    }
}
