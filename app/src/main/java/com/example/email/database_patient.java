package com.example.email;

public class database_patient {

    String aname;
    String aemail;
    String anumber;
    String adate;
    String adoctor;
    String agender;
    String aslot;

    public database_patient(){

    }

    public database_patient(String aname, String aemail, String anumber, String adate, String adoctor, String agender, String aslot) {
        this.aname = aname;
        this.aemail = aemail;
        this.anumber = anumber;
        this.adate = adate;
        this.adoctor = adoctor;
        this.agender = agender;
        this.aslot = aslot;
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

    public String getAdoctor() {
        return adoctor;
    }

    public String getAgender() {
        return agender;
    }

    public String getAslot() {
        return aslot;
    }
}
