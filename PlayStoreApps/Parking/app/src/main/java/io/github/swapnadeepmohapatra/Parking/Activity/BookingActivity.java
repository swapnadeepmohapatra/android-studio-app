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

import io.github.swapnadeepmohapatra.Parking.Adapter.CarAdapter;
import io.github.swapnadeepmohapatra.Parking.Model.Car;
import io.github.swapnadeepmohapatra.Parking.R;

public class BookingActivity extends AppCompatActivity {

    GridView listView;
    DatabaseReference carRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.park_activity);


        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String loc = intent.getStringExtra("loc");

        listView = findViewById(R.id.gridView);
        carRef = FirebaseDatabase.getInstance().getReference().child("Car");
        final CarAdapter carAdapter;
        List<Car> cars = new ArrayList<>();
        carAdapter = new CarAdapter(this, R.layout.park_item, cars);
        listView.setAdapter(carAdapter);

        carRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Car car = dataSnapshot.getValue(Car.class);
                car.setPlace(name);
                car.setLocation(loc);
                carAdapter.add(car);
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
