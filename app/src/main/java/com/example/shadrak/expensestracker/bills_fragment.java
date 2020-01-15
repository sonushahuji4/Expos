package com.example.shadrak.expensestracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class bills_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    recyclerviewAdapter adapter;         //recyclerviewAdapter class
    DatabaseReference rootref;         //firebase connections
    private FirebaseRecyclerAdapter<Bills,MyViewHolder> firebaseRecyclerAdapter;        //firebase connections
    ArrayList<Bills> list;
    String uid;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        linearlist();
        View rootview = inflater.inflate(R.layout.bills_fragment, container, false);

        //Recyclerview
        recyclerView = rootview.findViewById(R.id.bills_recyclerview);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

//        Bundle bundle = new Bundle();
//        uid = bundle.getString("userid");

//        adapter = new recyclerviewAdapter(getActivity(),this.list);
//        recyclerView.setAdapter(adapter);

//        Log.d("user",uid);
        rootref = FirebaseDatabase.getInstance().getReference();
        Query query = rootref.child("Bills");
        FirebaseRecyclerOptions<Bills> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Bills>()
                .setQuery(query, Bills.class)
                .build();


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Bills, MyViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Bills model) {
                holder.setBills(model);
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dataitems, viewGroup, false);
                return new MyViewHolder(view);
            }
        };


        return rootview;
    }

//    private void linearlist() {
//
//        list = new ArrayList<Bills>();
//        list.add(new Bills("Hanumant","09/08/19","811"));
//        list.add(new Bills("BigBazar", "10/07/07", "200"));
//        list.add(new Bills("Indian Oil", "23/06/07", "500"));
//        list.add(new Bills("Sai Leela", "10/12/18", "1200"));
//        list.add(new Bills("PVR Cinemas", "18/10/12", "1600"));
//        list.add(new Bills("Airtel", "05/04/16", "2000"));
//        list.add(new Bills("Nandini Tours & Travels", "16/07/19", "10000"));
//        list.add(new Bills("Hanumant","09/08/19","811"));
//        list.add(new Bills("BigBazar", "10/07/07", "200"));
//        list.add(new Bills("Indian Oil", "23/06/07", "500"));
//        list.add(new Bills("Sai Leela", "10/12/18", "1200"));
//        list.add(new Bills("PVR Cinemas", "18/10/12", "1600"));
//        list.add(new Bills("Airtel", "05/04/16", "2000"));
//        list.add(new Bills("Nandini Tours & Travels", "16/07/19", "10000"));
//        list.add(new Bills("Hanumant","09/08/19","811"));
//        list.add(new Bills("BigBazar", "10/07/07", "200"));
//        list.add(new Bills("Indian Oil", "23/06/07", "500"));
//        list.add(new Bills("Sai Leela", "10/12/18", "1200"));
//        list.add(new Bills("PVR Cinemas", "18/10/12", "1600"));
//        list.add(new Bills("Airtel", "05/04/16", "2000"));
//        list.add(new Bills("Nandini Tours & Travels", "16/07/19", "10000"));
//
//    }

    @Override
    public void onStart(){
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView v_name, date, cost;

        public MyViewHolder(View view) {
            super(view);

            v_name = view.findViewById(R.id.vendor_name);
            date = view.findViewById(R.id.date);
            cost = view.findViewById(R.id.cost);

        }

        void setBills(Bills bills) {
            String vendor = bills.getVendor();
            v_name.setText(vendor);
            String Date = bills.getDate();
            date.setText(Date);
            String amount = bills.getAmount();
            cost.setText(amount);
        }

    }
}
