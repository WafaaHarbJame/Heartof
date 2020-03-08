package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.heartoftheworldapp.heartoftheworld.Fragments.HomeBasicFragment;
import com.heartoftheworldapp.heartoftheworld.Fragments.SettingFragment;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.ChooseLangauageBottomDialog;
import com.heartoftheworldapp.heartoftheworld.R;
import com.heartoftheworldapp.heartoftheworld.ui.share.ShareFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor_signUp;
    private Boolean saveLogin;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.menu_home));
        sharedPreferences = getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);

        saveLogin = sharedPreferences.getBoolean(AppConstants.ISLOGIN, false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        Menu menu = navigationView.getMenu();
        if (saveLogin) {
            menu.findItem(R.id.nav_exitapp).setTitle(R.string.logout);


        }
        else {
            menu.findItem(R.id.nav_exitapp).setTitle(getString(R.string.login));




        }

        // View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeBasicFragment(), "HomeFragment").commit();


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
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);





        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            toolbar.setTitle(getString(R.string.menu_home));
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeBasicFragment(), "HomeFragment").commit();


        } else if (id == R.id.nav_setting) {

            toolbar.setTitle(getString(R.string.menu_setting));
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new SettingFragment(), "HomeFragment").commit();




        }
        else if (id == R.id.nav_profile) {
            toolbar.setTitle(getString(R.string.action_settings));

            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new ShareFragment(), "HomeFragment").commit();


        }





        else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "share the app : https://play.google.com/store/apps/details?id=com.heartoftheworldapp.heartoftheworld");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);


        }
        else if (id == R.id.nav_exitapp) {

            if (saveLogin) {
                editor_signUp = sharedPreferences.edit();
                editor_signUp.clear();
                editor_signUp.apply();
                editor_signUp.commit();
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                intent.putExtra(AppConstants.ISLOGIN, false);
                startActivity(intent);
                finish();
            }

            else {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);


            }





            }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}
