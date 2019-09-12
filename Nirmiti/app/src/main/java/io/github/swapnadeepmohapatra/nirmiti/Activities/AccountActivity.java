package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import io.github.swapnadeepmohapatra.nirmiti.R;

public class AccountActivity extends AppCompatActivity {

    private TextView textViewProfileName;
    private TextView textViewProfilePoints;
    private DatabaseReference pointsRef;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        textViewProfileName = findViewById(R.id.textViewProfile);
        textViewProfilePoints = findViewById(R.id.textViewProfilePoints);
        firebaseAuth = FirebaseAuth.getInstance();
        pointsRef = FirebaseDatabase.getInstance().getReference().child("Points").child(firebaseAuth.getCurrentUser().getUid());

        Picasso.get().load(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhotoUrl()).into((ImageView) findViewById(R.id.imageViewProfile));
        textViewProfileName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        pointsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textViewProfilePoints.setText("Points: "+(String.valueOf(dataSnapshot.getChildrenCount())));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
