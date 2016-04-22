package com.ivica.zzzzzzzz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MusicPlayer extends AppCompatActivity {

    private Toolbar toolbar;
    Button play, pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player_activity);


        //ovo je kod za muzicki player
        play = (Button)findViewById(R.id.gumb1);
        pause =(Button)findViewById(R.id.gumb2);
        final MediaPlayer sound = MediaPlayer.create(MusicPlayer.this,R.raw.pameti_bye_bye);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sound.pause();
            }
        });


        //dodan je v7 toolbar widget i strelica za povratak
        toolbar = (Toolbar)findViewById(R.id.tulbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Music Player");

    }
}
