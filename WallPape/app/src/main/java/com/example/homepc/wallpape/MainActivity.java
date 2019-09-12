package com.example.homepc.wallpape;

import android.app.WallpaperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IndiaGate(View view) {
        setContentView(R.layout.india_gate);
    }

    public void setIndiaGate(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.indiagate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Balloon(View view) {
        setContentView(R.layout.hot_balloon);
    }

    public void setBalloon(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.balloon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void basketBall(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.basketball);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beachA(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.beacha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beachB(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.beachb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void football(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.ball);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iphonex(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.iphone);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cata(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.cat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beachC(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.beachc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beachD(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.beachd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beachE(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.beache);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beachF(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.beachf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beachG(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.beachg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void arrow(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.arrow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}