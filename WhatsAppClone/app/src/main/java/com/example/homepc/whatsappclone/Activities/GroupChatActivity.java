package com.example.homepc.whatsappclone.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homepc.whatsappclone.Adapters.GroupMessageAdapter;
import com.example.homepc.whatsappclone.Model.GroupMessage;
import com.example.homepc.whatsappclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupChatActivity extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";

    private ListView mMessageListView;
    private GroupMessageAdapter mMessageAdapter;
    private EditText mMessageEditText;
    private ImageButton mSendButton;
    private Toolbar toolbar;

    private String mUsername;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference userRef;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;

    private String currentGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        mUsername = ANONYMOUS;

        currentGroupName = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("groupName")).toString();

        toolbar = findViewById(R.id.group_chat_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(currentGroupName);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Groups").child(currentGroupName).child("Messages");
        mMessagesDatabaseReference.keepSynced(true);

        userRef = mFirebaseDatabase.getReference().child("Groups").child(currentGroupName).child("Users");

        mMessageListView = findViewById(R.id.messageListView);
        mMessageEditText = findViewById(R.id.messageEditText);
        mSendButton = findViewById(R.id.sendButton);

        List<GroupMessage> groupMessages = new ArrayList<>();
        mMessageAdapter = new GroupMessageAdapter(this, R.layout.item_message, groupMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getDisplayName();
                GroupMessage groupMessage = new GroupMessage(mMessageEditText.getText().toString(), mUsername, mFirebaseAuth.getCurrentUser().getUid());
                mMessagesDatabaseReference.push().setValue(groupMessage);

                mMessageEditText.setText("");
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid())) {
                    attachDatabaseReadListener();
                }else {
                    Toast.makeText(GroupChatActivity.this, "You don't have access to this group", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                    GroupMessage groupMessage = dataSnapshot.getValue(GroupMessage.class);
                    mMessageAdapter.add(groupMessage);
                }

                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                }

                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }

                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                }

                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}
