package io.github.swapnadeepmohapatra.nirmiti.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import io.github.swapnadeepmohapatra.nirmiti.Activities.BinActivity;
import io.github.swapnadeepmohapatra.nirmiti.Model.BinItem;
import io.github.swapnadeepmohapatra.nirmiti.R;

public class BinAdapter extends ArrayAdapter<BinItem> {
    public BinAdapter(Context context, int resource, List<BinItem> objects) {
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
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.bin_item, parent, false);
        }

        final BinItem bin = getItem(position);
        final int percent;

        TextView status = convertView.findViewById(R.id.textViewStatus);
        final TextView location = convertView.findViewById(R.id.textViewLocation);
        TextView percentage = convertView.findViewById(R.id.textViewPercentage);
        ImageView image = convertView.findViewById(R.id.imageViewBin);
        LinearLayout layout = convertView.findViewById(R.id.linearLayoutImg);

        percent = Integer.parseInt(bin.getpercentage());
        location.setText("Location: " + bin.getLocation());
        percentage.setText("Percentage: "+bin.getpercentage() + " %");

        if (percent <= 100 & percent >= 91) {
            image.setImageResource(R.drawable.delete_bin_red);
            status.setText(R.string.binFull);
        } else if (percent <= 90 & percent >= 80) {
            image.setImageResource(R.drawable.delete_bin_yellow);
            status.setText(R.string.binMid1);
        } else if (percent <= 79 & percent >= 51) {
            image.setImageResource(R.drawable.delete_bin_yellow);
            status.setText(R.string.binMid2);
        } else if (percent <= 50 & percent >= 11) {
            image.setImageResource(R.drawable.delete_bin_yellow);
            status.setText(R.string.binMid3);
        } else if (percent <= 10 & percent >= 0) {
            image.setImageResource(R.drawable.delete_bin_green);
            status.setText(R.string.binEmpty);
        } else {
            image.setImageResource(R.drawable.common_full_open_on_phone);
            status.setText(R.string.error);
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BinActivity.class);
                intent.putExtra("perc", (percent));
                intent.putExtra("loc", (bin.getLocation()));
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
