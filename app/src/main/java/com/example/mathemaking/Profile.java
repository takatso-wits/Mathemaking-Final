package com.example.mathemaking;

import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Profile extends AppCompatActivity  implements View.OnClickListener {

    private EditText etAge, etName, etSurname;
    private RadioButton radMale, radFem;
    private Button btnSend;
    private DatabaseReference rootRef, mRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Update Profile");
        getSupportActionBar().setHomeButtonEnabled(true);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.et_last_name);
        etAge = findViewById(R.id.date_of_birth);
        radMale = findViewById(R.id.radMale);
        radFem = findViewById(R.id.radFem);
        btnSend = findViewById(R.id.btnSave);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSend){
            String name  = etName.getText().toString().trim();
            String surname = etSurname.getText().toString().trim();
            int age = Integer.parseInt(etAge.getText().toString().trim());
            rootRef = FirebaseDatabase.getInstance().getReference();
            mRoot = rootRef.child("Profile");

            mRoot.push().setValue(name);
            mRoot.push().setValue(surname);
            mRoot.push().setValue(age);

        }

    }
}
