package com.example.shadrak.expensestracker;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class bills_fragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    recyclerviewAdapter adapter;         //recyclerviewAdapter class
    DatabaseReference rootref;         //firebase connections
//    private FirebaseRecyclerAdapter<newBill, MyViewHolder> firebaseRecyclerAdapter;        //firebase connections
    ArrayList<newBill> list;
    String amount, date, place, vendor, category, status, link, items;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;
    ImageView verified, icon;
    LinearLayout bills_fragment;

    private Drawable dlt_icon;
    private ColorDrawable background;

    FloatingActionButton fab;


    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.bills_fragment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        list = new ArrayList<>();

        verified = (ImageView) rootview.findViewById(R.id.verified);
        icon = (ImageView) rootview.findViewById(R.id.bill_icon);

//        icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(),"icon link",Toast.LENGTH_SHORT).show();
//            }
//        });

        //Recyclerview
        recyclerView = rootview.findViewById(R.id.bills_recyclerview);
        bills_fragment = rootview.findViewById(R.id.bills_fragment);
//        recyclerView.hasFixedSize();

        populate_list(userId);

        rootref = FirebaseDatabase.getInstance().getReference();
        Query query = rootref.child("Bills").child(userId);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new recyclerviewAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "clicked",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), formpopup.class);
//                i.putExtra("uid",uid);
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getContext(), "lamba clicked",Toast.LENGTH_SHORT).show();
            }
        }));

//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                final int fromPos = viewHolder.getAdapterPosition();
//                final int toPos = target.getAdapterPosition();
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//                Toast.makeText(getActivity(),"Swiped",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                // view the background view
//            }
//        };
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);





        return rootview;
    }

    private void populate_list(String userId) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        rootref = database.getReference().child("Bills").child(userId);



        rootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    amount = data.child("amount").getValue().toString();
                    date = data.child("date").getValue().toString();
                    place = data.child("place").getValue().toString();
                    vendor = data.child("vendor").getValue().toString();
                    category = data.child("category").getValue().toString();
                    status = data.child("status").getValue().toString();
                    link = data.child("link").getValue().toString();
                    items = data.child("items").getValue().toString();

                    Log.d("values","values "+ amount + " " + date + " " + place + " " + vendor + " " + category + " " + status + " " + link + " " +items);

                    list.add(new newBill(amount, date, place, vendor, category, status, link, items));

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof recyclerviewAdapter.ViewHolder) {
            // get the removed item name to display it in snack bar
//            String name = list.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final newBill deletedItem = list.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            recyclerviewAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(bills_fragment, " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    recyclerviewAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
