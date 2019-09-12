package io.github.swapnadeepmohapatra.e_swachhbin.Fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.e_swachhbin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    CircleImageView profilePic;
    TextView username, userMail, UseuserID, RFID;
    String mUsername;
    DatabaseReference userRef;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        username = view.findViewById(R.id.username_acc);
        userMail = view.findViewById(R.id.userMail_acc);
        RFID = view.findViewById(R.id.rfid_number);
        profilePic = view.findViewById(R.id.profilePhoto_acc);
        UseuserID = view.findViewById(R.id.userid_acc);
        userRef = FirebaseDatabase.getInstance().getReference();

        displayUserInfo();
    }

    private void displayUserInfo() {
        mUsername = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName();
        Uri mUri = firebaseAuth.getCurrentUser().getPhotoUrl();
        Picasso.get().load(mUri).into(profilePic);
        username.setText(mUsername);
        userMail.setText(firebaseAuth.getCurrentUser().getEmail());
        UseuserID.setText("ID : " + firebaseAuth.getCurrentUser().getUid());

        userRef.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    RFID.setText(dataSnapshot.child("id").getValue().toString());
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
