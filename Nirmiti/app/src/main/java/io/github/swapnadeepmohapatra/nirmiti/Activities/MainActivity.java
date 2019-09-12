package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.accounts.Account;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.swapnadeepmohapatra.nirmiti.Adapter.BinAdapter;
import io.github.swapnadeepmohapatra.nirmiti.Model.BinItem;
import io.github.swapnadeepmohapatra.nirmiti.R;

public class MainActivity extends AppCompatActivity {

    private ListView listBins;
    private DatabaseReference binsRef;
    private BinAdapter binAdapter;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binsRef = FirebaseDatabase.getInstance().getReference().child("bins");
        listBins = findViewById(R.id.listView);
        mAuth = FirebaseAuth.getInstance();

        checkUser();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.food_Pickup:
                foodPickup();
                return true;
            case R.id.new_bin:
                newBinRequest();
                return true;
            case R.id.my_account:
                myAccount();
                return true;
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        mAuth.signOut();
    }

    private void myAccount() {
        startActivity(new Intent(MainActivity.this, AccountActivity.class));
    }


    private void newBinRequest() {
        startActivity(new Intent(MainActivity.this, PickupActivity.class));
    }

    private void foodPickup() {
        startActivity(new Intent(MainActivity.this, FoodActivity.class));
    }

    private void checkUser() {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(MainActivity.this, "Login First", Toast.LENGTH_LONG).show();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }

}
