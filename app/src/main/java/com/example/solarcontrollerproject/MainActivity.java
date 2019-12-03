package com.example.solarcontrollerproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView user;
    private AppBarConfiguration mAppBarConfiguration;
    FloatingActionButton fabshare;
    //private TextView displayname;
     //private TextView displayemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 3:
        ProcessPreference();
        //setTheme(R.style.AppTheme_NoActionBar);
        //setTheme(R.style.AppThemeGreen_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab2 = findViewById(R.id.fab2);

        //displayemail = findViewById(R.id.tv_email);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /* work on implicts Entent
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "share this with people", Snackbar.LENGTH_LONG)
                        .setAction("TEXT", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                email();
                            }
                        }).show();
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_control, R.id.nav_readings,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        //displayemail.setText(String.valueOf(user.getEmail()));
        View headerView = navigationView.getHeaderView(0);
        TextView navemail = (TextView) headerView.findViewById(R.id.tv_email);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tv_displayname);
        navemail.setText(user.getEmail());
        navUsername.setText(user.getDisplayName());

    }

  /*  private void showuser()  //finsih diplaying user when logged in
    {
        user = findViewById(R.id.ShowUser);
        user.setText("User "+ user.getUser() + " is now Logged In");
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        // TODO: 6. Finish all the options for the menu selections. Finished
        if (id == R.id.action_settings) {
            Intent intent0 = new Intent(this, SettingsScreen.class);
            startActivity(intent0);
            return true;
        }

        else if (id == R.id.signin) {
            Intent intent0 = new Intent(this, Login.class);
            startActivity(intent0);
            return true;
        }
        else if (id == R.id.writedata) {
            Intent intent1 = new Intent(this, WriteDB.class);
            startActivity(intent1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void ProcessPreference() {

        // TODO 9: This is to load the preference from the default PreferenceScreen setting screen.   finished.
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        // boolean grid = settings.getBoolean("check_box_preference_1", false);
        String color = settings.getString("list_preference_1", "1");
        Boolean tired = settings.getBoolean("check_box_preference_1", false);
        Log.d("MapleLeaf", "Color Index is: " + color);
        Log.d("MapleLeaf", "Tired? -  " + String.valueOf(tired));
        switch (Integer.parseInt(color)) //https://www.materialpalette.com/
        {
            case 1:
                setTheme(R.style.AppTheme_NoActionBar);
            break;

            case 2:   // Yellow
                setTheme(R.style.AppThemeGreen_NoActionBar);
               break;
            case 3:   // Sunset
                setTheme(R.style.AppThemeSunset_NoActionBar);
                break;

            default:
                setTheme(R.style.AppTheme_NoActionBar);
                break;
        }

    }

    }
