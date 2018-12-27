package com.example.mathemaking;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathemaking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2,btn3,btn4,btn5,btn6,btn7,btn8, btn9;
    FirebaseUser user;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()){
            case R.id.menu_exam:
                Toast.makeText(getApplicationContext(),"Exam not ready.",Toast.LENGTH_SHORT).show();

            case R.id.menu_view_progress:
                Toast.makeText(getApplicationContext(),"0% Complete",Toast.LENGTH_SHORT).show();

            case R.id.menu_sign_out:
                mAuth.signOut();
                MainActivity.this.finish();
                startActivity(new Intent(getApplicationContext(), Login.class));

            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivty.class));

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View view) {

        if(view == btn1)
            startActivity(new Intent(MainActivity.this, AlphieCombinations.class));

        if(view == btn2)
            startActivity(new Intent(MainActivity.this, Derivativish.class));

        if(view == btn3)
            startActivity(new Intent(MainActivity.this, DigitalTrading.class));

        if(view == btn4)
            startActivity(new Intent(MainActivity.this, SumsDigitalTrading.class));

        if(view == btn5)
            startActivity(new Intent(MainActivity.this, Ncanyana.class));

        if(view == btn6)
            startActivity(new Intent(MainActivity.this, SpecialDecimals.class));

        if(view == btn7)
            startActivity(new Intent(MainActivity.this, DecimalsNthDegree.class));

        if(view == btn8)
            startActivity(new Intent(MainActivity.this, DecimalsNegativeNthDegree.class));

        if(view == btn9)
            startActivity(new Intent(MainActivity.this, AbridgingCoincidences.class));

    }
}
