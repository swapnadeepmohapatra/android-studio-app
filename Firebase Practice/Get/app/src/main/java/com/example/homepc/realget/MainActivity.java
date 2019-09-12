package com.example.homepc.realget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String names, mail;
    ListView listView,listView2;
    ArrayList<String> userNames = new ArrayList<>();
    ArrayList<String> mMail = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    DatabaseReference usersRef = databaseReference.child("Message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userNames);
        listView.setAdapter(arrayAdapter);

        final ArrayAdapter<String> mailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMail);
        listView2.setAdapter(arrayAdapter);

        usersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue().toString();
                Log.i(TAG, "onChildAdded: "+value);
                try {
                    JSONObject object = new JSONObject(value);
                    names = object.getString("Name");
                    mail = object.getString("Email");
                    Log.i("Mail", "onChildAdded: " + mail);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userNames.add(names);
                mMail.add(mail);
                arrayAdapter.notifyDataSetChanged();
                mailAdapter.notifyDataSetChanged();
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
