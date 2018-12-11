package com.example.mathemaking;

import android.app.Dialog;
import android.content.Context;
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
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passoword);

        firebaseAuth = FirebaseAuth.getInstance();
        send = (Button) findViewById(R.id.btn_ResetPassword);
        email = (EditText)findViewById(R.id.ed_email);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = email.getText().toString().trim();



                /*Reset the user password*/
                firebaseAuth.sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(ResetPassoword.this, Login.class);
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }else{
                                    final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.custom);
                                    final TextView tv = (TextView) dialog.findViewById(R.id.custom_text);
                                    dialog.setTitle("Invalid Email");
                                    tv.setText("What Should come here?");
                                    Button dBtn = (Button) dialog.findViewById(R.id.dialogButtonOK);
                                    dBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                            startActivity(new Intent(ResetPassoword.this, Login.class));
                                        }
                                    });
                                    dialog.show();

                                }
                            }
                        });
            }
        });
    }
}
