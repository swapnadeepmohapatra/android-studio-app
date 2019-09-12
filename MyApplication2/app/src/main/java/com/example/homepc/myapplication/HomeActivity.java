package com.example.homepc.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    public int scoreTeamA;
    int scoreTeamB = 0;
    int ballsTeamA = 0;
    int ballsTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        displayForBallTeamA(ballsTeamA);
    }

    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;
        displayForBallTeamB(ballsTeamB);
    }

    public void addTwoForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        displayForBallTeamA(ballsTeamA);
    }

    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;
        displayForBallTeamB(ballsTeamB);
    }

    public void addThreeForTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        displayForBallTeamA(ballsTeamA);
    }

    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;
        displayForBallTeamB(ballsTeamB);
    }

    public void addFourForTeamA(View v) {
        scoreTeamA = scoreTeamA + 4;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        displayForBallTeamA(ballsTeamA);
    }

    public void addFourForTeamB(View v) {
        scoreTeamB = scoreTeamB + 4;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;
        displayForBallTeamB(ballsTeamB);
    }

    public void addSixForTeamA(View v) {
        scoreTeamA = scoreTeamA + 6;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        displayForBallTeamA(ballsTeamA);
    }

    public void addSixForTeamB(View v) {
        scoreTeamB = scoreTeamB + 6;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;
        displayForBallTeamB(ballsTeamB);
    }

    public void resetScore(View v){
        scoreTeamA = 0;
        displayForTeamA(scoreTeamA);
        scoreTeamB = 0;
        displayForTeamB(scoreTeamB);
        ballsTeamA = 0;
        displayForBallTeamA(ballsTeamA);
        ballsTeamB = 0;
        displayForBallTeamB(ballsTeamB);
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setCancelable(true);
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void exit(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setCancelable(true);
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        setContentView(R.layout.activity_exit);
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

    public void results(View view) {
        setContentView(R.layout.activity_results);
    }
}

