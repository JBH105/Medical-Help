package com.example.email;

public class loginData {
    String Id;
    String Email;
    String User_Type;
    String Name;
    String Number;
    String buildno;
    String areano;
    String lanno;
    String cityno;

    public loginData(){

    }

    public loginData(String id, String email, String user_Type, String name, String number, String buildno, String areano, String lanno, String cityno) {
        Id = id;
        Email = email;
        User_Type = user_Type;
        Name = name;
        Number = number;
        this.buildno = buildno;
        this.areano = areano;
        this.lanno = lanno;
        this.cityno = cityno;
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

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }

    public String getBuildno() {
        return buildno;
    }

    public String getAreano() {
        return areano;
    }

    public String getLanno() {
        return lanno;
    }

    public String getCityno() {
        return cityno;
    }
}
