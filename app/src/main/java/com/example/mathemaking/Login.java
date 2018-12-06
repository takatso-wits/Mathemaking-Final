package com.example.mathemaking;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView tv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        init();
        btnLogin.setOnClickListener(this);
        tv.setOnClickListener(this);
    }

    private void init() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        tv = (TextView)findViewById(R.id.tv_login);
        etUsername = (EditText)findViewById(R.id.et_username);
        etPassword = (EditText)findViewById(R.id.et_password);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            doLogin();
        }
        if(v == tv){
            Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(intent);

        }
    }

    private void doLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String emailError = "Email field cannot be empty";
        String passwordError = "Password field cannot be empty";
        Context context = getApplicationContext();
        String  message = "Getting started...";

        if(TextUtils.isEmpty(username)){
            Toast.makeText(context,emailError,Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,passwordError,Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setTitle("Loggin in");
        progressDialog.setMessage(message);
        progressDialog.show();

        /*Firebase Auth Login*/
        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isComplete()){

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
