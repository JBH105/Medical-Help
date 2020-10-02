package com.example.email;

public class doctor_appointment_data {

    String id;
    String Name;
    String Email;
    String Number;
    String Address;
    String city;
    String Zipcode;
    String Date;

    public  doctor_appointment_data(){

    }

    public doctor_appointment_data(String s, String id, String name, String email, String number, String address, String city, String zipcode, String date) {
        this.id = id;
        Name = name;
        Email = email;
        Number = number;
        Address = address;
        this.city = city;
        Zipcode = zipcode;
        Date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getNumber() {
        return Number;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public String getDate() {
        return Date;
    }
}
