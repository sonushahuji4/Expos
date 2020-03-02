package com.example.shadrak.expensestracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class recyclerviewAdapter extends RecyclerView.Adapter<recyclerviewAdapter.ViewHolder> {

    bills_fragment context;
    ArrayList<newBill> list;

    public recyclerviewAdapter(bills_fragment bills_fragment, ArrayList<newBill> list) {
        this.context = bills_fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dataitems, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.v_name.setText(list.get(i).getVendor());
        viewHolder.date.setText(list.get(i).getDate());
        viewHolder.category.setText(list.get(i).getCategory());
        viewHolder.cost.setText(list.get(i).getAmount());
        viewHolder.place.setText(list.get(i).getPlace());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView v_name, date, cost, place, category;

        public ViewHolder(View view) {
            super(view);

            v_name = view.findViewById(R.id.vendor_name);
            date = view.findViewById(R.id.date);
            cost = view.findViewById(R.id.cost);
            place = view.findViewById(R.id.place);
            category = view.findViewById(R.id.category);

        }
    }
}
