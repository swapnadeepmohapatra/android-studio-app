package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.swapnadeepmohapatra.nirmiti.Adapter.BinAdapter;
import io.github.swapnadeepmohapatra.nirmiti.Model.BinItem;
import io.github.swapnadeepmohapatra.nirmiti.R;

public class MainActivity extends AppCompatActivity {

    private ListView listBins;
    private DatabaseReference binsRef;
    private BinAdapter binAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binsRef = FirebaseDatabase.getInstance().getReference().child("bins");
        listBins = findViewById(R.id.listView);

        List<BinItem> binItems = new ArrayList<>();
        binAdapter = new BinAdapter(this, R.layout.bin_item, binItems);
        listBins.setAdapter(binAdapter);

        binsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BinItem binItem = dataSnapshot.getValue(BinItem.class);
                binAdapter.add(binItem);
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
