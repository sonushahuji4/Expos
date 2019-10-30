package com.example.shadrak.expensestracker;

public class User {

    public String Name;
    public String Email;
    public String Userid;

    public User() {

    }

    public User(String uid, String name, String email) {
        this.Name = name;
        this.Email = email;
        this.Userid = uid;
    }

}
