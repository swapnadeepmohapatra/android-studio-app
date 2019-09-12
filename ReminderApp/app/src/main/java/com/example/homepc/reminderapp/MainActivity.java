package com.example.homepc.reminderapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView result = findViewById(R.id.result);

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisecondsUntilDone) {
                result.setText("Time : " + String.valueOf(millisecondsUntilDone / 1000));
            }

            public void onFinish() {
                result.setText("Done !!!");
            }
        }.start();
    }
}