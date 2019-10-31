package com.example.shadrak.expensestracker;

public class Bills {

    public String vendor;
    public String date;
    public String amount;

    public Bills() {

    }

    public Bills(String vendor, String date, String amount) {
        this.vendor = vendor;
        this.date = date;
        this.amount = amount;
    }

    //Getter

    public String getVendor() {
        return vendor;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    //Setter

    public void setVendor(String name) {
        vendor = name;
    }

    public void setDate(String Date) {
        date = Date;
    }

    public void setAmount(String cost) {
        amount = cost;
    }
}
