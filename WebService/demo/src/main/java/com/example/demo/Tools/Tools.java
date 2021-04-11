package com.example.demo.Tools;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Tools {
    public String connection = "jdbc:sqlserver://voteshield.database.windows.net;databaseName=voteShield;user=Nate;password=Ghost123";


    private String MD5Converter(String password){
        MessageDigest md;
        String myHash = "";
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            myHash = number.toString(16);
            while(myHash.length() < 32){
                myHash = "0"+myHash;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return myHash;
    }

    private UserModel DBConnection(){
        UserModel user = new UserModel();
        user.UserName = "ShhhImASecret";
        user.Password = MD5Converter("ShhhImABiggerSecret123@");
        user.AccessLevel = "Good";
        return user;
    }

    public UserModel isUser(String auth){
        String userPass = auth.substring(6);
		byte[] decryptArray = Base64.decodeBase64(userPass);
        String decryptString = new String(decryptArray);
        int colon = decryptString.indexOf(":");
        String authUserName = decryptString.substring(0, colon);
        String authPassword = decryptString.substring(colon + 1);
        
        authPassword = MD5Converter(authPassword);
        UserModel user = DBConnection();

        if(authUserName.equals(user.UserName) && authPassword.equals(user.Password)){
            user.setPassword("");
            return user;
        }else{
            user.setUserName(authUserName);
            user.setAccessLevel("forbidden");
            return user;
        }
    }

    
    private int SaltByteSize = 24;
    private int HashByteSize = 24;
    private int HasingIterationsCount = 10101;
    public String Password(String password) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] salt;
        byte[] buffer2;
        if (password == null)
            throw new IllegalArgumentException("password");
        Rfc2898DeriveBytes bytes = new Rfc2898DeriveBytes(password, new byte[SaltByteSize], HasingIterationsCount);
        salt = bytes.getSalt();
        buffer2 = bytes.getBytes(HashByteSize);
        byte[] dst = new byte[(SaltByteSize + HashByteSize) + 1];
        System.arraycopy(salt, 0, dst, 1, SaltByteSize);
        System.arraycopy(buffer2, 0, dst, SaltByteSize+1, HashByteSize);
        String test = Base64.encodeBase64String(dst);
        System.out.println(Base64.decodeBase64(test));
        return Base64.encodeBase64String(dst);
    }

    public boolean verifyHashedPassword(String hashedPassword, String password) {
        byte[] buffer4;
        if (hashedPassword == null)
            return false;
        if (password == null)
            throw new IllegalArgumentException("password");
        
        try {
            byte[] src = Base64.decodeBase64(hashedPassword);
            if ((src.length != (SaltByteSize + HashByteSize) + 1) || (src[0] != 0))
            return false;
            byte[] dst = new byte[SaltByteSize];
            System.arraycopy(src, 1, dst, 0, SaltByteSize);
            byte[] buffer3 = new byte[HashByteSize];
            System.arraycopy(src, SaltByteSize+1, buffer3, 0, HashByteSize);
            Rfc2898DeriveBytes bytes;
            bytes = new Rfc2898DeriveBytes(password,dst,HasingIterationsCount);
            buffer4 = bytes.getBytes(HashByteSize);
            return Arrays.equals(buffer3, buffer4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
