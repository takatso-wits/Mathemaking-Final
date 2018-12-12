package com.example.mathemaking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;

public class AlphieNotes extends AppCompatActivity {

    private VideoView vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphie_notes);

        vid = findViewById(R.id.intro);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.introduction);
        vid.setVideoURI(uri);



        vid.start();
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, AlphieCombinations.class);
        startActivity(intent);
    }
}
