package io.github.swapnadeepmohapatra.busbookadmin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.swapnadeepmohapatra.busbookadmin.Model.UserItem;
import io.github.swapnadeepmohapatra.busbookadmin.R;

public class UserRchAdapter extends ArrayAdapter<UserItem> {
    public UserRchAdapter(@NonNull Context context, int resource, @NonNull List<UserItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.users_item, parent, false);
        }
        final UserItem userItem = getItem(position);
        final ArrayList<String> arrayListChild = new ArrayList<>();
        LinearLayout linearLayout = convertView.findViewById(R.id.linearLayoutUser);
        TextView name = convertView.findViewById(R.id.textViewUserName);
        TextView mail = convertView.findViewById(R.id.textViewUserMail);
        TextView rfid = convertView.findViewById(R.id.textViewUserRfid);

        name.setText("Name: " + userItem.getUsername());
        mail.setText("E-Mail: " + userItem.getEmail());
        rfid.setText("RFID NO.: " + userItem.getRfid());

        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Toast.makeText(getContext(), dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
                Log.i("", "onDataChange: " + dataSnapshot);


                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                    Toast.makeText(getContext(), child.getKey().toString(), Toast.LENGTH_SHORT).show();
                    arrayListChild.add(child.getKey());
                }
                Log.i("", "onDataChange: " + arrayListChild);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("", "onClick: " + arrayListChild.get(position));
                final String as = arrayListChild.get(position);
//                Toast.makeText(getContext(), "" + as, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Recharge RFID");
                alertDialog.setMessage("Enter Amount to be added");

                final EditText input = new EditText(getContext());
                input.setHint("Enter Amount in rupees");
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Added " + input.getText(), Toast.LENGTH_SHORT).show();
                        int amt = Integer.parseInt(input.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Users").child(as).child("money").setValue((userItem.getMoney()) + amt);

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alertDialog.show();
            }
        });

        return convertView;
    }
}
