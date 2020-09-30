package com.example.email;

public class loginData {
    String id;
    String Email;
    String User_Type;

    public loginData(){

    }

    public loginData(String id, String email, String user_Type) {
        this.id = id;
        Email = email;
        User_Type = user_Type;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getUser_Type() {
        return User_Type;
    }
}
