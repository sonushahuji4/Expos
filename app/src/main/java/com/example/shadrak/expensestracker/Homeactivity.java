package com.example.shadrak.expensestracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class Homeactivity extends AppCompatActivity {
    Button btnlogout;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    TextView textView;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    FloatingActionMenu floatingActionMenu;
    FloatingActionButton newbill, camera, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        btnlogout = findViewById(R.id.button5);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.framelayout);
        textView = findViewById(R.id.textview);

        // floating action buttons
        floatingActionMenu = findViewById(R.id.floating_action_menu);
        newbill = findViewById(R.id.Bill);
        camera = findViewById(R.id.camera);
        image = findViewById(R.id.clip);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent inToMain = new Intent(Homeactivity.this, MainActivity.class);
                startActivity(inToMain);
            }
        });

        textView.setText("Receipts");
        frameLayout.setBackgroundResource(R.color.colorPrimaryDark);

        bottomNavigationView.setOnNavigationItemSelectedListener(naviCustomListView);

        newbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Add new Bill", Toast.LENGTH_SHORT).show();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"click new bill", Toast.LENGTH_SHORT).show();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Open Gallery", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logout:
                Button option;
                option = findViewById(R.id.logout);

                option.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        Intent inToMain = new Intent(Homeactivity.this, MainActivity.class);
                        startActivity(inToMain);
                    }
                });
                return true;
            case R.id.help:
                Toast.makeText(this, "Help option selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener naviCustomListView = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.bill:
                    textView.setText("Bills");
                    frameLayout.setBackgroundResource(R.color.colorAccent);
                    break;
                case R.id.reports:
                    textView.setText("Reports");
                    frameLayout.setBackgroundResource(R.color.colorPrimaryDark);
                    break;
                case R.id.summary:
                    textView.setText("Summary");
                    frameLayout.setBackgroundResource(R.color.colorAccent);
                    break;
                case R.id.events:
                    textView.setText("Events");
                    frameLayout.setBackgroundResource(R.color.colorPrimaryDark);
                    break;
            }
            return true;
        }
    };


}

