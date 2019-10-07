package com.example.shadrak.expensestracker;

public class Bills {

    private String vendor;
    private String date;
    private int amount;

    public Bills() {

    }

    public Bills(String vendor, String date, int amount) {
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

    public int getAmount() {
        return amount;
    }

    //Setter

    public void setVendor(String name) {
        vendor = name;
    }

    public void setDate(String Date) {
        date = Date;
    }

    public void setAmount(int cost) {
        amount = cost;
    }
}
