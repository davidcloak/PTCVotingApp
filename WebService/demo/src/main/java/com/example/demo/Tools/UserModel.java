package com.example.demo.Tools;

public class UserModel {
    String UserName;
    String Password;
    String AccessLevel;

    public UserModel() {
    }

    public UserModel(String userName, String password, String accessLevel) {
        UserName = userName;
        Password = password;
        AccessLevel = accessLevel;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        AccessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return UserName+" Access Level: "+AccessLevel;
    }

    
}
