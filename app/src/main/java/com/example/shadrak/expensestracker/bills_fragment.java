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
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class bills_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    recyclerviewAdapter adapter;         //recyclerviewAdapter class
    DatabaseReference rootref;         //firebase connections
    private FirebaseRecyclerAdapter<newBill,MyViewHolder> firebaseRecyclerAdapter;        //firebase connections
    ArrayList<newBill> list;
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
//        FirebaseRecyclerOptions<newBill> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<newBill>()
//                .setQuery(query, newBill.class)
//                .build();

        FirebaseRecyclerOptions<newBill> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<newBill>()
                .setQuery(query, new SnapshotParser<newBill>() {
                    @NonNull
                    @Override
                    public newBill parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new newBill(snapshot.child("amount").getValue().toString(),
                                snapshot.child("date").getValue().toString(),
                                snapshot.child("place").getValue().toString(),
                                snapshot.child("vendor").getValue().toString());
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

        private TextView v_name, date, cost, place;

        public MyViewHolder(View view) {
            super(view);

            v_name = view.findViewById(R.id.vendor_name);
            date = view.findViewById(R.id.date);
            cost = view.findViewById(R.id.cost);
            place = view.findViewById(R.id.place);
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
    }
}
