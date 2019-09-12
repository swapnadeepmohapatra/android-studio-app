package io.github.swapnadeepmohapatra.e_swachhbinclient.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.e_swachhbinclient.R;
import io.github.swapnadeepmohapatra.e_swachhbinclient.Activities.RewardActivity;

public class HomeFragment extends Fragment {

    private static final int RC_SIGN_IN = 1;
    DatabaseReference rootRef;
    DatabaseReference userRef;
    FirebaseAuth firebaseAuth;
    CircleImageView profilePic;
    TextView username, points;
    Button nearByBtn, pointsBtn;
    String mUsername;
    String aa;
    //    String uid = "EA 21 4F 83";   //Key Chain
    String uid = "06 50 68 12";         //Card

    public HomeFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        username = view.findViewById(R.id.username);
        profilePic = view.findViewById(R.id.profilePhoto);
        nearByBtn = view.findViewById(R.id.buttonNearBy);
        points = view.findViewById(R.id.textViewReward);
        pointsBtn = view.findViewById(R.id.btnPoints);

        userRef = FirebaseDatabase.getInstance().getReference();
        rootRef = FirebaseDatabase.getInstance().getReference();

        checkUser();
        getPointsFromDB();

        pointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rewadIntent = new Intent(getContext(), RewardActivity.class);
                rewadIntent.putExtra("points", aa);
                startActivity(rewadIntent);
            }
        });

        nearByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Functionality to be added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPointsFromDB() {
        userRef.child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    uid = Objects.requireNonNull(dataSnapshot.child("id").getValue()).toString();
                    rootRef.child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Log.i("", "onDataChange: " + dataSnapshot.getChildrenCount());
                                Log.i("", "onDataChange: " + dataSnapshot);
                                aa = String.valueOf(dataSnapshot.getChildrenCount());
                                points.setText(aa);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                    Toast.makeText(getContext(), "err", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void checkUser() {
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(getContext(), "Login First", Toast.LENGTH_LONG).show();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Collections.singletonList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        } else {
            displayUserInfo();
        }
    }

    private void displayUserInfo() {
        mUsername = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName();
        Uri mUri = firebaseAuth.getCurrentUser().getPhotoUrl();
        Picasso.get().load(mUri).into(profilePic);
        username.setText(mUsername);
    }
}
