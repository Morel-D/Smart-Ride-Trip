package com.example.smarttripride__login;

public class ModuleClass {
    String userName, Email, PhoneNumber;

    ModuleClass(){

    }

    public ModuleClass(String userName, String email, String phoneNumber) {
        this.userName = userName;
        Email = email;
        PhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
