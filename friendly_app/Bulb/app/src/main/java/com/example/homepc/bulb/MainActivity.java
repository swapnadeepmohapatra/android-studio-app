package com.example.homepc.bulb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void blue(View view) {

        ImageView blue = findViewById(R.id.bluebulb);
        ImageView green = findViewById(R.id.greenbulb);

        blue.animate().alpha(1).setDuration(1000);
        green.animate().alpha(0).setDuration(1000);
    }

    public void green(View view) {

        ImageView green = findViewById(R.id.greenbulb);
        ImageView blue = findViewById(R.id.bluebulb);

        green.animate().alpha(1).setDuration(1000);
        blue.animate().alpha(0).setDuration(1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
