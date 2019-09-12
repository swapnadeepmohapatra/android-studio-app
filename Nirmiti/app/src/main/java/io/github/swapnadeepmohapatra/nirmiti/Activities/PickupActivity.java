package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class PickupActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView textViewStatus;
    private TextView percentageBin;
    private ImageView bin;
    private ListView listBinsREq;
    private DatabaseReference reqRef;
    private DatabaseReference pointsRef;
    private ComplainAdapter reqAdp;
    private Button requestBtn;
    String loc;
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        requestBtn = findViewById(R.id.button_new_req);
        reqRef = FirebaseDatabase.getInstance().getReference().child("NewBinRequests");
        listBinsREq = findViewById(R.id.list_pickup);
        firebaseAuth = FirebaseAuth.getInstance();
        pointsRef = FirebaseDatabase.getInstance().getReference().child("Points").child(firebaseAuth.getCurrentUser().getUid());


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        List<ComplainItem> req = new ArrayList<>();
        reqAdp = new ComplainAdapter(this, R.layout.complain_item, req);
        listBinsREq.setAdapter(reqAdp);

        reqRef.child("").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ComplainItem complainItem = dataSnapshot.getValue(ComplainItem.class);
                reqAdp.add(complainItem);
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

        requestBtn.setOnClickListener(new View.OnClickListener() {
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
            map.put("Message", "A New Dustbin is requested here");
            map.put("Location", loc);
            map.put("Date", saveCurrentDate);
            map.put("Time", saveCurrentTime);

            reqRef.child("").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        pointsRef.child("").push().setValue("Point", "1").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(PickupActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location xlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            LatLng userLocation = new LatLng(xlocation.getLatitude(), xlocation.getLongitude());
            loc = "Latitude  " + xlocation.getLatitude() + "\n" + "Longitude  " + xlocation.getLongitude();
            mMap.clear();
            Log.i("", "onMapReady: " + loc);
            mMap.addMarker(new MarkerOptions().position(userLocation).title("Dustbin Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
        }
    }
}
