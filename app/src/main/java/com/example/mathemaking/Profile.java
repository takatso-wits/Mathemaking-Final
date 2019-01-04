package com.example.mathemaking;

import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class Profile extends AppCompatActivity  implements View.OnClickListener {

    private EditText etDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Update Profile");
        getSupportActionBar().setHomeButtonEnabled(true);

        etDOB = findViewById(R.id.date_of_birth);
        etDOB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == etDOB){



        }
    }
}
