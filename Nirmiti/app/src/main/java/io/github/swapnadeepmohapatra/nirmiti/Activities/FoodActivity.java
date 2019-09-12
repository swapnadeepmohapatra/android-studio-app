package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.swapnadeepmohapatra.nirmiti.Adapter.ComplainAdapter;
import io.github.swapnadeepmohapatra.nirmiti.Model.ComplainItem;
import io.github.swapnadeepmohapatra.nirmiti.R;

public class FoodActivity extends AppCompatActivity {

    private ListView listBinsFood;
    private DatabaseReference foodRef;
    private DatabaseReference pointsRef;
    private ComplainAdapter foodAdp;
    private Button requestFoodBtn;
    String loc;
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        requestFoodBtn = findViewById(R.id.button_new_food);
        foodRef = FirebaseDatabase.getInstance().getReference().child("FoodRequest");
        listBinsFood = findViewById(R.id.list_pickup_food);
        firebaseAuth = FirebaseAuth.getInstance();
        pointsRef = FirebaseDatabase.getInstance().getReference().child("Points").child(firebaseAuth.getCurrentUser().getUid());

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        List<ComplainItem> food = new ArrayList<>();
        foodAdp = new ComplainAdapter(this, R.layout.complain_item, food);
        listBinsFood.setAdapter(foodAdp);

        foodRef.child("").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ComplainItem foodItem = dataSnapshot.getValue(ComplainItem.class);
                foodAdp.add(foodItem);
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

        requestFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newRequest();

            }
        });
    }

    private void newRequest() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location xlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


            LatLng userLocation = new LatLng(xlocation.getLatitude(), xlocation.getLongitude());
            loc = xlocation.getLatitude() + ", " + xlocation.getLongitude();
            String saveCurrentDate, saveCurrentTime;

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd yyyy");
            saveCurrentDate = currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
            saveCurrentTime = currentTime.format(calendar.getTime());

            Map<String, String> map = new HashMap<>();
            map.put("Name", firebaseAuth.getCurrentUser().getDisplayName());
            map.put("Message", "Please take the leftover food from here");
            map.put("Location", loc);
            map.put("Date", saveCurrentDate);
            map.put("Time", saveCurrentTime);

            foodRef.child("").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        pointsRef.child("").push().setValue("Point", "1").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(FoodActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

            foodRef.push();
        }
    }
}
