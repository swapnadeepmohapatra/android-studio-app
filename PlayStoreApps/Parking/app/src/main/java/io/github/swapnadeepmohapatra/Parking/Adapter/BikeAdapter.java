package io.github.swapnadeepmohapatra.Parking.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import io.github.swapnadeepmohapatra.Parking.Activity.BookedActivity;
import io.github.swapnadeepmohapatra.Parking.Model.Car;
import io.github.swapnadeepmohapatra.Parking.R;

public class BikeAdapter extends ArrayAdapter<Car> {
    public BikeAdapter(Context context, int resource, List<Car> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.bike_item, parent, false);
        }

        final Car car = getItem(position);
        final CheckBox checkBox = convertView.findViewById(R.id.checkBoxBike);
        final TextView textView = convertView.findViewById(R.id.textNumberBike);
        textView.setText("B" + String.valueOf(position + 1));
        if (car.isState()) {
            checkBox.setChecked(true);
            checkBox.setEnabled(false);
        } else {
            checkBox.setChecked(false);
            checkBox.setEnabled(true);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(true);
                        checkBox.setEnabled(false);

//                        FirebaseDatabase.getInstance().getReference().child("Bike").child(car.getId()).child("state").setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(getContext(), "Booked", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getContext(), BookedActivity.class);
//                                    intent.putExtra("ticket", car.getTicket());
//                                    intent.putExtra("id", car.getId());
//                                    getContext().startActivity(intent);
//                                }
//                            }
//                        });
                        final int[] booked = new int[1];
                        final int[] available = new int[1];
                        FirebaseDatabase.getInstance().getReference().child("ParkingSpot").child(car.getPlace()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                booked[0] = Integer.parseInt(dataSnapshot.child("booked").getValue().toString());
                                available[0] = Integer.parseInt(dataSnapshot.child("empty").getValue().toString());
                                Log.i("", "onDataChange: " + booked[0]);
                                Log.i("", "onDataChange: " + available[0]);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                        FirebaseDatabase.getInstance().getReference().child("Bike").child(car.getId()).child("state").setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Booked", Toast.LENGTH_SHORT).show();
                                    final Intent intent = new Intent(getContext(), BookedActivity.class);
                                    intent.putExtra("ticket", car.getTicket());
                                    intent.putExtra("id", car.getId());
                                    Toast.makeText(getContext(), car.getLocation(), Toast.LENGTH_SHORT).show();
                                    intent.putExtra("place", car.getPlace());
                                    intent.putExtra("loc", car.getLocation());
                                    FirebaseDatabase.getInstance().getReference().child("ParkingSpot").child(car.getPlace()).child("booked").setValue(String.valueOf(booked[0] + 1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            FirebaseDatabase.getInstance().getReference().child("ParkingSpot").child(car.getPlace()).child("empty").setValue(String.valueOf(available[0] - 1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    getContext().startActivity(intent);
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });

//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                }
//            });
        }
        return convertView;
    }
}
