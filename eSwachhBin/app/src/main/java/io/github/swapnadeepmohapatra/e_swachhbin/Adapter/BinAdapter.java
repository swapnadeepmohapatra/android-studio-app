package io.github.swapnadeepmohapatra.e_swachhbin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import io.github.swapnadeepmohapatra.e_swachhbin.Activites.MapsActivity;
import io.github.swapnadeepmohapatra.e_swachhbin.Model.BinItem;
import io.github.swapnadeepmohapatra.e_swachhbin.R;

public class BinAdapter extends ArrayAdapter<BinItem> {
    public BinAdapter(Context context, int resource, List<BinItem> objects) {
        super(context, resource, objects);
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.bin_item, parent, false);
        }

        final BinItem message = getItem(position);

        TextView status = convertView.findViewById(R.id.textViewStatus);
        TextView location = convertView.findViewById(R.id.textViewLocation);
        TextView emptied = convertView.findViewById(R.id.textViewEmpty);
        ImageView image = convertView.findViewById(R.id.imageViewBin);
        LinearLayout layout = convertView.findViewById(R.id.linearLayoutImg);

        Picasso.get().load(Objects.requireNonNull(message).getImage()).into(image);
        status.setText(Objects.requireNonNull(message).getStatus());
        location.setText("Location: " + message.getLocation());
        emptied.setText("Last Emptied: " + message.getLastEmptied());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra("log", Double.parseDouble(message.getLocationLog()));
                intent.putExtra("lat", Double.parseDouble(message.getLocationLat()));
                getContext().startActivity(intent);
            }
        });


        return convertView;
    }
}
