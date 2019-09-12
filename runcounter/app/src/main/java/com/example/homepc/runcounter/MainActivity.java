package com.example.homepc.runcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA =  0;
    int scoreTeamB = 0;
    int balls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }


    public void addTwoForTeamA(View v){
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addThreeForTeamA(View v){
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addFourForTeamA(View v){
        scoreTeamA = scoreTeamA + 4;
        displayForTeamA(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addSixForTeamA(View v){
        scoreTeamA = scoreTeamA + 6;
        displayForTeamA(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addOneForTeamB(View v){
        scoreTeamA = scoreTeamB + 1;
        displayForTeamB(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addTwoForTeamB(View v){
        scoreTeamA = scoreTeamB + 2;
        displayForTeamB(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addThreeForTeamB(View v){
        scoreTeamA = scoreTeamB + 3;
        displayForTeamB(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addFourForTeamB(View v){
        scoreTeamA = scoreTeamB + 4;
        displayForTeamB(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void addSixForTeamB(View v){
        scoreTeamA = scoreTeamB + 6;
        displayForTeamB(scoreTeamA);
        balls = balls + 1;
        displayForBall(balls);
    }

    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        balls = 0;
        displayForBall(balls);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }


    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    private void displayForBall(int balls) {
        TextView scoreView = (TextView) findViewById(R.id.ballNo);
        scoreView.setText(String.valueOf(balls));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
