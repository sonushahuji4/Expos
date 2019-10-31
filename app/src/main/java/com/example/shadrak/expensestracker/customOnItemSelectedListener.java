package com.example.shadrak.expensestracker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class customOnItemSelectedListener implements OnItemSelectedListener {

    public String value;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        value = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public String getValue(){
        return value;
    }
}
