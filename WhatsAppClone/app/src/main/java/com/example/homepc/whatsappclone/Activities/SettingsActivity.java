package com.example.homepc.whatsappclone.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homepc.whatsappclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private Button updateAccountSettings;
    private EditText userName;
    private EditText userStatus;
    private CircleImageView userProfileImage;

    private String setUserName;
    private String setStatus;
    private String currentUserId;

    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;

    private Toolbar SettingsToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initializeFields();

        mAuth = FirebaseAuth.getInstance();
        currentUserId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();

        updateAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSettings();
            }
        });

        retrieveUserInfo();
    }

    private void initializeFields() {
        userStatus = findViewById(R.id.set_profile_status);
        userProfileImage = findViewById(R.id.profile_image);
        userName = findViewById(R.id.set_user_name);
        updateAccountSettings = findViewById(R.id.update_button);

        SettingsToolBar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(SettingsToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Account Settings");
    }


    private void updateSettings() {
        setUserName = userName.getText().toString();
        setStatus = userStatus.getText().toString();
        if (setUserName.isEmpty()) {
            userName.setError("Enter Email");
            userName.requestFocus();
        } else if (setStatus.isEmpty()) {
            userStatus.setError("Enter Status");
            userStatus.requestFocus();
        } else if (setUserName.length() > 15) {
            userName.setError("Name should be less than 15 letters");
            userName.requestFocus();
        } else if (setStatus.length() > 15) {
            userStatus.setError("Status should be less than 15 letters");
            userStatus.requestFocus();
        } else {
            HashMap<String, String> profileMap = new HashMap<>();
            profileMap.put("uid", currentUserId);
            profileMap.put("name", setUserName);
            profileMap.put("status", setStatus);
            profileMap.put("image", Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getPhotoUrl()).toString());

            rootRef.child("Users").child(currentUserId).setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SettingsActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        SendUserToMainActivity();
                    } else {
                        Toast.makeText(SettingsActivity.this, Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void retrieveUserInfo() {
        rootRef.child("Users").child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && dataSnapshot.hasChild("name") && (dataSnapshot.hasChild("image"))) {
                            String retrieveUserName = Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString();
                            String retrieveStatus = Objects.requireNonNull(dataSnapshot.child("status").getValue()).toString();
                            String retrieveProfileImage = Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString();

                            Picasso.get().load(retrieveProfileImage).into(userProfileImage);

                            userName.setText(retrieveUserName);
                            userStatus.setText(retrieveStatus);
                        } else if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("name"))) {
                            String retrieveUserName = Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString();
                            String retrieveStatus = Objects.requireNonNull(dataSnapshot.child("status").getValue()).toString();

                            String photoUrl = Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getPhotoUrl()).toString();
                            Picasso.get().load(photoUrl).into(userProfileImage);

                            userName.setText(retrieveUserName);
                            userStatus.setText(retrieveStatus);
                        } else {
                            Toast.makeText(SettingsActivity.this, "Please Update Your Profile", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(SettingsActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
