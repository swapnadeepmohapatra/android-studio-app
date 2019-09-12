package io.github.swapnadeepmohapatra.busbooklocation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements LocationListener {

    DatabaseReference firebaseDatabase;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("bus-booking-93395")
                .setApiKey("AIzaSyDArLwLnXQscFJJJ1khmoTqA4AbDp6ZMwI")
                .setDatabaseUrl("https://bus-booking-93395.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(MainActivity.this, options);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Location");


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        String TAG = "asd";
        Log.i(TAG, "onLocationChanged: " + location.getLongitude() + location.getLatitude());
        Toast.makeText(this, String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
        firebaseDatabase.setValue(String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));

        TextView textView = findViewById(R.id.text);
        textView.setText("You Location is " + String.valueOf(location.getLatitude()) + " , " + String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
