package com.example.shadrak.expensestracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

// imports from line 10-18 are for Navigation Drawer, dlt it if doesn't work

public class Homeactivity extends AppCompatActivity {
    Button btnlogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        btnlogout = findViewById(R.id.button5);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent inToMain = new Intent(Homeactivity.this, MainActivity.class);
                startActivity(inToMain);
            }
        });
    }
}

// Code for Navigation Drawer
// Delete this if not working

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_navigation_drawer); // example_navigation_drawer

        toolbar = findViewById(R.id.tool_bar); //tool_bar
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getGroupId()) {
            case R.id.navi_bills;
                Toast.makeText(this, "Bills Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navi_bills;
                Toast.makeText(this, "Bills Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navi_bills;
                Toast.makeText(this, "Bills Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navi_bills;
                Toast.makeText(this, "Bills Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}