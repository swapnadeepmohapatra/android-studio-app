package io.github.swapnadeepmohapatra.Parking.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.swapnadeepmohapatra.Parking.Adapter.BikeAdapter;
import io.github.swapnadeepmohapatra.Parking.Model.Car;
import io.github.swapnadeepmohapatra.Parking.R;

public class BikeParkActivity extends AppCompatActivity {

    GridView listView;
    DatabaseReference bikeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_park);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");

        listView = findViewById(R.id.gridViewBikePark);
        bikeRef = FirebaseDatabase.getInstance().getReference().child("Bike");
        final BikeAdapter bikeAdapter;

        List<Car> cars = new ArrayList<>();
        bikeAdapter = new BikeAdapter(this, R.layout.bike_item, cars);
        listView.setAdapter(bikeAdapter);

        bikeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Car car = dataSnapshot.getValue(Car.class);
                car.setPlace(name);
                bikeAdapter.add(car);
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

