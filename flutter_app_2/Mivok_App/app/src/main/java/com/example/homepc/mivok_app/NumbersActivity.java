package com.example.homepc.mivok_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        MobileAds.initialize(this, "ca-app-pub-7589870232837078~1463242879");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("One", "Un", R.drawable.number_one));
        words.add(new Word("Two", "Deux", R.drawable.number_two));
        words.add(new Word("Three", "Trois", R.drawable.number_three));
        words.add(new Word("Four", "Quatre", R.drawable.number_four));
        words.add(new Word("Five", "Cinq", R.drawable.number_five));
        words.add(new Word("Six", "Six", R.drawable.number_six));
        words.add(new Word("Seven", "Sept", R.drawable.number_seven));
        words.add(new Word("Eight", "Huit", R.drawable.number_eight));
        words.add(new Word("Nine", "Neuf", R.drawable.number_nine));
        words.add(new Word("Ten", "Dix", R.drawable.number_ten));
        words.add(new Word("Hundred", "Cent"));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}