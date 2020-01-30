package com.example.shadrak.expensestracker;

import android.content.Intent;
import android.os.Bundle;
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
    //    recyclerviewAdapter adapter;         //recyclerviewAdapter class
    DatabaseReference rootref;         //firebase connections
    private FirebaseRecyclerAdapter<newBill, MyViewHolder> firebaseRecyclerAdapter;        //firebase connections
    ArrayList<newBill> list;
    String uid,amount, date, place, vendor, category;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;

    FloatingActionButton fab;


    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        linearlist();
        View rootview = inflater.inflate(R.layout.bills_fragment, container, false);

        //Recyclerview
        recyclerView = rootview.findViewById(R.id.bills_recyclerview);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

//        fab = rootview.findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getContext(), formpopup.class);
//                i.putExtra("uid",userId);
//                startActivity(i);
//            }
//        });

//        Bundle bundle = new Bundle();            //passing userid
//        uid = bundle.getString("userid");

//        adapter = new recyclerviewAdapter(getActivity(),this.list);
//        recyclerView.setAdapter(adapter);

//        Log.d("user",uid);
        rootref = FirebaseDatabase.getInstance().getReference();
        Query query = rootref.child("Bills").child(userId);
//        FirebaseRecyclerOptions<newBill> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<newBill>()
//                .setQuery(query, newBill.class)
//                .build();

        FirebaseRecyclerOptions<newBill> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<newBill>()
                .setQuery(query, new SnapshotParser<newBill>() {
                    @NonNull
                    @Override
                    public newBill parseSnapshot(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datasnapshot : snapshot.getChildren()
                        ) {

                            for (DataSnapshot dataSnapshot1 : datasnapshot.getChildren()){

                                amount = dataSnapshot1.child("amount").getValue().toString();
                                date = dataSnapshot1.child("date").getValue().toString();
                                place = dataSnapshot1.child("place").getValue().toString();
                                vendor = dataSnapshot1.child("vendor").getValue().toString();
                                category = dataSnapshot1.child("category").getValue().toString();
                            }

//                            Log.d("done","amount" +
//                                    amount);
                        }
                        return new newBill(amount,
                                date,
                                place,
                                vendor,
                                category);
                    }
                })
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<newBill, MyViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull newBill model) {
                holder.setTextamount(model.getAmount());
                holder.setTextdate(model.getDate());
                holder.setTextplace(model.getPlace());
                holder.setTextvendor(model.getVendor());
                holder.setTextcategory(model.getCategory());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dataitems, viewGroup, false);
                return new MyViewHolder(view);
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView v_name, date, cost, place, category;

        public MyViewHolder(View view) {
            super(view);

            v_name = view.findViewById(R.id.vendor_name);
            date = view.findViewById(R.id.date);
            cost = view.findViewById(R.id.cost);
            place = view.findViewById(R.id.place);
            category = view.findViewById(R.id.category);

        }

//        void setBills(newBill bills) {
//            String vendor = bills.getVendor();
//            v_name.setText(vendor);
//            String Date = bills.getDate();
//            date.setText(Date);
//            String amount = bills.getAmount();
//            cost.setText(amount);
//            String jagah = bills.getPlace();
//            place.setText(jagah);
//        }

        public void setTextvendor(String textvendor) {
            v_name.setText(textvendor);
        }

        public void setTextdate(String textdate) {
            date.setText(textdate);
        }

        public void setTextplace(String textplace) {
            place.setText(textplace);
        }

        public void setTextamount(String textamount) {
            cost.setText(textamount);
        }

        public void setTextcategory(String textcategory) { category.setText(textcategory); }
    }
}
