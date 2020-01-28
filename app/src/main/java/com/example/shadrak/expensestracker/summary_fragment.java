package com.example.shadrak.expensestracker;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class summary_fragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    String userId;
    DatabaseReference rootref, childref;

    PieChart pieChart;

    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.summary_fragment,container,false);

        firebaseAuth = FirebaseAuth.getInstance();
        User = firebaseAuth.getCurrentUser();
        userId = User.getUid();

        pieChart = (PieChart) rootview.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(51f);


        rootref = FirebaseDatabase.getInstance().getReference();
        childref = rootref.child("Bills").child(userId);
        final ValueEventListener billsDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot billSnapshot = dataSnapshot.child("Bills").child(userId);
                Iterable<DataSnapshot> billchildren = billSnapshot.getChildren();
                ArrayList<PieEntry> values = new ArrayList<>();
                for (DataSnapshot bill : billchildren) {
                    newBill bills = bill.getValue(newBill.class);
                    values.add(new PieEntry(bills.getAmount(), bills.getCategory()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }

        ArrayList<PieEntry> values = new ArrayList<>();

        values.add(new PieEntry(34f, "Shadrak"));
        values.add(new PieEntry(23f, "hatt"));
        values.add(new PieEntry(14f, "jaa be"));
        values.add(new PieEntry(35f, "Chaitaniya"));
        values.add(new PieEntry(40f, "lol"));
        values.add(new PieEntry(23, "Shubham"));

        Description description = new Description();
        description.setText("This is PieChart");
        description.setTextSize(15);
//        description.setTextAlign(Paint.Align.CENTER);
        pieChart.setDescription(description);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet pieDataSet = new PieDataSet(values, "Names");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);


        return rootview;
    }
}
