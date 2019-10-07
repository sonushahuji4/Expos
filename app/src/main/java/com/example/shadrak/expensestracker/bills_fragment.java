package com.example.shadrak.expensestracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class bills_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    recyclerviewAdapter adapter;

    ArrayList<Bills> list;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearlist();
        View rootview = inflater.inflate(R.layout.bills_fragment, container, false);

        //Recyclerview
        recyclerView = rootview.findViewById(R.id.bills_recyclerview);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new recyclerviewAdapter(getActivity(),this.list);
        recyclerView.setAdapter(adapter);

        return rootview;
    }

    private void linearlist() {

        list = new ArrayList<Bills>();
        list.add(new Bills("BigBazar", "10/07/07", 200));
        list.add(new Bills("Indian Oil", "23/06/07", 500));
        list.add(new Bills("Sai Leela", "10/12/18", 1200));
        list.add(new Bills("PVR Cinemas", "18/10/12", 1600));
        list.add(new Bills("Airtel", "05/04/16", 2000));
        list.add(new Bills("Nandini Tours & Travels", "16/07/19", 10000));
    }
}
