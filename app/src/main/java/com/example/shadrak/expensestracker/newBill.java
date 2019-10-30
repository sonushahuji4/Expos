package com.example.shadrak.expensestracker;

public class newBill {

    public String Amount;
    public String Date;
    public String Place;
    public String Category;
    public String Vendor;

    public newBill() {

    }

    public newBill(String amount, String date, String place, String category, String vendor) {
        this.Amount = amount;
        this.Date = date;
        this.Place = place;
        this.Vendor = vendor;
        this.Category = category;
    }
}
