package com.example.homepc.wallpaper;

import android.app.WallpaperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void adda(View view) {
        try {
            WallpaperManager.getInstance(getApplicationContext()).setResource(R.drawable.amba);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addb(View view) {
        try {
            WallpaperManager.getInstance(getApplicationContext()).setResource(R.drawable.code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addc(View view) {
        try {
            WallpaperManager.getInstance(getApplicationContext()).setResource(R.drawable.horizon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
