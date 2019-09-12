package io.github.swapnadeepmohapatra.Parking.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import io.github.swapnadeepmohapatra.Parking.R;

public class ParkingLotActivity extends AppCompatActivity {

    private DatabaseReference parkRef;
    private Button bookBtn;
    ImageView imageViewInfo;
    private Button bookBtnBike;
    ImageView imageViewInfoBike;

    int carPrice = 20;
    int bikePrice = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String loc = intent.getStringExtra("loc");

        parkRef = FirebaseDatabase.getInstance().getReference().child("ParkingSpot").child(name);
        bookBtn = findViewById(R.id.button_book);
        imageViewInfo = findViewById(R.id.imageViewInfo);
        bookBtnBike = findViewById(R.id.button_book_bike);
        imageViewInfoBike = findViewById(R.id.imageViewInfoBike);

        parkRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageView imageView = findViewById(R.id.imageView2);
                TextView textView = findViewById(R.id.textViewLoc);
                TextView textViewBook = findViewById(R.id.textViewBook);
                TextView textViewAva = findViewById(R.id.textViewAva);
                textView.setText(dataSnapshot.child("name").getValue().toString());
                textViewAva.setText(dataSnapshot.child("empty").getValue().toString());
                textViewBook.setText(dataSnapshot.child("booked").getValue().toString());
                Picasso.get().load(Uri.parse(dataSnapshot.child("img").getValue().toString())).into(imageView);


                bookBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ParkingLotActivity.this, BookingActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("loc", loc);
                        startActivity(intent);
                    }
                });
                bookBtnBike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ParkingLotActivity.this, BikeParkActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("loc", loc);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        imageViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(ParkingLotActivity.this);
                dialog.setTitle("Price Estimate");
                dialog.setMessage("Write the time in hrs");
                LinearLayout layout = new LinearLayout(ParkingLotActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(2);
                final EditText titleBox = new EditText(ParkingLotActivity.this);
                titleBox.setHint("In Time");
                titleBox.setFilters(filterArray);
                layout.addView(titleBox);
                final EditText descriptionBox = new EditText(ParkingLotActivity.this);
                descriptionBox.setHint("Out Time");
                descriptionBox.setFilters(filterArray);
                layout.addView(descriptionBox);
                final TextView estimateText = new TextView(ParkingLotActivity.this);
                estimateText.setText("₹ 20/hour");
//                estimateText.setTextColor(getResources().getColor(R.color.fui_bgGitHub));
                estimateText.setPadding(50, 10, 10, 10);
                layout.addView(estimateText);
                dialog.setCancelable(false);

                dialog.setPositiveButton("Get Estimate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!(descriptionBox.getText().toString().matches("") && titleBox.getText().toString().matches(""))) {
                            int a = Integer.valueOf(descriptionBox.getText().toString());
                            int b = Integer.valueOf(titleBox.getText().toString());
                            int c = (a - b) * carPrice;
                            estimateText.setText("₹ " + String.valueOf(c));
                            Toast.makeText(ParkingLotActivity.this, ("₹ " + String.valueOf(c)), Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder builder = new AlertDialog.Builder(ParkingLotActivity.this);
                            builder.setTitle("Price Estimate: ");
                            builder.setMessage("₹ " + String.valueOf(c));
                            builder.show();
                        } else {
                            Toast.makeText(ParkingLotActivity.this, "Enter The Time", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                dialog.setView(layout);
                dialog.show();
            }
        });
        imageViewInfoBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(ParkingLotActivity.this);
                dialog.setTitle("Price Estimate");
                dialog.setMessage("Write the time in hrs");
                LinearLayout layout = new LinearLayout(ParkingLotActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(2);
                final EditText titleBox = new EditText(ParkingLotActivity.this);
                titleBox.setHint("In Time");
                titleBox.setFilters(filterArray);
                layout.addView(titleBox);
                final EditText descriptionBox = new EditText(ParkingLotActivity.this);
                descriptionBox.setHint("Out Time");
                descriptionBox.setFilters(filterArray);
                layout.addView(descriptionBox);
                final TextView estimateText = new TextView(ParkingLotActivity.this);
                estimateText.setText("₹ 10/hour");
//                estimateText.setTextColor(getResources().getColor(R.color.fui_bgGitHub));
                estimateText.setPadding(50, 10, 10, 10);
                layout.addView(estimateText);
                dialog.setCancelable(false);

                dialog.setPositiveButton("Get Estimate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!(descriptionBox.getText().toString().matches("") && titleBox.getText().toString().matches(""))) {
                            int a = Integer.valueOf(descriptionBox.getText().toString());
                            int b = Integer.valueOf(titleBox.getText().toString());
                            int c = (a - b) * bikePrice;
                            estimateText.setText("₹ " + String.valueOf(c));
                            Toast.makeText(ParkingLotActivity.this, ("₹ " + String.valueOf(c)), Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder builder = new AlertDialog.Builder(ParkingLotActivity.this);
                            builder.setTitle("Price Estimate: ");
                            builder.setMessage("₹ " + String.valueOf(c));
                            builder.show();
                        } else {
                            Toast.makeText(ParkingLotActivity.this, "Enter The Time", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                dialog.setView(layout);
                dialog.show();
            }
        });
    }
}
