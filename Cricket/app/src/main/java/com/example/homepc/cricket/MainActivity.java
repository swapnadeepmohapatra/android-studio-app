package com.example.homepc.cricket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // run
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    // Balls
    int ballsTeamA = 0;
    int ballsTeamB = 0;
    // Overs
    int oversTeamA = 0;
    int oversTeamB = 0;
    // Wickets
    int wicketTeamA = 0;
    int wicketTeamB = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;

        if (ballsTeamA == 6) {
            oversTeamA = oversTeamA + 1;
            ballsTeamA = 0;
        }
        displayForOverTeamA(oversTeamA);
        displayForBallTeamA(ballsTeamA);
    }

    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;

        if (ballsTeamB == 6) {
            oversTeamB = oversTeamB + 1;
            ballsTeamB = 0;
        }
        displayForBallTeamB(ballsTeamB);
        displayForOverTeamB(oversTeamB);
    }

    public void addTwoForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        if (ballsTeamA == 6) {
            oversTeamA = oversTeamA + 1;
            ballsTeamA = 0;
        }
        displayForOverTeamA(oversTeamA);
        displayForBallTeamA(ballsTeamA);
    }

    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;

        if (ballsTeamB == 6) {
            oversTeamB = oversTeamB + 1;
            ballsTeamB = 0;
        }
        displayForBallTeamB(ballsTeamB);
        displayForOverTeamB(oversTeamB);
    }

    public void addThreeForTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        if (ballsTeamA == 6) {
            oversTeamA = oversTeamA + 1;
            ballsTeamA = 0;
        }
        displayForOverTeamA(oversTeamA);
        displayForBallTeamA(ballsTeamA);
    }

    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;


        if (ballsTeamB == 6) {
            oversTeamB = oversTeamB + 1;
            ballsTeamB = 0;
        }
        displayForBallTeamB(ballsTeamB);
        displayForOverTeamB(oversTeamB);
    }

    public void addFourForTeamA(View v) {
        scoreTeamA = scoreTeamA + 4;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        if (ballsTeamA == 6) {
            oversTeamA = oversTeamA + 1;
            ballsTeamA = 0;
        }
        displayForOverTeamA(oversTeamA);
        displayForBallTeamA(ballsTeamA);
    }

    public void addFourForTeamB(View v) {
        scoreTeamB = scoreTeamB + 4;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;


        if (ballsTeamB == 6) {
            oversTeamB = oversTeamB + 1;
            ballsTeamB = 0;
        }
        displayForBallTeamB(ballsTeamB);
        displayForOverTeamB(oversTeamB);
    }

    public void addSixForTeamA(View v) {
        scoreTeamA = scoreTeamA + 6;
        displayForTeamA(scoreTeamA);
        ballsTeamA = ballsTeamA + 1;
        if (ballsTeamA == 6) {
            oversTeamA = oversTeamA + 1;
            ballsTeamA = 0;
        }
        displayForOverTeamA(oversTeamA);
        displayForBallTeamA(ballsTeamA);
    }

    public void addSixForTeamB(View v) {
        scoreTeamB = scoreTeamB + 6;
        displayForTeamB(scoreTeamB);
        ballsTeamB = ballsTeamB + 1;


        if (ballsTeamB == 6) {
            oversTeamB = oversTeamB + 1;
            ballsTeamB = 0;
        }
        displayForBallTeamB(ballsTeamB);
        displayForOverTeamB(oversTeamB);
    }

    public void outForTeamA(View v) {
        wicketTeamA = wicketTeamA + 1;

        if (wicketTeamA == 10) {
            Toast.makeText(this, "Team A all out", Toast.LENGTH_SHORT).show();
        }

        displayForOutTeamA(wicketTeamA);
    }

    public void displayForTeamBA(View v){
        String priceMessage = "Run : " + scoreTeamB + "-" + wicketTeamB;
        priceMessage += "\nOver : " + oversTeamB + "." + ballsTeamB;
        displayForTeamB(priceMessage);
    }


    public void outForTeamB(View v) {
        wicketTeamB = wicketTeamB + 1;

        if (wicketTeamB == 10) {
            Toast.makeText(this, "Team B all out", Toast.LENGTH_SHORT).show();
        }
        displayForOutTeamB(wicketTeamB);
    }

    private String createOrderSummary(int ballsTeamB, int wicketTeamB, int oversTeamB, int scoreTeamB) {
        String priceMessage = "Run : " + scoreTeamB + "-" + wicketTeamB;
        priceMessage += "\nOver : " + oversTeamB + "." + ballsTeamB;
        return priceMessage;
    }


    public void resetScore(View v) {
        scoreTeamA = 0;
        displayForTeamA(scoreTeamA);
        scoreTeamB = 0;
        displayForTeamB(scoreTeamB);
        ballsTeamA = 0;
        displayForBallTeamA(ballsTeamA);
        ballsTeamB = 0;
        displayForBallTeamB(ballsTeamB);
        oversTeamA = 0;
        displayForOverTeamA(oversTeamA);
        oversTeamB = 0;
        displayForOverTeamB(oversTeamB);
        wicketTeamB = 0;
        displayForOutTeamB(oversTeamB);
        wicketTeamA = 0;
        displayForOutTeamA(wicketTeamA);
    }

    private void displayForOutTeamA(int wicketTeamA) {
        TextView scoreView = findViewById(R.id.team_a_wicket);
        scoreView.setText(String.valueOf(wicketTeamA));
    }

    private void displayForOutTeamB(int wicketTeamB) {
        TextView scoreView = findViewById(R.id.team_b_wicket);
        scoreView.setText(String.valueOf(wicketTeamB));
    }

    private void displayForBallTeamA(int ballsTeamA) {
        TextView scoreView = findViewById(R.id.team_a_balls);
        scoreView.setText(String.valueOf(ballsTeamA));
    }

    private void displayForOverTeamA(int oversTeamA) {
        TextView scoreView = findViewById(R.id.team_a_overs);
        scoreView.setText(String.valueOf(oversTeamA));
    }

    private void displayForOverTeamB(int oversTeamB) {
        TextView scoreView = findViewById(R.id.team_b_overs);
        scoreView.setText(String.valueOf(oversTeamB));
    }

    private void displayForTeamA(int scoreTeamA) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    private void displayForBallTeamB(int ballsTeamB) {
        TextView scoreView = findViewById(R.id.team_b_balls);
        scoreView.setText(String.valueOf(ballsTeamB));
    }

    private void displayForTeamB(int scoreTeamB) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));

    }

    private void displayForTeamBA(String createOrderSummary,String priceMessage){
        TextView runView = findViewById(R.id.team_b_runs);
        runView.setText(String.valueOf(priceMessage));
    }
}