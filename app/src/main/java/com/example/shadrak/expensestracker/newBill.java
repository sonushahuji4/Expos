package com.example.shadrak.expensestracker;

public class newBill {

    private String Amount;
    private String Date;
    private String Place;
    private String Category;
    private String Vendor;

    public newBill() {

    }

    public newBill(String amount, String date, String place, String vendor, String category) {
        this.Amount = amount;
        this.Date = date;
        this.Place = place;
        this.Vendor = vendor;
        this.Category = category;
    }

    //Getter

    public String getVendor() {
        return Vendor;
    }

    public String getDate() {
        return Date;
    }

    public String getAmount() {
        return Amount;
    }

    public String getPlace() {
        return Place;
    }

    public String getCategory() { return Category; }

    //Setter

    public void setVendor(String name) {
        Vendor = name;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setAmount(String cost) {
        Amount = cost;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public void setCategory(String category) { Category = category; }
}
