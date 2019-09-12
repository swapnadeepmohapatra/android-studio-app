package com.example.homepc.mivok_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        MobileAds.initialize(this, "ca-app-pub-7589870232837078~1463242879");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Red", "Rouge", R.drawable.color_red));
        words.add(new Word("Mustard yellow", "Jaune moutarde", R.drawable.color_mustard_yellow));
        words.add(new Word("Dusty yellow", "Jauna poussiereux", R.drawable.color_dusty_yellow));
        words.add(new Word("Green", "Vert", R.drawable.color_green));
        words.add(new Word("Brown", "Marron", R.drawable.color_brown));
        words.add(new Word("Gray", "Gris", R.drawable.color_gray));
        words.add(new Word("Black", "Noir", R.drawable.color_black));
        words.add(new Word("White", "Blanc", R.drawable.color_white));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
