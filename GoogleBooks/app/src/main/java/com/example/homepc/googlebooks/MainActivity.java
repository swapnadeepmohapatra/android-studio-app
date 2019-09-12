package com.example.homepc.googlebooks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL bookUrl = ApiUtil.buildUrl("cooking");
        new BooksQueryTask().execute(bookUrl);
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(searchURL);
            } catch (IOException e) {
                Log.i("Error", "doInBackground: " + e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            textView = findViewById(R.id.tvResponse);
            textView.setText(result);
        }
    }
}
