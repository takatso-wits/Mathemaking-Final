package com.example.mathemaking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivty extends AppCompatActivity implements View.OnClickListener {

    private Switch sw1;
    private Button updateProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activty);
        getSupportActionBar().setHomeButtonEnabled(true);

        sw1 = findViewById(R.id.switch1);
        updateProfile = findViewById(R.id.btn_update);

        if(sw1.isEnabled()){
            Toast.makeText(getApplicationContext(), "Enaabled",Toast.LENGTH_LONG);
        }
        updateProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == updateProfile){
            startActivity(new Intent(SettingsActivty.this, Profile.class));

        }
    }
}
