package com.example.homepc.mivok_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        MobileAds.initialize(this, "ca-app-pub-7589870232837078~1463242879");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Father", "Pere", R.drawable.family_father));
        words.add(new Word("Mother", "Mere", R.drawable.family_mother));
        words.add(new Word("Son", "Fis", R.drawable.family_son));
        words.add(new Word("Daughter", "File", R.drawable.family_daughter));
        words.add(new Word("Older Brother", "Grand Frere", R.drawable.family_older_brother));
        words.add(new Word("Younger Brother", "Frere Cadet", R.drawable.family_younger_brother));
        words.add(new Word("Older Sister", "Soeur Ainee", R.drawable.family_older_sister));
        words.add(new Word("Younger Sister", "Soeur Cadette", R.drawable.family_younger_sister));
        words.add(new Word("Grandmother ", "Grand-mere", R.drawable.family_grandmother));
        words.add(new Word("Grandfather", "Grand-pere", R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}