package com.example.email;

public class database_patient {

    String aname;
    String aemail;
    String anumber;
    String adate;

    public database_patient(){

    }

    public database_patient(String aname, String aemail, String anumber, String adate) {
        this.aname = aname;
        this.aemail = aemail;
        this.anumber = anumber;
        this.adate = adate;
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

    public String getAdate() {
        return adate;
    }
}
