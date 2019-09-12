package io.github.swapnadeepmohapatra.e_swachhbinclient.Fragments;


import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.github.swapnadeepmohapatra.e_swachhbinclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    TextView moneyff, pointsrr;
    String uid = "";
    Button sendMoney;
    String pointsNumber = "1";
    DatabaseReference rootRef;
    DatabaseReference userRef;

    private String TAG = "RewardAct";

    public RewardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reward, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        moneyff = view.findViewById(R.id.textViewMoneyRr);
        pointsrr = view.findViewById(R.id.textViewPoints);
        sendMoney = view.findViewById(R.id.sendMoneyButton);

        userRef = FirebaseDatabase.getInstance().getReference();
        rootRef = FirebaseDatabase.getInstance().getReference();

        getPointsFromDB();

        sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
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
                                pointsNumber = String.valueOf(dataSnapshot.getChildrenCount());
                                pointsrr.setText("Total Points =  " + pointsNumber);

                                double s = Double.parseDouble(pointsNumber);
                                String moneyTxt = String.valueOf(s / 100);

                                moneyff.setText("Estimated Earnings = " + "₹ " + moneyTxt);
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
}
