package io.github.swapnadeepmohapatra.elezione.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URL;
import java.util.Arrays;

import io.github.swapnadeepmohapatra.elezione.Fragments.HomeFragment;
import io.github.swapnadeepmohapatra.elezione.Fragments.NewsFragment;
import io.github.swapnadeepmohapatra.elezione.Fragments.ResultsFragment;
import io.github.swapnadeepmohapatra.elezione.R;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static java.security.AccessController.getContext;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 1;
    TextView headerUserName;
    TextView headerUserEmail;
    ImageView headerUserPhoto;
    View navHeaderView;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(NavActivity.this, CountDownActivity.class));

            }
        });

        checkUser();

        if (mAuth.getCurrentUser() != null) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_nav);
            headerUserName = navHeaderView.findViewById(R.id.userNameNav);
            headerUserEmail = navHeaderView.findViewById(R.id.textViewUserEmail);
            headerUserPhoto = navHeaderView.findViewById(R.id.imageViewProfile);
            headerUserName.setText(mAuth.getCurrentUser().getDisplayName());
            headerUserEmail.setText(mAuth.getCurrentUser().getEmail());
            Picasso.get().load(mAuth.getCurrentUser().getPhotoUrl()).into(headerUserPhoto);
//            LinearLayout linearLayout = navHeaderView.findViewById(R.id.headerLinearLayout);
        }

        displayFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_gallery:
                fragment = new HomeFragment();
                Toast.makeText(this, "Gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_news:
                fragment = new NewsFragment();
                Toast.makeText(this, "SlideShow", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_results:
                fragment = new ResultsFragment();
                Toast.makeText(this, "Manage", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_polls:
                fragment = new ResultsFragment();
                Toast.makeText(this, "Manage", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                fragment = new HomeFragment();
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                fragment = new HomeFragment();
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }
        displayFragment(fragment);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_area, fragment)
                .commit();
    }

    private void checkUser() {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(NavActivity.this, "Login First", Toast.LENGTH_LONG).show();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }
}
