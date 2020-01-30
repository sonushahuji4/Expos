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
import com.google.firebase.database.FirebaseDatabase;

import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

public class Homeactivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    public String uid;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseDatabase ref;


//    FloatingActionMenu floatingActionMenu;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        uid = bundle.getString("uid");

        Bundle bund = new Bundle();
        bund.putString("userid", uid);
        bills_fragment billsFragment = new bills_fragment();
        billsFragment.setArguments(bund);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new bills_fragment(), "Bills");
        adapter.add(new camera_fragment(), "Camera");
        adapter.add(new summary_fragment(), "Summary");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        mAuth = FirebaseAuth.getInstance();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), formpopup.class);
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            case R.id.profile:
                Intent i = new Intent(this, ProfileActivity.class);
                i.putExtra("uid", uid);
                startActivity(i);
                Toast.makeText(this, "Profile Page", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

