package com.example.email;

public class database_patient {
    String id;
    String aname;
    String aemail;
    String anumber;
    String aaddress;
    String astreet;
    String acity;
    String azip;
    String adate;

    public database_patient(){

    }

    public database_patient(String id, String aname, String aemail, String anumber, String aaddress, String astreet, String acity, String azip, String adate) {
        this.id = id;
        this.aname = aname;
        this.aemail = aemail;
        this.anumber = anumber;
        this.aaddress = aaddress;
        this.astreet = astreet;
        this.acity = acity;
        this.azip = azip;
        this.adate = adate;
    }

    public String getAzip() {
        return azip;
    }

    public String getAdate() {
        return adate;
    }

    public database_patient(String id, String aname, String aemail, String anumber, String aaddress, String astreet, String acity) {
        this.id = id;
        this.aname = aname;
        this.aemail = aemail;
        this.anumber = anumber;
        this.aaddress = aaddress;
        this.astreet = astreet;
        this.acity = acity;
    }

    public String getId() {
        return id;
    }

    public String getAname() {
        return aname;
    }

    public String getAemail() {
        return aemail;
    }

    public String getAnumber() {
        return anumber;
    }

    public String getAaddress() {
        return aaddress;
    }

    public String getAstreet() {
        return astreet;
    }

    public String getAcity() {
        return acity;
    }
}