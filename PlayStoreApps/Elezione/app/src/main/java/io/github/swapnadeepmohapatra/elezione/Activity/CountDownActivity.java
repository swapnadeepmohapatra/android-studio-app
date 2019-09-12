package io.github.swapnadeepmohapatra.elezione.Activity;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.github.swapnadeepmohapatra.elezione.R;

public class CountDownActivity extends AppCompatActivity {

    private long milliseconds;
    private long startTime;
    private long diff;
    private TextView ViewDays;
    private TextView ViewHours;
    private TextView ViewMinutes;
    private TextView ViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        ViewDays = findViewById(R.id.txtViewDays);
        ViewHours = findViewById(R.id.txtHour);
        ViewMinutes = findViewById(R.id.txtMinute);
        ViewSecond = findViewById(R.id.txtSecond);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        formatter.setLenient(false);


        String endTime = "23.05.2019, 17:00:00";

        Date endDate;
        try {
            endDate = formatter.parse(endTime);
            milliseconds = endDate.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        startTime = System.currentTimeMillis();

        diff = milliseconds - startTime;

        CountDownTimer mCountDownTimer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                startTime = startTime - 1;
                Long serverUptimeSeconds =
                        (millisUntilFinished - startTime) / 1000;

                String daysLeft = String.format("%d", serverUptimeSeconds / 86400);
                ViewDays.setText(daysLeft);

                String hoursLeft = String.format("%d", (serverUptimeSeconds % 86400) / 3600);
                ViewHours.setText(hoursLeft);

                String minutesLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) / 60);

                ViewMinutes.setText(minutesLeft);

                String secondsLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) % 60);
                ViewSecond.setText(secondsLeft);

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}

