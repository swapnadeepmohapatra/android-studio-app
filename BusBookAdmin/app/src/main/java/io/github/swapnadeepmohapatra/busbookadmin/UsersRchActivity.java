package io.github.swapnadeepmohapatra.busbookadmin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.swapnadeepmohapatra.busbookadmin.Adapter.UserAdapter;
import io.github.swapnadeepmohapatra.busbookadmin.Adapter.UserRchAdapter;
import io.github.swapnadeepmohapatra.busbookadmin.Model.UserItem;

public class UsersRchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_rch);

        DatabaseReference usersRef;
        ListView listView;

        listView = findViewById(R.id.list_users_rch_all);
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        final UserRchAdapter busAdapter;
        List<UserItem> busItems = new ArrayList<>();
        busAdapter = new UserRchAdapter(this, R.layout.users_item, busItems);
        listView.setAdapter(busAdapter);

        usersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i(" ", "onChildAdded: " + dataSnapshot);
                UserItem busItem = dataSnapshot.getValue(UserItem.class);
                busAdapter.add(busItem);
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
