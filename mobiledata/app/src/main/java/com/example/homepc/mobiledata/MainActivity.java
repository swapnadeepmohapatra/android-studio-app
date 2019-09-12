package com.example.homepc.mobiledata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    // controls
    ImageButton tBMobileData;
    boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load controls
        tBMobileData = (ImageButton) findViewById(R.id.tBMobileData);
        // check current state first of mobile data

        mobilecheack();

        // set click event for button
        tBMobileData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mobilecheack();
                // toggle state and set image according to state
                if (state) {

                    toggleMobileDataConnection(false);

                    tBMobileData.setImageResource(R.drawable.ic_launcher_background);

                } else {

                    // on - fore
                    // off - back
                    toggleMobileDataConnection(true);
                    tBMobileData.setImageResource(R.drawable.ic_launcher_foreground);
                }

            }
        });

    }

    private void mobilecheack() {
        // TODO Auto-generated method stub

        state = isMobileDataEnable();

        // toggle state and set image according to state
        if (state) {
            tBMobileData.setImageResource(R.drawable.ic_launcher_foreground);
        } else {
            tBMobileData.setImageResource(R.drawable.ic_launcher_background);
        }

    }

    public void updateUI1(boolean state) {
        // set image according to state
        if (state) {
            tBMobileData.setImageResource(R.drawable.ic_launcher_background);

        } else {
            tBMobileData.setImageResource(R.drawable.ic_launcher_foreground);

        }
    }

    public boolean isMobileDataEnable() {

        boolean mobileDataEnabled = false; // Assume disabled
        ConnectivityManager cm = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // method is callable
            // get the setting for "mobile data"
            mobileDataEnabled = (Boolean) method.invoke(cm);
        } catch (Exception e) {
            // Some problem accessible private API and do whatever error
            // handling here as you want..
        }
        return mobileDataEnabled;
    }

    public boolean toggleMobileDataConnection(boolean ON) {
        try {
            // create instance of connectivity manager and get system service

            final ConnectivityManager conman = (ConnectivityManager) this
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            // define instance of class and get name of connectivity manager
            // system service class
            final Class conmanClass = Class
                    .forName(conman.getClass().getName());
            // create instance of field and get mService Declared field
            final Field iConnectivityManagerField = conmanClass
                    .getDeclaredField("mService");
            // Attempt to set the value of the accessible flag to true
            iConnectivityManagerField.setAccessible(true);
            // create instance of object and get the value of field conman
            final Object iConnectivityManager = iConnectivityManagerField
                    .get(conman);
            // create instance of class and get the name of iConnectivityManager
            // field
            final Class iConnectivityManagerClass = Class
                    .forName(iConnectivityManager.getClass().getName());
            // create instance of method and get declared method and type
            final Method setMobileDataEnabledMethod = iConnectivityManagerClass
                    .getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            // Attempt to set the value of the accessible flag to true
            setMobileDataEnabledMethod.setAccessible(true);
            // dynamically invoke the iConnectivityManager object according to
            // your need (true/false)
            setMobileDataEnabledMethod.invoke(iConnectivityManager, ON);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}