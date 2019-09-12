package io.github.swapnadeepmohapatra.busbookadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.provider.GitHubLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.swapnadeepmohapatra.busbookadmin.Adapter.BusAdapter;
import io.github.swapnadeepmohapatra.busbookadmin.Model.BusItem;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }


        DatabaseReference busRef;
        ListView listView;

        listView = findViewById(R.id.list_bus_all);
        busRef = FirebaseDatabase.getInstance().getReference().child("Buses");
        final BusAdapter busAdapter;
        List<BusItem> busItems = new ArrayList<>();
        busAdapter = new BusAdapter(this, R.layout.bus_item, busItems);
        listView.setAdapter(busAdapter);

        busRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BusItem busItem = dataSnapshot.getValue(BusItem.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
//    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_user:
                userIntent();
                return true;
            case R.id.menu_rfid:
                rfidIntent();
                return true;
            case R.id.menu_logout:
                mLogout();
                return true;
            case R.id.menu_reset:
                mReset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mReset() {
        for (int i = 0; i <= 35; i++) {
//            Log.i("", "mReset: " + i);
            FirebaseDatabase.getInstance().getReference().child("Bookings").child(String.valueOf(i)).setValue(false);
        }
    }

    private void userIntent() {
        startActivity(new Intent(MainActivity.this, UsersActivity.class));
    }

    private void mLogout() {
        firebaseAuth.signOut();
        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
    }

    private void rfidIntent() {
        startActivity(new Intent(MainActivity.this, UsersRchActivity.class));
    }
}
