package io.github.swapnadeepmohapatra.e_swachhbinncsc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ImageView bin;
    TextView textViewStatus, numder;
    Button openBtn, closeBtn;
    String percentageGarbage;
    int rawInt;
    int aa;
    DatabaseReference dustRef = FirebaseDatabase.getInstance().getReference("bins");
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bin = findViewById(R.id.imageView);
        textViewStatus = findViewById(R.id.textView3);
        numder = findViewById(R.id.textView4);

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
                numder.setText(percentageGarbage + " %");

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
}

