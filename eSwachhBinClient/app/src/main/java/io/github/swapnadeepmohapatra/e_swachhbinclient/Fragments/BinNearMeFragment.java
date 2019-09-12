package io.github.swapnadeepmohapatra.e_swachhbinclient.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.swapnadeepmohapatra.e_swachhbinclient.Adapter.BinAdapter;
import io.github.swapnadeepmohapatra.e_swachhbinclient.Model.BinItem;
import io.github.swapnadeepmohapatra.e_swachhbinclient.R;

public class BinNearMeFragment extends Fragment {

    private ListView listBins;
    private DatabaseReference binsRef;
    private BinAdapter binAdapter;


    public BinNearMeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bin_near_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binsRef = FirebaseDatabase.getInstance().getReference().child("bin");
        listBins = view.findViewById(R.id.listView);

        List<BinItem> binItems = new ArrayList<>();
        binAdapter = new BinAdapter(getContext(), R.layout.bin_item, binItems);
        listBins.setAdapter(binAdapter);

        binsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BinItem friendlyMessage = dataSnapshot.getValue(BinItem.class);
                binAdapter.add(friendlyMessage);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
