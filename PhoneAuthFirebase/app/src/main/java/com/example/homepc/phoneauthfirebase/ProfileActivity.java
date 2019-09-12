package com.example.homepc.phoneauthfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {

    EditText name, userName, phone, email;
    Button btn;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference usersRef = rootRef.child("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.editText);
        userName = findViewById(R.id.editText2);
        phone = findViewById(R.id.editText3);
        email = findViewById(R.id.editText4);

        btn = findViewById(R.id.buttonWeather);

        // Log Out
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myUserName = userName.getText().toString().trim();
                String myName = name.getText().toString().trim();
                String myEmail = email.getText().toString().trim();
                String myPhone = phone.getText().toString().trim();

                if (myUserName.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Enter User Name", Toast.LENGTH_SHORT).show();
                } else if (myName.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (myEmail.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Enter E-Mail", Toast.LENGTH_SHORT).show();
                } else if (myPhone.length() < 10) {
                    Toast.makeText(ProfileActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else {

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("UserName", myUserName);
                    userMap.put("Name", myName);
                    userMap.put("Email", myEmail);
                    userMap.put("Phone", myPhone);

                    usersRef.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
