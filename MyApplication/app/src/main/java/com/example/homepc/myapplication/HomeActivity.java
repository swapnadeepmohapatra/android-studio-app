package com.example.homepc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void secondAct(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void thirdAct(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    public void fifthAct(View view) {
        Intent intent = new Intent(this, FifthActivity.class);
        startActivity(intent);
    }

    public void fourthAct(View view) {
        Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);
    }

    public void sixthAct(View view) {
        Intent intent = new Intent(this, SixthActivity.class);
        startActivity(intent);
    }

    public void sevenAct(View view) {
        Intent intent = new Intent(this, SeventhActivity.class);
        startActivity(intent);
    }

    public void eightAct(View view) {
        Intent intent = new Intent(this, EightActivity.class);
        startActivity(intent);
    }

    public void nineAct(View view) {
        Intent intent = new Intent(this, NinthActivity.class);
        startActivity(intent);
    }

    public void tenAct(View view) {
        Intent intent = new Intent(this, TenActivity.class);
        startActivity(intent);
    }

    public void elevenAct(View view) {
        Intent intent = new Intent(this, ElevenActivity.class);
        startActivity(intent);
    }

    public void twelveAct(View view) {
        Intent intent = new Intent(this, TwelveActivity.class);
        startActivity(intent);
    }
}