package com.example.mathemaking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private EditText etUsername, etPassword;
    private CheckBox checkBox;
    private Button btnRegister;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Initialize the views*/
        initialize();

        /*Check whether the user is already logged in*/
        if(firebaseAuth.getCurrentUser() != null){
            /*Start the Journal activity*/
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        btnRegister.setOnClickListener(this);

    }

    private void initialize() {
        etUsername = (EditText)findViewById(R.id.et_username);
        etPassword = (EditText)findViewById(R.id.et_password);
        checkBox = (CheckBox) findViewById(R.id.checkBx);
        btnRegister = (Button)findViewById(R.id.btn_reg);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister){
            doRegistration();
        }
    }

    private void doRegistration() {

        String nameError = "Name field cannot be empty";
        String emailError = "Email field cannot be empty";
        String passwordError = "Password field cannot be empty";
        final String progressMessage = "Registration underway...";
        Context context = getApplicationContext();

        String username, password;

        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        /*Validate input*/
        if(TextUtils.isEmpty(username)){
            Toast.makeText(context,emailError,Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,passwordError,Toast.LENGTH_SHORT).show();
            return;
        }

        /*Straight to Firebase*/
        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener((Activity) getApplicationContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            /*Start Journal Activity*/
                            Intent intent = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Could not Login...",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
