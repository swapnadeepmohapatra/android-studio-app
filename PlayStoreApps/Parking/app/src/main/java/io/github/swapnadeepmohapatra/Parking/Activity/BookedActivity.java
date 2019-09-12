package io.github.swapnadeepmohapatra.Parking.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

import io.github.swapnadeepmohapatra.Parking.MainActivity;
import io.github.swapnadeepmohapatra.Parking.R;
public class BookedActivity extends AppCompatActivity {

    TextView textViewId;
    TextView textViewTicket;
    TextView textViewRef;
    Random rand = new Random();
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String ticket = intent.getStringExtra("ticket");

        int rand_int2 = rand.nextInt(1000000);
        final String ticketRnd = String.valueOf(rand_int2);
        final String ticketRef = randomAlphaNumeric(12);

        textViewId = findViewById(R.id.textViewId);
        textViewTicket = findViewById(R.id.textViewTicket);
        textViewRef = findViewById(R.id.textViewRef);
        HashMap<String, String> map = new HashMap<>();
        map.put("Seat", String.valueOf((Integer.parseInt(id) + 1)));
        map.put("Ticket", ticketRnd);
        map.put("Ref", ticketRef);


        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    textViewId.setText("Your Parking Lot Number is: B" + (Integer.parseInt(id) + 1));
                    textViewTicket.setText("Ticket Number: " + ticketRnd);
                    textViewRef.setText("Booking Ref: " + ticketRef);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Go Back ?")
                .setMessage("Are you sure you want to go back?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(BookedActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }).create().show();
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
