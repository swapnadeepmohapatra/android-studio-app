package io.github.swapnadeepmohapatra.nirmiti.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.swapnadeepmohapatra.nirmiti.Activities.LocationMapsActivity;
import io.github.swapnadeepmohapatra.nirmiti.Model.ComplainItem;
import io.github.swapnadeepmohapatra.nirmiti.R;

public class ComplainAdapter extends ArrayAdapter<ComplainItem> implements View.OnClickListener {

    ComplainItem message;

    public ComplainAdapter(Context context, int resource, List<ComplainItem> objects) {
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
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.complain_item, parent, false);
        }

        message = getItem(getCount() - position - 1);

        TextView nameView = convertView.findViewById(R.id.textViewName);
        TextView messageView = convertView.findViewById(R.id.textViewMessage);
        TextView location = convertView.findViewById(R.id.textViewLocation);
        TextView dateView = convertView.findViewById(R.id.textViewDate);

        nameView.setText("Name: " + message.getName());
        messageView.setText("Message: " + message.getMessage());
        location.setText("Location: " + message.getLocation());
        dateView.setText("Date: " + message.getDate() + " Time: " + message.getTime());

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LocationMapsActivity.class);
                intent.putExtra("log", (message.getLocation()));
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.linearLayout) {
            Intent intent = new Intent(getContext(), LocationMapsActivity.class);
            intent.putExtra("log", Double.parseDouble(message.getLocation()));
            getContext().startActivity(intent);
        }
    }
}
