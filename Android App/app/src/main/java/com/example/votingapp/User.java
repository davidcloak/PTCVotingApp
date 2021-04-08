package com.example.votingapp;

public class User {
    String Id;
    String UserName;
    String NormalizedUserName;
    String Email;
    String NormalizedEmail;
    String EmailConfirmed;
    String PasswordHash;
    String SecurityStamp;
    String ConcurrencyStamp;
    String PhoneNumber;
    String PhoneNumberConfirmed;
    String TwoFactorEnabled;
    String LockoutEnd;
    String LockoutEnabled;
    String AccessFailedCount;
    String deviceID;
    String setStatus;

    public User() {
    }

    public User(String id, String userName, String normalizedUserName, String email, String normalizedEmail,
                String emailConfirmed, String passwordHash, String securityStamp, String concurrencyStamp,
                String phoneNumber, String phoneNumberConfirmed, String twoFactorEnabled, String lockoutEnd,
                String lockoutEnabled, String accessFailedCount, String deviceID, String setStatus) {
        Id = id;
        UserName = userName;
        NormalizedUserName = normalizedUserName;
        Email = email;
        NormalizedEmail = normalizedEmail;
        EmailConfirmed = emailConfirmed;
        PasswordHash = passwordHash;
        SecurityStamp = securityStamp;
        ConcurrencyStamp = concurrencyStamp;
        PhoneNumber = phoneNumber;
        PhoneNumberConfirmed = phoneNumberConfirmed;
        TwoFactorEnabled = twoFactorEnabled;
        LockoutEnd = lockoutEnd;
        LockoutEnabled = lockoutEnabled;
        AccessFailedCount = accessFailedCount;
        this.deviceID = deviceID;
        this.setStatus = setStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNormalizedUserName() {
        return NormalizedUserName;
    }

    public void setNormalizedUserName(String normalizedUserName) {
        NormalizedUserName = normalizedUserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNormalizedEmail() {
        return NormalizedEmail;
    }

    public void setNormalizedEmail(String normalizedEmail) {
        NormalizedEmail = normalizedEmail;
    }

    public String getEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(String emailConfirmed) {
        EmailConfirmed = emailConfirmed;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getSecurityStamp() {
        return SecurityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        SecurityStamp = securityStamp;
    }

    public String getConcurrencyStamp() {
        return ConcurrencyStamp;
    }

    public void setConcurrencyStamp(String concurrencyStamp) {
        ConcurrencyStamp = concurrencyStamp;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPhoneNumberConfirmed() {
        return PhoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(String phoneNumberConfirmed) {
        PhoneNumberConfirmed = phoneNumberConfirmed;
    }

    public String getTwoFactorEnabled() {
        return TwoFactorEnabled;
    }

    public void setTwoFactorEnabled(String twoFactorEnabled) {
        TwoFactorEnabled = twoFactorEnabled;
    }

    public String getLockoutEnd() {
        return LockoutEnd;
    }

    public void setLockoutEnd(String lockoutEnd) {
        LockoutEnd = lockoutEnd;
    }

    public String getLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setLockoutEnabled(String lockoutEnabled) {
        LockoutEnabled = lockoutEnabled;
    }

    public String getAccessFailedCount() {
        return AccessFailedCount;
    }

    public void setAccessFailedCount(String accessFailedCount) {
        AccessFailedCount = accessFailedCount;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getStatus() {
        return setStatus;
    }

    public void setStatus(String setStatus) {
        this.setStatus = setStatus;
    }
}