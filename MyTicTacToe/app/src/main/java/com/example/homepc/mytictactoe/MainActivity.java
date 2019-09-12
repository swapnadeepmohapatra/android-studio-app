package com.example.homepc.mytictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int myActivePlayer = 0;  //0 for cross and 1 for circle

    int[] myGameState = {
            2, 2, 2, 2, 2, 2, 2, 2, 2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void imageTapped(View view) {
        ImageView myTapped = (ImageView) view;

        int tappedTag = Integer.parseInt(myTapped.getTag().toString());

        if (myGameState[tappedTag] == 2) {
            myGameState[tappedTag] = myActivePlayer;

            if (myActivePlayer == 0) {
                myTapped.setImageResource(R.drawable.cross);
                myActivePlayer = 1;
            } else {
                myTapped.setImageResource(R.drawable.circle);

                myActivePlayer = 0;
            }
        } else {
            Toast.makeText(this, "This Place is Filled", Toast.LENGTH_SHORT).show();
        }
    }

    public void playAgain(View view) {
        myActivePlayer = 0;

        for (int i = 0; i < myGameState.length; i++) {
            myGameState[i] = 2;
        }

        LinearLayout linearLayout = findViewById(R.id.line1);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ((ImageView) linearLayout.getChildAt(i)).setImageResource(R.color.holo);
        }

        LinearLayout linearLayout1 = findViewById(R.id.line2);
        for (int i = 0; i < linearLayout1.getChildCount(); i++) {
            ((ImageView) linearLayout1.getChildAt(i)).setImageResource(R.color.holo);
        }

        LinearLayout linearLayout2 = findViewById(R.id.line3);
        for (int i = 0; i < linearLayout2.getChildCount(); i++) {
            ((ImageView) linearLayout2.getChildAt(i)).setImageResource(R.color.holo);
        }
    }
}
