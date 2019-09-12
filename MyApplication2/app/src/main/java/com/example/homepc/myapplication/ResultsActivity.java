package com.example.homepc.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.homepc.myapplication.HomeActivity;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }

    public void displayForBallTeamA(int ballsTeamA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_balls);
        scoreView.setText(String.valueOf(ballsTeamA));
    }

    public void displayForTeamA(int scoreTeamA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    public void displayForBallTeamB(int ballsTeamB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_balls);
        scoreView.setText(String.valueOf(ballsTeamB));
    }

    public void displayForTeamB(int scoreTeamB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));

    }
}
