package io.github.swapnadeepmohapatra.campfiremsg.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.campfiremsg.R;

public class AppSettingsActivity extends AppCompatActivity {

    private CircleImageView profileImage;
    private TextView userName, userStatus;

    private String currentUserId;

    private DatabaseReference usersRef;
    private FirebaseAuth mAuth;
    private LinearLayout linearLayoutLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        initializeFields();
        showUserInfo();

        linearLayoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        mAuth.signOut();
        SendUserToLoginActivity();
    }

    private void showUserInfo() {
        usersRef.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String retrieveUserName = Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString();
                    String retrieveStatus = Objects.requireNonNull(dataSnapshot.child("status").getValue()).toString();
                    String retrieveProfileImage = Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString();

                    Picasso.get().load(retrieveProfileImage).into(profileImage);
                    userName.setText(retrieveUserName);
                    userStatus.setText(retrieveStatus);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeFields() {

        Toolbar appSettingsToolBar = findViewById(R.id.app_settings_toolbar);

        setSupportActionBar(appSettingsToolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("App Settings");

        profileImage = findViewById(R.id.app_settings_users_profile_image);
        userName = findViewById(R.id.app_settings_users_profile_name);
        userStatus = findViewById(R.id.app_settings_users_profile_status);
        linearLayoutLogout = findViewById(R.id.logout_layout);

        mAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        currentUserId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(AppSettingsActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}
