package io.github.swapnadeepmohapatra.nirmiti.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import io.github.swapnadeepmohapatra.nirmiti.R;

public class BinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin);

        Intent i = getIntent();
        int a = i.getIntExtra("perc", 100);
        String percent = String.valueOf(a);

//        if (percent <= 100 & percent >= 91) {
//            image.setImageResource(R.drawable.delete_bin_red);
//            status.setText(R.string.binFull);
//        } else if (percent <= 90 & percent >= 80) {
//            image.setImageResource(R.drawable.delete_bin_yellow);
//            status.setText(R.string.binMid1);
//        } else if (percent <= 79 & percent >= 51) {
//            image.setImageResource(R.drawable.delete_bin_yellow);
//            status.setText(R.string.binMid2);
//        } else if (percent <= 50 & percent >= 11) {
//            image.setImageResource(R.drawable.delete_bin_yellow);
//            status.setText(R.string.binMid3);
//        } else if (percent <= 10 & percent >= 0) {
//            image.setImageResource(R.drawable.delete_bin_green);
//            status.setText(R.string.binEmpty);
//        } else {
//            image.setImageResource(R.drawable.common_full_open_on_phone);
//            status.setText(R.string.error);
//        }
    }
}
