package io.github.swapnadeepmohapatra.Parking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.swapnadeepmohapatra.Parking.Activity.MyBookingsActivity;
import io.github.swapnadeepmohapatra.Parking.Adapter.ParkingAdapter;
import io.github.swapnadeepmohapatra.Parking.Model.Parking;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth firebaseAuth;
    private ListView listView;
    private DatabaseReference parkRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("parking-72f2d")
                .setApiKey("AIzaSyBwvMpssGEkWVIHl0dJnqeZ3BBykojTPE4")
                .setDatabaseUrl("https://parking-72f2d.firebaseio.com")
                .build();

//        FirebaseApp.initializeApp(MainActivity.this, options);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Data");
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build()
                            ))
                            .build(),
                    RC_SIGN_IN);
        }

        progressDialog.show();
        listView = findViewById(R.id.list_park_all);
        parkRef = FirebaseDatabase.getInstance().getReference().child("ParkingSpot");
        final ParkingAdapter parkingAdapter;
        List<Parking> parkings = new ArrayList<>();
        parkingAdapter = new ParkingAdapter(this, R.layout.park_item, parkings);
        listView.setAdapter(parkingAdapter);

        parkRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Parking parking = dataSnapshot.getValue(Parking.class);
                parkingAdapter.add(parking);
                progressDialog.dismiss();
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
        switch (item.getItemId()) {
            case R.id.menu_bookings:
                mBookings();
                return true;
            case R.id.menu_logout:
                mLogout();
                return true;
            case R.id.menu_rebook:
                newDay();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newDay() {
        for (int i = 0; i <= 28; i++) {
            FirebaseDatabase.getInstance().getReference().child("Car").child(String.valueOf(i)).child("state").setValue(false);
        }
        for (int i = 0; i <= 28; i++) {
            FirebaseDatabase.getInstance().getReference().child("Bike").child(String.valueOf(i)).child("state").setValue(false);
        }

        final List<String> parks = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("ParkingSpot").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                parks.add(dataSnapshot.getKey());
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

        FirebaseDatabase.getInstance().getReference().child("TestData").setValue("Hello").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    for (int i = 0; i < parks.size(); i++) {
                        FirebaseDatabase.getInstance().getReference().child("ParkingSpot").child(parks.get(i)).child("booked").setValue("0");
                        FirebaseDatabase.getInstance().getReference().child("ParkingSpot").child(parks.get(i)).child("empty").setValue("58");
                    }
                }
            }
        });
    }
    // this method performs the task


    private void mLogout() {
        firebaseAuth.signOut();
        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
        if (firebaseAuth.getCurrentUser() == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build()
                            ))
                            .build(),
                    RC_SIGN_IN);
        }

    }

    private void mBookings() {
        startActivity(new Intent(MainActivity.this, MyBookingsActivity.class));
    }
}
