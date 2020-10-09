package com.example.email;

public class loginData {
    String Id;
    String Email;
    String User_Type;
    String Name;
    String Number;

    public loginData(){

    }

    public loginData(String id, String email, String user_Type, String name, String number) {
        Id = id;
        Email = email;
        User_Type = user_Type;
        Name = name;
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }

    public String getId() {
        return Id;
    }

    public String getEmail() {
        return Email;
    }

    public String getUser_Type() {
        return User_Type;
    }
}
