package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.swapnadeepmohapatra.nirmiti.Adapter.ComplainAdapter;
import io.github.swapnadeepmohapatra.nirmiti.Model.ComplainItem;
import io.github.swapnadeepmohapatra.nirmiti.R;

public class BinActivity extends AppCompatActivity {

    private TextView textViewStatus;
    private TextView percentageBin;
    private ImageView bin;
    private ListView listBins;
    private DatabaseReference complainRef;
    private ComplainAdapter complainAdapter;
    private Button complinBtn;
    private String location;
    private DatabaseReference pointsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin);

        textViewStatus = findViewById(R.id.textView3);
        percentageBin = findViewById(R.id.textView4);
        bin = findViewById(R.id.imageView);
        complinBtn = findViewById(R.id.buttonComp);

        Intent i = getIntent();
        int aa = i.getIntExtra("perc", 100);
        location = i.getStringExtra("loc");
        String percent = String.valueOf(aa);

        complainRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        pointsRef = FirebaseDatabase.getInstance().getReference().child("Points").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        listBins = findViewById(R.id.complainList);

        List<ComplainItem> complainItems = new ArrayList<>();
        complainAdapter = new ComplainAdapter(this, R.layout.complain_item, complainItems);
        listBins.setAdapter(complainAdapter);

        percentageBin.setText(percent + " %");

        if (aa <= 100 & aa >= 91) {
            bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_red));
            textViewStatus.setText(R.string.binFull);
        } else if (aa <= 90 & aa >= 80) {
            bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_yellow));
            textViewStatus.setText(R.string.binMid1);
        } else if (aa <= 79 & aa >= 51) {
            bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_yellow));
            textViewStatus.setText(R.string.binMid2);
        } else if (aa <= 50 & aa >= 11) {
            bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_yellow));
            textViewStatus.setText(R.string.binMid3);
        } else if (aa <= 10 & aa >= 0) {
            bin.setImageDrawable(getResources().getDrawable(R.drawable.delete_bin_green));
            textViewStatus.setText(R.string.binEmpty);
        } else {
            bin.setImageDrawable(getResources().getDrawable(R.drawable.common_full_open_on_phone));
            textViewStatus.setText(R.string.error);
        }

        complainRef.child(location).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ComplainItem complainItem = dataSnapshot.getValue(ComplainItem.class);
                complainAdapter.add(complainItem);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        complinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestNewGroup();

            }
        });

    }

    private void requestNewGroup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle("Complain Form ");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText messageField = new EditText(this);
        messageField.setHint("Write Your message here");
        messageField.setTextColor(getResources().getColor(R.color.fui_bgGoogle));
        messageField.setHintTextColor(getResources().getColor(R.color.fui_bgGoogle));
        layout.addView(messageField);

        builder.setView(layout);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = messageField.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    Toast.makeText(BinActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                } else {
                    String saveCurrentDate, saveCurrentTime;

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd yyyy");
                    saveCurrentDate = currentDate.format(calendar.getTime());

                    SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
                    saveCurrentTime = currentTime.format(calendar.getTime());

                    Map<String, String> map = new HashMap<>();
                    map.put("Name", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName());
                    map.put("Location", location);
                    map.put("Message", message);
                    map.put("Date", saveCurrentDate);
                    map.put("Time", saveCurrentTime);

                    complainRef.child(location).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                pointsRef.child("").push().setValue("Point", "1").addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(BinActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();

    }
}
