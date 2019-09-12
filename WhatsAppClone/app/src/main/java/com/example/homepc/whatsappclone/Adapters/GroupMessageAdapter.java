package com.example.homepc.whatsappclone.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.homepc.whatsappclone.Model.GroupMessage;
import com.example.homepc.whatsappclone.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class GroupMessageAdapter extends ArrayAdapter<GroupMessage> {


    public GroupMessageAdapter(Context context, int resource, List<GroupMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        TextView receiveTextView = convertView.findViewById(R.id.receiver_message_text);
        TextView sendTextView = convertView.findViewById(R.id.sender_message_text);
        TextView receiveTextName = convertView.findViewById(R.id.receiver_message_name);
        RelativeLayout relativeLayout = convertView.findViewById(R.id.re);

        GroupMessage message = getItem(position);

        String uid = Objects.requireNonNull(message).getuID();

        String currentUserId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        receiveTextView.setVisibility(View.INVISIBLE);
        sendTextView.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);
        receiveTextName.setVisibility(View.GONE);

        if (Objects.equals(uid, currentUserId)) {
            sendTextView.setVisibility(View.VISIBLE);
            sendTextView.setText(message.getText());
            sendTextView.setTextColor(Color.parseColor("#F1F1F1"));
            sendTextView.setTextSize(20);
        } else {
            receiveTextView.setVisibility(View.VISIBLE);
            receiveTextName.setVisibility(View.VISIBLE);
            receiveTextView.setText(message.getText());
            receiveTextView.setTextColor(Color.parseColor("#F1F1F1"));
            receiveTextView.setTextSize(20);
            receiveTextName.setText(message.getName());
            receiveTextName.setTextColor(Color.parseColor("#F1F1F1"));
            relativeLayout.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}