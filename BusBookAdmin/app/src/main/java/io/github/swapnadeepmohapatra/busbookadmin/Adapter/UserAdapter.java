package io.github.swapnadeepmohapatra.busbookadmin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;

import java.util.List;

import io.github.swapnadeepmohapatra.busbookadmin.Model.BusItem;
import io.github.swapnadeepmohapatra.busbookadmin.Model.UserItem;
import io.github.swapnadeepmohapatra.busbookadmin.R;

public class UserAdapter extends ArrayAdapter<UserItem> {
    public UserAdapter(@NonNull Context context, int resource, @NonNull List<UserItem> objects) {
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
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.users_item, parent, false);
        }
        final UserItem userItem = getItem(position);

        LinearLayout linearLayout = convertView.findViewById(R.id.linearLayoutUser);
        TextView name = convertView.findViewById(R.id.textViewUserName);
        TextView mail = convertView.findViewById(R.id.textViewUserMail);
        TextView rfid = convertView.findViewById(R.id.textViewUserRfid);

        name.setText("Name: " + userItem.getUsername());
        mail.setText("E-Mail: " + userItem.getEmail());
        rfid.setText("RFID NO.: " + userItem.getRfid());


        return convertView;
    }
}
