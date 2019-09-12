package io.github.swapnadeepmohapatra.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ImageView bin;
    TextView textViewStatus, number;
    Button openBtn, closeBtn;
    String percentageGarbage;
    int rawInt;
    int aa;
    //    FirebaseApp app = FirebaseApp.initializeApp(this);
    DatabaseReference dustRef;
    DatabaseReference rootRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("iottest-89b28") // Required for Analytics.
                .setApiKey("AIzaSyAOiurumCPlVGlZ9VbxErM_w68Pp8vnj0U") // Required for Auth.
                .setDatabaseUrl("https://iottest-89b28.firebaseio.com/") // Required for RTDB.

                // ...
                .build();
        FirebaseApp.initializeApp(this /* Context */, options, "secondary");


        bin = findViewById(R.id.imageView);
        textViewStatus = findViewById(R.id.textView3);
        number = findViewById(R.id.textView4);

        rootRef = FirebaseDatabase.getInstance().getReference();
        dustRef = FirebaseDatabase.getInstance().getReference("bins");

        openBtn = findViewById(R.id.buttonOpen);
        closeBtn = findViewById(R.id.buttonClose);

        dustRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("", "onDataChange: " + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        dustRef.child("binStatus").setValue("full");
        rootRef.child("garbage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                numder.setText(dataSnapshot.getValue().toString());
                rawInt = Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                aa = Math.abs(rawInt);
                percentageGarbage = String.valueOf(aa);
                Log.i("int", "onDataChange: " + aa);
                number.setText(percentageGarbage + " %");

                if (aa <= 100 & aa >= 91) {
                    bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_red));
                    textViewStatus.setText(R.string.binFull);
                } else if (aa <= 90 & aa >= 80) {
                    bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_yellow));
                    textViewStatus.setText(R.string.binMid1);
                } else if (aa <= 79 & aa >= 51) {
                    bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_yellow));
                    textViewStatus.setText(R.string.binMid2);
                } else if (aa <= 50 & aa >= 11) {
                    bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_yellow));
                    textViewStatus.setText(R.string.binMid3);
                } else if (aa <= 10 & aa >= 0) {
                    bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_green));
                    textViewStatus.setText(R.string.binEmpty);
                } else {
                    bin.setImageDrawable(getResources().getDrawable(R.drawable.common_full_open_on_phone));
                    textViewStatus.setText(R.string.error);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLid();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeLid();
            }
        });
    }

    private void closeLid() {
        rootRef.child("google").child("open").setValue("4");
    }

    private void openLid() {
        rootRef.child("google").child("open").setValue("3");
    }

    public void mapSend(View view) {
        startActivity(new Intent(MainActivity.this, MapsActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.requests:
                showPickupRequests();
                return true;
            case R.id.sign_out:
                Toast.makeText(this, "Sorry!!! Something is out of it's mind", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showPickupRequests() {
        startActivity(new Intent(MainActivity.this, RequestsActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
