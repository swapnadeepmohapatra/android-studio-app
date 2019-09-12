package io.github.swapnadeepmohapatra.Parking.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.swapnadeepmohapatra.Parking.Adapter.BookAdapter;
import io.github.swapnadeepmohapatra.Parking.Model.Book;
import io.github.swapnadeepmohapatra.Parking.R;

public class MyBookingsActivity extends AppCompatActivity {

    private DatabaseReference usersReference;
    private ListView listView;
    BookAdapter bookAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        listView = findViewById(R.id.list_view_bookings);
        List<Book> books = new ArrayList<>();
        bookAdapter = new BookAdapter(this, R.layout.book_item, books);
        listView.setAdapter(bookAdapter);
        usersReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        usersReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("", "onChildAdded: "+dataSnapshot);
                Book book = dataSnapshot.getValue(Book.class);
                bookAdapter.add(book);
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
