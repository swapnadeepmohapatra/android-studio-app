package io.github.swapnadeepmohapatra.instagramclone.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.swapnadeepmohapatra.instagramclone.Model.Message;
import io.github.swapnadeepmohapatra.instagramclone.Adapter.MessageAdapter;
import io.github.swapnadeepmohapatra.instagramclone.R;

public class PostsFragment extends Fragment {

    private View postFragmentView;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;

    private ProgressDialog mProgressDialog;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;

    private SwipeRefreshLayout swipeContainer;

    public PostsFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        postFragmentView = inflater.inflate(R.layout.fragment_posts, container, false);
        initialize();

        attachDatabaseReadListener();

        swipeContainer = postFragmentView.findViewById(R.id.refresh);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                detachDatabaseReadListener();
                attachDatabaseReadListener();
                mMessageListView.smoothScrollToPosition(0);
            }
        });

        swipeContainer.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Loading Feeds");
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        postFragmentView.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMessageListView.setSmoothScrollbarEnabled(true);
                mMessageListView.smoothScrollToPosition(0);
            }
        });

        return postFragmentView;
    }

    private void attachDatabaseReadListener() {

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                if (swipeContainer.isRefreshing()) {
                    swipeContainer.setRefreshing(false);
                    Toast.makeText(getContext(), "All Caught", Toast.LENGTH_SHORT).show();
                }
                Message friendlyMessage = dataSnapshot.getValue(Message.class);
                mMessageAdapter.add(friendlyMessage);
                mMessageListView.smoothScrollToPosition(0);
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
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


    private void initialize() {

        mMessageListView = postFragmentView.findViewById(R.id.msgListView);
        mMessageListView.smoothScrollToPosition(0);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("messages");
        mMessagesDatabaseReference.keepSynced(true);

        List<Message> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(Objects.requireNonNull(getContext()), R.layout.message_layout, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);
        mMessageListView.setSmoothScrollbarEnabled(true);
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mMessageListView.smoothScrollToPosition(0);
            mMessagesDatabaseReference.removeEventListener(mChildEventListener);
            mMessageAdapter.clear();
            mChildEventListener = null;
            mMessageListView.smoothScrollToPosition(0);
        }
    }
}
