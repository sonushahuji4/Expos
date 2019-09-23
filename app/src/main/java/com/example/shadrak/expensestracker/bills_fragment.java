package com.example.shadrak.expensestracker;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ListView;

import java.util.ArrayList;

public class bills_fragment extends ListFragment{

    ArrayList<String> bills = new ArrayList<>();
//    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
       // listView = view.findViewById(R.id.list1);

        bills.add("1001");
        bills.add("1002");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,bills);
        setListAdapter(adapter);
        //return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}
