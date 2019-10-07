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

import java.util.Calendar;

public class formpopup extends Activity {

    EditText vendor, place, edit_date, amount;
    Button submit;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    String vendorname, location, costprice, Category;
    Spinner category;

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
//        category = findViewById(R.id.category);
        submit = findViewById(R.id.submitbutton);

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
                Log.d("category", "category is "+Category);
            }
        });

    }

    private void addListenerOnSpinnerItemSelection() {
        category = (Spinner) findViewById(R.id.category);
        category.setOnItemSelectedListener(new customOnItemSelectedListener());
    }
}
