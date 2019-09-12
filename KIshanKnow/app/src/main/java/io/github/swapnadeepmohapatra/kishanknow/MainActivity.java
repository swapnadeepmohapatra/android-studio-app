package io.github.swapnadeepmohapatra.kishanknow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView temp, humd, rain, soil;
    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootRef = FirebaseDatabase.getInstance().getReference();

        temp = findViewById(R.id.temp);
        humd = findViewById(R.id.hum);
        rain = findViewById(R.id.rain);
        soil = findViewById(R.id.soil);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tempS = Objects.requireNonNull(dataSnapshot.child("temp").getValue()).toString();
                String humdS = Objects.requireNonNull(dataSnapshot.child("humd").getValue()).toString();
                String rainS = Objects.requireNonNull(dataSnapshot.child("rain").getValue()).toString();
                String soilS = Objects.requireNonNull(dataSnapshot.child("soil").getValue()).toString();

                temp.setText("Temperature:-" + tempS);
                humd.setText("Humidity :- " + humdS);
                rain.setText("Rainfall :-" + rainS);
                soil.setText("Soil Moisture :-" + soilS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void checkUser() {
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(MainActivity.this, "Login First", Toast.LENGTH_LONG).show();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
