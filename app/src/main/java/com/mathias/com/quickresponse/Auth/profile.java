package com.mathias.com.quickresponse.Auth;


import com.google.firebase.database.Exclude;

public class profile {
   private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String Address;
    private String EmemgencyName;
    private String EmengencyNumber;
    private String IdType;
    private String IdNumber;
    private String mKey;


    public profile(String trim, String s, String trim1, String s1) {
        //empty constructor needed
    }

    public profile(String firstName, String lastName, String phoneNumber, String address, String ememgencyName, String emengencyNumber, String idType, String idNumber, String mKey) {
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        Address = address;
        EmemgencyName = ememgencyName;
        EmengencyNumber = emengencyNumber;
        IdType = idType;
        IdNumber = idNumber;
        this.mKey = mKey;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmemgencyName() {
        return EmemgencyName;
    }

    public void setEmemgencyName(String ememgencyName) {
        EmemgencyName = ememgencyName;
    }

    public String getEmengencyNumber() {
        return EmengencyNumber;
    }

    public void setEmengencyNumber(String emengencyNumber) {
        EmengencyNumber = emengencyNumber;
    }

    public String getIdType() {
        return IdType;
    }

    public void setIdType(String idType) {
        IdType = idType;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }



    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}