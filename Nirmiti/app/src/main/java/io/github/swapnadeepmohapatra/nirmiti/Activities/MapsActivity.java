package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import io.github.swapnadeepmohapatra.nirmiti.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker m1;
    private Marker m2;
    private Marker m3;
    private Marker m4;
    private DatabaseReference binsRef;
    float Parking_LotPc;
    float Main_BuildingPc;
    float CanteenPc;
    float EnexaPc;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        binsRef = FirebaseDatabase.getInstance().getReference().child("bins");
        mAuth = FirebaseAuth.getInstance();

        checkUser();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setOnMarkerClickListener(MapsActivity.this);

        binsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Parking_LotPc = Float.parseFloat(dataSnapshot.child("ctc").child("percentage").getValue().toString());
                Main_BuildingPc = Float.parseFloat(dataSnapshot.child("Banglore").child("percentage").getValue().toString());
                CanteenPc = Float.parseFloat(dataSnapshot.child("aiims").child("percentage").getValue().toString());
                EnexaPc = Float.parseFloat(dataSnapshot.child("Bhuj").child("percentage").getValue().toString());

                LatLng myLoc = new LatLng(15.42255556, 73.98166667);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15));


                if (String.valueOf(Parking_LotPc) != null) {

                    if (Math.round(Parking_LotPc) <= 100 & Math.round(Parking_LotPc) >= 91) {
                        m1 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42255556, 73.98166667))
                                .anchor(0.5f, 0.5f)
                                .title("Parking Lot")
                                .snippet(String.valueOf(Parking_LotPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    } else if (Math.round(Parking_LotPc) <= 90 & Math.round(Parking_LotPc) >= 11) {
                        m1 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42255556, 73.98166667))
                                .anchor(0.5f, 0.5f)
                                .title("Parking Lot")
                                .snippet(String.valueOf(Parking_LotPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_yellow)));
                    } else if (Math.round(Parking_LotPc) <= 10 & Math.round(Parking_LotPc) >= 0) {
                        m1 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42255556, 73.98166667))
                                .anchor(0.5f, 0.5f)
                                .title("Parking Lot")
                                .snippet(String.valueOf(Parking_LotPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_green)));
                    } else {
                        m1 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42255556, 73.98166667))
                                .anchor(0.5f, 0.5f)
                                .title("Parking Lot")
                                .snippet(String.valueOf(Parking_LotPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    }


                    if (Math.round(Main_BuildingPc) <= 100 & Math.round(Main_BuildingPc) >= 91) {
                        m2 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.98027778))
                                .anchor(0.5f, 0.5f)
                                .title("Main Building")
                                .snippet(String.valueOf(Main_BuildingPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    } else if (Math.round(Main_BuildingPc) <= 90 & Math.round(Main_BuildingPc) >= 11) {
                        m2 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.98027778))
                                .anchor(0.5f, 0.5f)
                                .title("Main Building")
                                .snippet(String.valueOf(Main_BuildingPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_yellow)));
                    } else if ((Main_BuildingPc) <= 10 & Math.round(Main_BuildingPc) >= 0) {
                        m2 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.98027778))
                                .anchor(0.5f, 0.5f)
                                .title("Main Building")
                                .snippet(String.valueOf(Main_BuildingPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_green)));
                    } else {
                        m2 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.98027778))
                                .anchor(0.5f, 0.5f)
                                .title("Main Building")
                                .snippet(String.valueOf(Main_BuildingPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    }


                    if (Math.round(CanteenPc) <= 100 & Math.round(CanteenPc) >= 91) {
                        m3 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.97916667))
                                .anchor(0.5f, 0.5f)
                                .title("Canteen")
                                .snippet(String.valueOf(CanteenPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    } else if (Math.round(CanteenPc) <= 90 & Math.round(CanteenPc) >= 11) {
                        m3 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.97916667))
                                .anchor(0.5f, 0.5f)
                                .title("Canteen")
                                .snippet(String.valueOf(CanteenPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_yellow)));
                    } else if (Math.round(CanteenPc) <= 10 & Math.round(CanteenPc) >= 0) {
                        m3 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.97916667))
                                .anchor(0.5f, 0.5f)
                                .title("Canteen")
                                .snippet(String.valueOf(CanteenPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_green)));
                    } else {
                        m3 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.42269444, 73.97916667))
                                .anchor(0.5f, 0.5f)
                                .title("Canteen")
                                .snippet(String.valueOf(CanteenPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    }
                    if (Math.round(EnexaPc) <= 100 & Math.round(EnexaPc) >= 91) {
                        m4 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.4244407, 73.9792430))
                                .anchor(0.5f, 0.5f)
                                .title("Enexa")
                                .snippet(String.valueOf(EnexaPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    } else if (Math.round(EnexaPc) <= 90 & Math.round(EnexaPc) >= 11) {
                        m4 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.4244407, 73.9792430))
                                .anchor(0.5f, 0.5f)
                                .title("Enexa")
                                .snippet(String.valueOf(EnexaPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_yellow)));
                    } else if (Math.round(EnexaPc) <= 10 & Math.round(EnexaPc) >= 0) {
                        m4 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.4244407, 73.9792430))
                                .anchor(0.5f, 0.5f)
                                .title("Enexa")
                                .snippet(String.valueOf(EnexaPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_green)));
                    } else {
                        m4 = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(15.4244407, 73.9792430))
                                .anchor(0.5f, 0.5f)
                                .title("Enexa")
                                .snippet(String.valueOf(EnexaPc))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_red)));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        m2 = googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(15.42269444, 73.98027778))
//                .anchor(0.5f, 0.5f)
//                .title("Main Building")
//                .snippet("Snippet2")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_green)));

//
//        m3 = googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(15.42269444, 73.97916667))
//                .anchor(0.5f, 0.5f)
//                .title("Canteen")
//                .snippet("Snippet3")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_yellow)));

//        m4 = googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(15.422615644, 73.97496667))
//                .anchor(0.5f, 0.5f)
//                .title("Enexa")
//                .snippet("Snippet3")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_yellow)));

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    private void logout() {
        mAuth.signOut();
    }

    private void myAccount() {
        startActivity(new Intent(MapsActivity.this, AccountActivity.class));
    }

    private void pickupRequest() {

    }

    private void newBinRequest() {
        startActivity(new Intent(MapsActivity.this, PickupActivity.class));
    }

    private void foodPickup() {
        startActivity(new Intent(MapsActivity.this, FoodActivity.class));
    }

    private void checkUser() {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(MapsActivity.this, "Login First", Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onMarkerClick(Marker marker) {
//        Toast.makeText(this, "Clicked " + marker.getTitle(), Toast.LENGTH_SHORT).show();
//        if (marker.getTitle().toLowerCase().trim().equals("")) {
        Intent intent = new Intent(MapsActivity.this, BinActivity.class);
        if (marker.getTitle().equals("Parking Lot")) {
            intent.putExtra("perc", Math.round(Parking_LotPc));
        } else if (marker.getTitle().equals("Main Building")) {
            intent.putExtra("perc", Math.round(Main_BuildingPc));
        } else if (marker.getTitle().equals("Canteen")) {
            intent.putExtra("perc", Math.round(CanteenPc));
        } else if (marker.getTitle().equals("Enexa")) {
            intent.putExtra("perc", Math.round(EnexaPc));
        } else {
            intent.putExtra("perc", Math.round(100.00));
        }

        intent.putExtra("loc", (marker.getTitle()));
        startActivity(intent);
//        }

        return false;
    }
}
