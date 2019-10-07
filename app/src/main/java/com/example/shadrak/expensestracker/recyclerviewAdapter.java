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

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        viewHolder.v_name.setText(mlist.get(i).getVendor());
//        RecyclerView.ViewHolder.date.setText(mlist.get(i).getDate());
//        RecyclerView.ViewHolder.cost.setText(mlist.get(i).getAmount());
//
//      //  ((MyViewHolder)viewHolder).bind(mlist.get(i));
//    }

    @Override
    public void onBindViewHolder(@NonNull recyclerviewAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.v_name.setText(mlist.get(i).getVendor());
        myViewHolder.date.setText(mlist.get(i).getDate());
        myViewHolder.cost.setText(Integer.toString(mlist.get(i).getAmount()));

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

//        public MyViewHolder(LinearLayout l) {
//            super(l);
//            v_name = (TextView) l.findViewById(R.id.vendor_name);
//            date = (TextView) l.findViewById(R.id.date);
//            cost =  (TextView) l.findViewById(R.id.cost);
//        }
//
//        public bind(Bills bills){
//            v_name.setText(bills.getVendor());
//            date.setText(bills.getDate());
//            cost.setText(bills.getAmount());
//        }
    }
}
