package com.example.shadrak.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bills_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    recyclerviewAdapter adapter;         //recyclerviewAdapter class
    DatabaseReference rootref;         //firebase connections
//    private FirebaseRecyclerAdapter<newBill, MyViewHolder> firebaseRecyclerAdapter;        //firebase connections
    ArrayList<newBill> list;
    String uid,amount, date, place, vendor, category;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;

    FloatingActionButton fab;


    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.bills_fragment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        list = new ArrayList<>();


        //Recyclerview
        recyclerView = rootview.findViewById(R.id.bills_recyclerview);
//        recyclerView.hasFixedSize();

        populate_list(userId);

        rootref = FirebaseDatabase.getInstance().getReference();
        Query query = rootref.child("Bills").child(userId);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new recyclerviewAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "clicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getContext(), "lamba clicked",Toast.LENGTH_SHORT).show();
            }
        }));


        return rootview;
    }

    private void populate_list(String userId) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        rootref = database.getReference().child("Bills").child(userId);



        rootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    amount = data.child("amount").getValue().toString();
                    date = data.child("date").getValue().toString();
                    place = data.child("place").getValue().toString();
                    vendor = data.child("vendor").getValue().toString();
                    category = data.child("category").getValue().toString();

                    list.add(new newBill(amount, date, place, vendor, category));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
