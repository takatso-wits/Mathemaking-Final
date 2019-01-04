package com.example.mathemaking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassoword extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button send;
    EditText email;
    String userEmail;
    final Context context = ResetPassoword.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passoword);

        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setHomeButtonEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        send = findViewById(R.id.btn_ResetPassword);
        email = findViewById(R.id.ed_email);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = email.getText().toString().trim();

                if(userEmail.isEmpty()){
                    email.setError("Feild cannot be empty");
                    return;
                }



                /*Reset the user password*/
                firebaseAuth.sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(ResetPassoword.this, Login.class);
                                final AlertDialog alertDialogBuilder = new AlertDialog.Builder(context).create();
                                if(task.isSuccessful()){
                                            alertDialogBuilder.setTitle("Password Reset");
                                            alertDialogBuilder.setMessage("Email has been sent.");

                                            alertDialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener(){
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                    startActivity(new Intent(context,Login.class));
                                                }
                                            });
                                            alertDialogBuilder.show();
                                    
                                }else{
                                    alertDialogBuilder.setTitle("Password Reset");
                                    alertDialogBuilder.setMessage("Email not found.");

                                    alertDialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE,"Cancel", new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            startActivity(new Intent(context,Login.class));
                                        }
                                    });
                                    alertDialogBuilder.show();
                                }
                            }
                        });
            }
        });
    }
}
