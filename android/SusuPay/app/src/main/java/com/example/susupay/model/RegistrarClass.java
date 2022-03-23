package com.example.susupay.model;

public class RegistrarClass {
    public String id, firstname, lastname, password;
    public String phoneNum, NationalID, RegistrationEmail;

    public RegistrarClass(String Firstname, String Lastname, String nationalID, String PhoneNum ) {
        this.firstname= Firstname;
        this.lastname = Lastname;
        this.NationalID = nationalID;
        this.phoneNum = PhoneNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }

    public String getRegistrationEmail() {
        return RegistrationEmail;
    }

    public void setRegistrationEmail(String registrationEmail) {
        RegistrationEmail = registrationEmail;
    }
}
