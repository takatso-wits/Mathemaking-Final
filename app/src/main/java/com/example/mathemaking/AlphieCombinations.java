package com.example.mathemaking;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

public class AlphieCombinations extends AppCompatActivity implements View.OnClickListener {

    VideoView alphieVideo;
    Button alphieDone, readMode;
    ScrollView scrollView;
    MediaController mediaController;
    private String video;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        alphieDone.setVisibility(View.INVISIBLE);
        getSupportActionBar().hide();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphie_combinations);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alphieVideo = findViewById(R.id.vid_alphie_intro);
        alphieDone = findViewById(R.id.btn_alphieDone);
        readMode = findViewById(R.id.btnReadMode);
        scrollView = findViewById(R.id.scrollView2);
        mediaController = new MediaController(this);
        video = "https://firebasestorage.googleapis.com/" +
                "v0/b/mathemaking.appspot.com/o/3v5gb.mp4" +
                "?alt=media&token=9a039fb3-ab3b-44bc-a1f9-07e9b9c8b963";

        Uri uri = Uri.parse(video);
        alphieVideo.setVideoURI(uri);
        alphieVideo.setMediaController(mediaController);
        mediaController.setAnchorView(alphieVideo);
        alphieVideo.start();

        readMode.setOnClickListener(this);
        alphieDone.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if(view == alphieDone){
            startActivity(new Intent(AlphieCombinations.this, Derivativish.class));
            AlphieCombinations.this.finish();
        }
        if(view == readMode){
            startActivity(new Intent(AlphieCombinations.this, AlphieNotes.class));
        }
    }
}
