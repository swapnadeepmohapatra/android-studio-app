package io.github.swapnadeepmohapatra.instagramclone.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.instagramclone.Activity.EditProfileActivity;
import io.github.swapnadeepmohapatra.instagramclone.Activity.MainActivity;
import io.github.swapnadeepmohapatra.instagramclone.R;


public class ProfileFragment extends Fragment {

    private View profileFragmentView;
    private TextView userName;
    private TextView userStatus;
    private CircleImageView profileImage;
    private FirebaseAuth auth;
    private DatabaseReference usersRef;
    private RelativeLayout signOut;
    private TextView editProfile;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileFragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Objects.requireNonNull(auth.getCurrentUser()).getUid());

        userName = profileFragmentView.findViewById(R.id.user_name);
        userStatus = profileFragmentView.findViewById(R.id.user_status);
        signOut = profileFragmentView.findViewById(R.id.logOut);
        editProfile = profileFragmentView.findViewById(R.id.editProfile);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });

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

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToEditProfileActivity();
            }
        });

        profileImage = profileFragmentView.findViewById(R.id.profileImageView);
        Glide.with(Objects.requireNonNull(getContext())).load(Objects.requireNonNull(auth.getCurrentUser()).getPhotoUrl()).into(profileImage);

        return profileFragmentView;
    }

    private void sendToEditProfileActivity() {
        Intent editIntent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(editIntent);
    }

    private void SignOut() {
        auth.signOut();
        if (auth.getCurrentUser() == null) {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainIntent);
            Objects.requireNonNull(getActivity()).finish();
            Toast.makeText(getContext(), "Signed Out !", Toast.LENGTH_SHORT).show();
        } else {
            SignOut();
        }

    }

}
