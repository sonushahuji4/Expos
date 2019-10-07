package com.example.shadrak.expensestracker;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

//import com.github.clans.fab.FloatingActionButton;
//import com.github.clans.fab.FloatingActionMenu;

public class Homeactivity extends AppCompatActivity {
//    Button btnlogout;
//    FrameLayout frameLayout;
//    BottomNavigationView bottomNavigationView;
//    TextView textView;

    TabLayout tabLayout;
    ViewPager viewPager;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

//    FloatingActionMenu floatingActionMenu;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String uid = bundle.getString("uid");

        //btnlogout = findViewById(R.id.button5);
//        bottomNavigationView = findViewById(R.id.bottom_nav);
//        frameLayout = findViewById(R.id.framelayout);
        //textView = findViewById(R.id.textview);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new bills_fragment(), "Bills");
        adapter.add(new camera_fragment(), "Camera");
        adapter.add(new summary_fragment(), "Summary");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //textView.setText("Receipts");
//        frameLayout.setBackgroundResource(R.color.colorPrimaryDark);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(naviCustomListView);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), formpopup.class);
                startActivity(i);
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
                startActivity(new Intent(this,MainActivity.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this, ProfileActivity.class));
                Toast.makeText(this, "Profile Page", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener naviCustomListView = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            Log.i("nav","bottom_nav");
//            switch (menuItem.getItemId()) {
//                case R.id.bill:
//                    Log.i("nav","bill");
//                    textView.setText("Bills");
//                    frameLayout.setBackgroundResource(R.color.colorAccent);
//                    break;
//                case R.id.camera:
//                    Log.i("nav","reports");
//                    textView.setText("Camera");
//                    frameLayout.setBackgroundResource(R.color.colorPrimaryDark);
//                    break;
//                case R.id.summary:
//                    Log.i("nav","summary");
//                    textView.setText("Summary");
//                    frameLayout.setBackgroundResource(R.color.colorAccent);
//                    break;
//
//            }
//            return true;
//        }
//    };


}

