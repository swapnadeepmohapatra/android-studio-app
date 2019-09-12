package io.github.swapnadeepmohapatra.e_swachhbinclient.Activities;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import io.github.swapnadeepmohapatra.e_swachhbinclient.Fragments.AccountFragment;
import io.github.swapnadeepmohapatra.e_swachhbinclient.Fragments.BinNearMeFragment;
import io.github.swapnadeepmohapatra.e_swachhbinclient.Fragments.HomeFragment;
import io.github.swapnadeepmohapatra.e_swachhbinclient.Fragments.RewardFragment;
import io.github.swapnadeepmohapatra.e_swachhbinclient.R;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    DatabaseReference userRef;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        toolbar = getSupportActionBar();
        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Home");

        userRef = FirebaseDatabase.getInstance().getReference();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_money:
                    toolbar.setTitle("Money");
                    fragment = new AccountFragment();
                    break;
                case R.id.navigation_near_me:
                    toolbar.setTitle("Dustbins Near Me");
                    fragment = new BinNearMeFragment();
                    break;
                case R.id.navigation_reward:
                    toolbar.setTitle("Total Reward");
                    fragment = new RewardFragment();
                    break;
                default:
                    toolbar.setTitle("Home");
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

}
