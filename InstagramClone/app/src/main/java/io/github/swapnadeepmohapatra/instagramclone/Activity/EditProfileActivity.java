package io.github.swapnadeepmohapatra.instagramclone.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.instagramclone.R;

public class EditProfileActivity extends AppCompatActivity {

    private DatabaseReference usersRef;
    private FirebaseAuth firebaseAuth;
    private CircleImageView editProfileImageView;
    private EditText userName, userStatus;
    private Button updateButton;
    private String stUserStatus;
    private String currentUserId;
    private String stUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfileImageView = findViewById(R.id.editProfileImageView);
        userName = findViewById(R.id.editUsername);
        userStatus = findViewById(R.id.editUserStatus);
        updateButton = findViewById(R.id.updateProfileButton);

        firebaseAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());

        Glide.with(this).load(firebaseAuth.getCurrentUser().getPhotoUrl()).into(editProfileImageView);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    userName.setText(Objects.requireNonNull(dataSnapshot.child("Name").getValue()).toString());
                    userStatus.setText(Objects.requireNonNull(dataSnapshot.child("Status").getValue()).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
        currentUserId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

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

            usersRef.setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(EditProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent mainInt = new Intent(EditProfileActivity.this, MainActivity.class);
                    mainInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainInt);
                    finish();
                }
            });
        }
    }
}
