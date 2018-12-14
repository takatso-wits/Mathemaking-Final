package com.example.mathemaking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
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
    private TextView tv, tv_resetPassword;
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
        tv_resetPassword.setOnClickListener(this);
    }

    private void init() {
        btnLogin = findViewById(R.id.btn_login);
        tv = findViewById(R.id.tv_login);
        tv_resetPassword = findViewById(R.id.tv_resetPassword);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            doLogin();
        }
        if(v == tv){
            Intent intent = new Intent(Login.this, RegistrationActivity.class);
            startActivity(intent);

        }
        if(v == tv_resetPassword){
            startActivity(new Intent(Login.this, ResetPassoword.class));
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
            etUsername.setError(emailError);
            return;
        }
        if(TextUtils.isEmpty(password)){
            etPassword.setError(passwordError);

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

                        if(task.isSuccessful()){
                            startActivity(new Intent(Login.this,MainActivity.class));
                            Login.this.finish();

                        }else{
                            Toast.makeText(Login.this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}
