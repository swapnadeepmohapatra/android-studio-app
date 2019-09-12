package com.example.homepc.mivok_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        MobileAds.initialize(this, "ca-app-pub-7589870232837078~1463242879");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Good Morning", "Bonjour"));
        words.add(new Word("Thanks", "Merci"));
        words.add(new Word("How are you ?", "Comment allez-vous"));
        words.add(new Word("Where are you going?", "Ou allez-vous"));
        words.add(new Word("What is your name?", "Comment vous appelez-vous"));
        words.add(new Word("My name is...", "Mon nom est"));
        words.add(new Word("How are you feeling?", "Comment allez-vous"));
        words.add(new Word("I’m feeling good.", "Je me sens bien"));
        words.add(new Word("Are you coming?", "Viens-tu"));
        words.add(new Word("Yes, I’m coming.", "Oui je viens"));
        words.add(new Word("I’m coming.", "J'rrive"));
        words.add(new Word("Let’s go.", "Allons-y"));
        words.add(new Word("Come here.", "Venez ici"));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}