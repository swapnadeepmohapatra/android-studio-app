package io.github.swapnadeepmohapatra.busbookadmin.Adapter;

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

import io.github.swapnadeepmohapatra.busbookadmin.Model.BusItem;
import io.github.swapnadeepmohapatra.busbookadmin.R;

public class BusAdapter extends ArrayAdapter<BusItem> {
    public BusAdapter(@NonNull Context context, int resource, @NonNull List<BusItem> objects) {
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
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.bus_item, parent, false);
        }
        final BusItem busItem = getItem(position);

        LinearLayout linearLayout = convertView.findViewById(R.id.linearLayout);
        TextView name = convertView.findViewById(R.id.textViewName);
        TextView number = convertView.findViewById(R.id.textViewNumber);
        TextView time = convertView.findViewById(R.id.textViewTime);

        name.setText(busItem.getName());
        number.setText(busItem.getNumber());
        time.setText(busItem.getTime());

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), BookActivity.class);
//                intent.putExtra("name", busItem.getName());
//                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
