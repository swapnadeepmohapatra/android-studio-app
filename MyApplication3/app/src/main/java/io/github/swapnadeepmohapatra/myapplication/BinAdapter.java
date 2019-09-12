package io.github.swapnadeepmohapatra.myapplication;

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

        final BinItem message = getItem(getCount() - position - 1);

        TextView status = convertView.findViewById(R.id.textViewStatus);
        TextView location = convertView.findViewById(R.id.textViewLocation);
        TextView emptied = convertView.findViewById(R.id.textViewEmpty);

        status.setText("Name: " + message.getName());
        location.setText("Location: " + message.getLocation());
        emptied.setText("Date: " + message.getDate() +" Time: "+ message.getTime());

        return convertView;
    }
}
