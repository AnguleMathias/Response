package com.mathias.com.quickresponse.Auth;

import com.google.firebase.database.Exclude;

public class user {
    private String UserName;
    private String Pin;
    private String Email;
    private String PhoneNumber;
    private String mKey;
    public user() {
        //empty constructor needed
    }

    public user(String userName, String pin, String email, String phoneNumber) {
        UserName = userName;
        Pin = pin;
        Email = email;
        PhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
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

