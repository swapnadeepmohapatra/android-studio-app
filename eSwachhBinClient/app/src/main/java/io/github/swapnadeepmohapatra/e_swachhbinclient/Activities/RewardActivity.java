package io.github.swapnadeepmohapatra.e_swachhbinclient.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.e_swachhbinclient.R;

public class RewardActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView moneyff, pointsrr;
    String uid;
    Button sendMoney;
    String pointsNumber;
    private String TAG = "RewardAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        firebaseAuth = FirebaseAuth.getInstance();
        moneyff = findViewById(R.id.textViewMoneyRr);
        pointsrr = findViewById(R.id.textViewPoints);
        sendMoney = findViewById(R.id.sendMoneyButton);

        Intent intent = getIntent();
        pointsNumber = intent.getStringExtra("points");

        Log.i(TAG, "onCreate: " + pointsNumber);

        double s = Double.parseDouble(pointsNumber);
        String moneyTxt = String.valueOf(s / 100);

        moneyff.setText("₹ " + moneyTxt);
        pointsrr.setText(pointsNumber);

        sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            }
        });
    }
}
