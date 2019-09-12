package io.github.swapnadeepmohapatra.instagramandroid;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;

public class MainActivity extends AppCompatActivity {

    private String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        if (ParseUser.getCurrentUser() != null) {
            showUserList();
        }

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }


    public void signUp(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
            Toast.makeText(this, "All fields are compulsory", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser user = new ParseUser();

            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "err : " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void signIn(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
            Toast.makeText(this, "All fields are compulsory", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser.logInInBackground(
                    username.getText().toString().trim(),
                    password.getText().toString().trim(),
                    new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Toast.makeText(MainActivity.this, "Welcome " + user.getUsername(), Toast.LENGTH_SHORT).show();
                                showUserList();
                            } else {
                                Toast.makeText(MainActivity.this, "Err : " + e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }


    public void showUserList() {
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }
}
