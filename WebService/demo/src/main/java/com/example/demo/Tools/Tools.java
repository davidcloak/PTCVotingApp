package com.example.demo.Tools;

import org.apache.tomcat.util.codec.binary.Base64;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
