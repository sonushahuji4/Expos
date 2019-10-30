package com.example.shadrak.expensestracker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class formpopup extends Activity {

    EditText vendor, place, edit_date, amount;
    Button submit;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String vendorname, location, costprice, Category, date;
    Spinner category;
    DatabaseReference rootref, childref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formpopup);

        //spinner
        addListenerOnSpinnerItemSelection();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        vendor = findViewById(R.id.vendorname);
        place = findViewById(R.id.place);
        edit_date = findViewById(R.id.editdate);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submitbutton);

        //firebase stuff
        rootref = FirebaseDatabase.getInstance().getReference();

        // Pick date function
        edit_date.setOnClickListener(new View.OnClickListener(){
            //Log.i("date","onclicklistener");
            @Override
            public void onClick(View view) {
                Log.i("date","onclickview");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(formpopup.this, onDateSetListener,year,month,day);

                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
              Log.i("date","onDateset");
              month = month+1;
              String date = dayOfMonth + "/" + month + "/" + year;
              edit_date.setText(date);

          }
        };

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("button","submit button clicked");
                vendorname = vendor.getText().toString();
                location = place.getText().toString();
                costprice = amount.getText().toString();
                //Category = category.getText().toString();
                customOnItemSelectedListener obj = new customOnItemSelectedListener();
                Category = obj.getValue();
                date = edit_date.getText().toString();
//                Log.d("category", "category is "+Category);
                Toast.makeText(getApplicationContext(),"category is "+Category,Toast.LENGTH_SHORT).show();

                //get userid
                MainActivity activity = new MainActivity();
                String userid = activity.getUid();
                Random r = new Random();
                int billid = r.nextInt(9999 - 1000) + 1000;

                //newBill data = new newBill(costprice, date, location, Category, vendorname);

                //childref = rootref.child("Bills").child(userid).child(String.valueOf(billid));
                //childref = rootref.child("Bills").child(userid);
                childref = rootref.child("Bills");

                Map<String, newBill> bills = new HashMap<>();
                bills.put(String.valueOf(billid), new newBill(costprice, date, location, Category, vendorname));


//                childref.push().setValue(userMap);

                childref.push().setValue(bills);

            }
        });

    }

    private void addListenerOnSpinnerItemSelection() {
        category = (Spinner) findViewById(R.id.category);
        category.setOnItemSelectedListener(new customOnItemSelectedListener());
    }
}
