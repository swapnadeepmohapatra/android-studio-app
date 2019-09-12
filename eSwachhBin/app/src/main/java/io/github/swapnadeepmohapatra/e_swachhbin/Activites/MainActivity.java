package io.github.swapnadeepmohapatra.e_swachhbin.Activites;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.swapnadeepmohapatra.e_swachhbin.Fragments.AccountFragment;
import io.github.swapnadeepmohapatra.e_swachhbin.Fragments.DustbinNearMeFragment;
import io.github.swapnadeepmohapatra.e_swachhbin.Fragments.HomeFragment;
import io.github.swapnadeepmohapatra.e_swachhbin.Fragments.RewardFragment;
import io.github.swapnadeepmohapatra.e_swachhbin.R;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    Fragment fragment;
    DatabaseReference userRef;
    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    private static final int RC_SIGN_IN = 1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        toolbar = getSupportActionBar();
        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Welcome to e-SwachhBin");
        userRef = FirebaseDatabase.getInstance().getReference();
        rootRef = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Logging In");
        progressDialog.setMessage("Please Wait...");

        checkUser();
        displayFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Welcome to e-SwachhBin");
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_money:
                    toolbar.setTitle("My Account");
                    fragment = new AccountFragment();
                    break;
                case R.id.navigation_near_me:
                    toolbar.setTitle("Dustbins Near Me");
                    fragment = new DustbinNearMeFragment();
                    break;
                case R.id.navigation_reward:
                    toolbar.setTitle("Total Reward");
                    fragment = new RewardFragment();
                    break;
                default:
                    toolbar.setTitle("Welcome to e-SwachhBin");
                    fragment = new HomeFragment();
                    break;
            }
            displayFragment(fragment);
            return true;
        }
    };

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_area, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.change:
                showRFIDChange();
                return true;
            case R.id.sign_out:
                firebaseAuth.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showRFIDChange() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Change RFID");
        dialogBuilder.setMessage("Enter your new RFID Tag ID");
        dialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String b = editText.getText().toString();
                userRef.child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).child("id").setValue(b).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void checkUser() {
        if (firebaseAuth.getCurrentUser() == null) {
            progressDialog.show();
            Toast.makeText(MainActivity.this, "Login First", Toast.LENGTH_LONG).show();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finish();
            }
        }
    }
}
