package com.example.mathemaking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activty);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
