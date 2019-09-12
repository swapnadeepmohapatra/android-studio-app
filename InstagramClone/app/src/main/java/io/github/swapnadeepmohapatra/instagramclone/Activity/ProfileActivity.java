package io.github.swapnadeepmohapatra.instagramclone.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

import io.github.swapnadeepmohapatra.instagramclone.R;

public class ProfileActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userStatus;
    private Button updateButton;

    private String stUserName;
    private String stUserStatus;
    private String currentUserId;

    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();

        updateButton = findViewById(R.id.updateButton);
        userName = findViewById(R.id.userName);
        userStatus = findViewById(R.id.userStatus);

        userName.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAccountDetails();
            }
        });
    }

    private void updateAccountDetails() {
        stUserName = userName.getText().toString();
        stUserStatus = userStatus.getText().toString();
        currentUserId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        if (stUserName.isEmpty()) {
            userName.setError("Enter User Name");
            userName.requestFocus();
        } else if (stUserStatus.isEmpty()) {
            userStatus.setError("Enter Status");
            userStatus.requestFocus();
        } else {

            HashMap<String, String> profileMap = new HashMap<>();
            profileMap.put("Name", stUserName);
            profileMap.put("Status", stUserStatus);
            profileMap.put("uid", currentUserId);

            rootRef.child("Users").child(currentUserId).setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent mainInt = new Intent(ProfileActivity.this, MainActivity.class);
                    mainInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainInt);
                    finish();
                }
            });
        }
    }
}
