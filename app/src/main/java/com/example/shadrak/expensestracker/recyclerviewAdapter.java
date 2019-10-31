package com.example.shadrak.expensestracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class recyclerviewAdapter extends RecyclerView.Adapter<recyclerviewAdapter.MyViewHolder> {

    Context mContent;
    List<Bills> mlist;
    //LinearLayout l;



    public recyclerviewAdapter(Context mContent, List<Bills> mlist) {
        this.mContent = mContent;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public recyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v = LayoutInflater.from(mContent).inflate(R.layout.dataitems,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull recyclerviewAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.v_name.setText(mlist.get(i).getVendor());
        myViewHolder.date.setText(mlist.get(i).getDate());
        myViewHolder.cost.setText(mlist.get(i).getAmount());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView v_name, date, cost;

        public MyViewHolder(View view) {
            super(view);

            v_name = view.findViewById(R.id.vendor_name);
            date = view.findViewById(R.id.date);
            cost = view.findViewById(R.id.cost);

        }

//        void setBills(Bills bills) {
//            String vendor = bills.getVendor();
//            v_name.setText(vendor);
//            String Date = bills.getDate();
//            date.setText(Date);
//            String amount = bills.getAmount();
//            cost.setText(amount);
//        }

    }
}
