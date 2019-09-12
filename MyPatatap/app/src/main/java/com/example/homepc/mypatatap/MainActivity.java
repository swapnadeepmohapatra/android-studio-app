package com.example.homepc.mypatatap;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playMusic(View view) {
        int id = view.getId();
        String nameID;
        nameID = view.getResources().getResourceEntryName(id);

        int myMusic = getResources().getIdentifier(nameID, "raw", "com.example.homepc.mypatatap");

        MediaPlayer mediaPlayer = MediaPlayer.create(this, myMusic);

        mediaPlayer.start();
    }
}