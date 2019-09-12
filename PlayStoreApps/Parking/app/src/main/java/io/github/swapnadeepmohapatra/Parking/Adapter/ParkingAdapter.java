package io.github.swapnadeepmohapatra.Parking.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.github.swapnadeepmohapatra.Parking.Model.Parking;
import io.github.swapnadeepmohapatra.Parking.Activity.ParkingLotActivity;
import io.github.swapnadeepmohapatra.Parking.R;

public class ParkingAdapter extends ArrayAdapter<Parking> {
    public ParkingAdapter(Context context, int resource, List<Parking> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.park_item, parent, false);
        }
        final Parking parking = getItem(position);

        TextView name = convertView.findViewById(R.id.textViewParkName);
//        TextView location = convertView.findViewById(R.id.textViewLocation);
        TextView empty = convertView.findViewById(R.id.textViewParkEmpty);
        TextView booked = convertView.findViewById(R.id.textViewParkBooked);
        LinearLayout layout = convertView.findViewById(R.id.linearLayout);

        booked.setText("Booked: " + parking.getBooked());
        name.setText(parking.getName());
        empty.setText("Available: " + parking.getEmpty());
//        location.setText("Address: " + parking.getLocation());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ParkingLotActivity.class);
                intent.putExtra("name", parking.getLocation());
                intent.putExtra("loc", parking.getName());
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
