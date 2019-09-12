package io.github.swapnadeepmohapatra.smartreply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import io.github.swapnadeepmohapatra.smartreply.chat.ChatFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("smartreply-b88c6")
                .setApiKey("AIzaSyA0IY_TD77v1FpIYTL6dmF_HEZuBEPa8ow")
                .setDatabaseUrl("https://smartreply-b88c6.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(MainActivity.this, options);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ChatFragment.newInstance())
                    .commitNow();
        }
    }
}
