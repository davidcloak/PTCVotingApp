package com.example.demo.User;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Tools.Tools;
import com.example.demo.Voter.Races;
import com.example.demo.Voter.VoteCount;
import com.github.cnlinjie.infrastructure.util.security.Rfc2898DeriveBytes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import org.apache.tomcat.util.codec.binary.Base64;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

@RestController
public class UserController {
    Tools t = new Tools();
    private String connectionString = t.connection;


    private int SaltByteSize = 24;
    private int HashByteSize = 24;
    private int HasingIterationsCount = 10101;
    // @RequestMapping(value = "/password", method = RequestMethod.GET)
    // public String PasswordTest(@RequestParam(defaultValue = "*") String password) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
    //     byte[] salt;
    //     byte[] buffer2;
    //     if (password == null)
    //         throw new IllegalArgumentException("password");
    //     Rfc2898DeriveBytes bytes = new Rfc2898DeriveBytes(password, new byte[SaltByteSize], HasingIterationsCount);
    //     salt = bytes.getSalt();
    //     buffer2 = bytes.getBytes(HashByteSize);
    //     byte[] dst = new byte[(SaltByteSize + HashByteSize) + 1];
    //     System.arraycopy(salt, 0, dst, 1, SaltByteSize);
    //     System.arraycopy(buffer2, 0, dst, SaltByteSize+1, HashByteSize);
    //     return Base64.encodeBase64String(dst);
    // }

    // @RequestMapping(value = "/passwordH", method = RequestMethod.GET)
    // public boolean verifyHashedPassword(String hashedPassword, String password) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
    //     byte[] buffer4;
    //     if (hashedPassword == null)
    //         return false;
    //     if (password == null)
    //         throw new IllegalArgumentException("password");
    //     byte[] src = Base64.decodeBase64(hashedPassword);
    //     if ((src.length != (SaltByteSize + HashByteSize) + 1) || (src[0] != 0))
    //        return false;
    //     byte[] dst = new byte[SaltByteSize];
    //     System.arraycopy(src, 1, dst, 0, 0x10);
    //     byte[] buffer3 = new byte[HashByteSize];
    //     System.arraycopy(src, SaltByteSize+1, buffer3, 0, HashByteSize);
    //     Rfc2898DeriveBytes bytes = new Rfc2898DeriveBytes(password,dst,HasingIterationsCount);
    //     buffer4 = bytes.getBytes(HashByteSize);
    //     return Arrays.equals(buffer3, buffer4);
    // }

    @RequestMapping(value = "/getVotedRaces", method = RequestMethod.GET)
    public List<String> getVotedRaces() {
        List<String> results = new ArrayList<String>();
        String SQL = String.format("select fk_race from voting group by fk_race");
        try {
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(SQL);
            while(result.next()){
                results.add(result.getString("fk_race"));
            }
        }catch (SQLException e) {
        }
        return results;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public List<User> GetUser(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String email) {
        if(t.isUser(auth).getAccessLevel().equals("Good")){
            List<User> response = new ArrayList<User>();

            User aUser = new User();
            String SQL = "";

            if (email.equals("*")) {
                // fail
                aUser.setId(null);
                aUser.setUserName("No email sent");
                aUser.setStatus("404");
                response.add(aUser);
            } else {
                SQL = String.format("select * from AspNetUsers where Email = '%s'", email);

                try {
                    Connection con = DriverManager.getConnection(connectionString);
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(SQL);
                    if (result.next()) {
                        User v = new User();
                        v.setId(result.getString("Id"));
                        v.setUserName(result.getString("UserName"));
                        v.setNormalizedUserName(result.getString("NormalizedUserName"));
                        v.setEmail(result.getString("Email"));
                        v.setNormalizedEmail(result.getString("NormalizedEmail"));
                        v.setEmailConfirmed(result.getString("EmailConfirmed"));
                        v.setPasswordHash(result.getString("PasswordHash"));
                        v.setSecurityStamp(result.getString("SecurityStamp"));
                        v.setConcurrencyStamp(result.getString("ConcurrencyStamp"));
                        v.setPhoneNumber(result.getString("PhoneNumber"));
                        v.setPhoneNumberConfirmed(result.getString("PhoneNumberConfirmed"));
                        v.setTwoFactorEnabled(result.getString("TwoFactorEnabled"));
                        v.setLockoutEnd(result.getString("LockoutEnd"));
                        v.setLockoutEnabled(result.getString("LockoutEnabled"));
                        v.setAccessFailedCount(result.getString("AccessFailedCount"));
                        v.setDeviceID(result.getString("deviceID"));
                        v.setStatus("200");
                        response.add(v);
                    }
                    else{
                        aUser.setId(null);
                        aUser.setUserName("No User To Be found");
                        aUser.setStatus("404");
                        response.add(aUser);
                    }
                    con.close();
                } catch (SQLException e) {
                    aUser.setId(e.getMessage());
                    aUser.setUserName(SQL);
                    aUser.setStatus("500");
                    response.add(aUser);
                }
            }
            return response;
        }else{
            return new ArrayList<User>();
        }
    }

    private String getVotingID(String race, String State, String city){
        String SQL = String.format("select voting from voting where fk_race = '%s' and locationID = '%s'", race, getLocationID(State, city));
            
        try {
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(SQL);
            if (result.next()) {
                return result.getInt("voting")+"";
            }
            con.close();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "0";
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public String Vote(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String pol, @RequestParam(defaultValue = "*") String userID, @RequestParam(defaultValue = "*") String race, @RequestParam(defaultValue = "*") String state, @RequestParam(defaultValue = "*") String city) {
        if(t.isUser(auth).getAccessLevel().equals("Good")){
            String SQL = String.format("select id from UserVotes where userID = '%s' and votingID = '%s'", userID, getVotingID(race, state, city));

            try {
                Connection con = DriverManager.getConnection(connectionString);
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(SQL);
                if (result.next()) {
                    return "already voted";
                }else{
                    String SQL3 = String.format("insert into voting values ('%s', '%s', '%s')", pol, race, getLocationID(state, city));
                    Connection con3;
                    con3 = DriverManager.getConnection(connectionString);
                    Statement stmt3 = con.createStatement();
                    stmt3.executeUpdate(SQL3);
                    con3.close();

                    String SQL2 = String.format("insert into UserVotes values ('%s', '%s')", userID, getVotingID(race, state, city));
                    Connection con2;
                    con2 = DriverManager.getConnection(connectionString);
                    Statement stmt2 = con.createStatement();
                    stmt2.executeUpdate(SQL2);
                    con2.close();
                }
                con.close();
            } catch (SQLException e) {
                return e.getMessage();
            }
            return "voted";
        }else{
            return "Failed Auth";
        }
    }

    //Add way to get number of votes and who recieved those votes
     // - Make Java Class to hold Name and number of votes and what race it was in
     @RequestMapping(value = "/getVoteCount", method = RequestMethod.GET)
     public List<VoteCount> GetVoteCount(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String race, @RequestParam(defaultValue = "*") String state, @RequestParam(defaultValue = "*") String city){
         if(t.isUser(auth).getAccessLevel().equals("Good")){
            List<VoteCount> response = new ArrayList<VoteCount>();
            VoteCount aVoteCount = new VoteCount();
            String SQL = "";
    
            if (race.equals("*")) {
                // fail
            } else {
                SQL = String.format("select count(Canidate) as Votes, Canidate, fk_race as Race from voting where fk_race = '%s' and locationID = '%s' group by Canidate, fk_race order by Votes desc", race, getLocationID(state, city));
    
                try {
                    Connection con = DriverManager.getConnection(connectionString);
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(SQL);
                    while (result.next()) {
                        VoteCount v = new VoteCount();
                        v.setCanidate(result.getString("Canidate"));
                        v.setRace(result.getString("Race"));
                        v.setVotes(result.getString("Votes"));
                        response.add(v);
                    }
                    con.close();
                } catch (SQLException e) {
                    aVoteCount.setCanidate(e.getMessage());
                    response.add(aVoteCount);
                }
            }
            return response;
        }else{
            return new ArrayList<VoteCount>();
        }
     }

     @RequestMapping(value = "/setLocation", method = RequestMethod.GET)
     public String setLocation(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String state, @RequestParam(defaultValue = "*") String city){
        String SQL = String.format("insert into Location values ('%s', '%s')", state, city);

        try{
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(SQL);

            if(result.next()){
                String id = result.getInt("locationID") +"";
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return getLocationID(state, city);
    }

     @RequestMapping(value = "/getLocation", method = RequestMethod.GET)
     public String getLocation(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String state, @RequestParam(defaultValue = "*") String city){
        String id = "null";
        String SQL = String.format("select * from location where state = '%s' and city = '%s'", state, city);

        try{
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(SQL);

            if(result.next()){
                id = result.getInt("locationID") +"";
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return id;
    }
    
    public String getLocationID(String state, String city){
        String id = "null";
        String SQL = String.format("select * from location where state = '%s' and city = '%s'", state, city);

        try{
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(SQL);

            if(result.next()){
                id = result.getInt("locationID") +"";
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return id;
    }
 
    @RequestMapping(value = "/deleteRace", method = RequestMethod.GET)
    public String delteRace(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String state, @RequestParam(defaultValue = "*") String city, @RequestParam(defaultValue = "*") String name){
        if(t.isUser(auth).getAccessLevel().equals("Good")){
            String SQL = String.format("delete from race where locationID = '%s' and race = '%s'", getLocationID(state, city), name);
            Connection con;
            try {
                con = DriverManager.getConnection(connectionString);
                Statement stmt = con.createStatement();
                stmt.executeUpdate(SQL);
                return "success";
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return "failed";
        }else{
            return "Not Authed";
        }
    }

    @RequestMapping(value = "/getSRace", method = RequestMethod.GET)
    public List<Races> GetRace(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String state, @RequestParam(defaultValue = "*") String city, @RequestParam(defaultValue = "*") String name){
        if(t.isUser(auth).getAccessLevel().equals("Good")){
            List<Races> response = new ArrayList<Races>();
            Races aVoteCount = new Races();
            String SQL = String.format("select race, locationID from race where locationID = '%s' and race = '%s' group by race, locationID", getLocationID(state, city), name);


            try {
                Connection con = DriverManager.getConnection(connectionString);
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(SQL);

                while (result.next()) {
                    String race = result.getString("Race");
                    String SQL2 = String.format("select * from race join Politcal on race.fk_politcal = Politcal.politcal where race = '%s'", race);

                    Connection con2 = DriverManager.getConnection(connectionString);
                    Statement stmt2 = con2.createStatement();
                    ResultSet result2 = stmt2.executeQuery(SQL2);

                    List<Pol> runners = new ArrayList<Pol>();
                    while(result2.next()){
                        Pol pol = new Pol();
                        pol.setName(result2.getString("Name"));
                        pol.setPoliticalParty(result2.getString("politicalParty"));
                        pol.setDescription(result2.getString("description"));
                        pol.setImageURL(result2.getString("imageURL"));
                        runners.add(pol);
                    }
                    
                    Races v = new Races();

                    String SQL3 = String.format("select * from Location where locationID = '%s'", result.getInt("locationID")+"");

                    Connection con3 = DriverManager.getConnection(connectionString);
                    Statement stmt3 = con3.createStatement();
                    ResultSet result3 = stmt3.executeQuery(SQL3);
                    if(result3.next()){
                        v.setState(result3.getString("state"));
                        v.setCity(result3.getString("city"));
                    }else{
                        v.setState("Not Added Yet");
                        v.setCity("Note Added Yet");
                    }
                    

                    v.setRunners(runners);
                    v.setRace(race);
                    response.add(v);
                }
                con.close();
            } catch (SQLException e) {
                aVoteCount.setRace(e.getMessage());
                response.add(aVoteCount);
            }
            
            return response;
        }else{
            return new ArrayList<Races>();
        }
    }

    @RequestMapping(value = "/clearPol", method = RequestMethod.GET)
    public String clearExtras(@RequestHeader("Authorization") String auth){
        if(t.isUser(auth).getAccessLevel().equals("Good")){
            try {
                String SQL = "delete from Politcal where politcal in (select Politcal from Politcal where politcal not in (select Min(Politcal) from Politcal group by name))";
                Connection con = DriverManager.getConnection(connectionString);
                Statement stmt = con.createStatement();
                stmt.executeUpdate(SQL);
            }catch(Exception e){
                return e.getMessage();
            }
            return "success";
        }
        return "fail";
    }
      //Races list of people in the race
    @RequestMapping(value = "/getRaces", method = RequestMethod.GET)
    public List<Races> GetRaces(@RequestHeader("Authorization") String auth){
        if(t.isUser(auth).getAccessLevel().equals("Good")){
            List<Races> response = new ArrayList<Races>();
            Races aVoteCount = new Races();
            String SQL = "select race, locationID from race group by race, locationID";

            try {
                Connection con = DriverManager.getConnection(connectionString);
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(SQL);

                while (result.next()) {
                    String race = result.getString("Race");
                    String location = result.getInt("locationID")+"";
                    String SQL2 = String.format("select * from race join Politcal on race.fk_politcal = Politcal.politcal where race = '%s' and locationID = '%s'", race, location);

                    Connection con2 = DriverManager.getConnection(connectionString);
                    Statement stmt2 = con2.createStatement();
                    ResultSet result2 = stmt2.executeQuery(SQL2);

                    List<Pol> runners = new ArrayList<Pol>();
                    while(result2.next()){
                        Pol pol = new Pol();
                        pol.setName(result2.getString("Name"));
                        pol.setPoliticalParty(result2.getString("politicalParty"));
                        pol.setDescription(result2.getString("description"));
                        pol.setImageURL(result2.getString("imageURL"));
                        runners.add(pol);
                    }
                    
                    Races v = new Races();

                    String SQL3 = String.format("select * from Location where locationID = '%s'", result.getInt("locationID")+"");

                    Connection con3 = DriverManager.getConnection(connectionString);
                    Statement stmt3 = con3.createStatement();
                    ResultSet result3 = stmt3.executeQuery(SQL3);
                    if(result3.next()){
                        v.setState(result3.getString("state"));
                        v.setCity(result3.getString("city"));
                    }else{
                        v.setState("Not Added Yet");
                        v.setCity("Note Added Yet");
                    }
                    

                    v.setRunners(runners);
                    v.setRace(race);
                    response.add(v);
                }
                con.close();
            } catch (SQLException e) {
                aVoteCount.setRace(e.getMessage());
                response.add(aVoteCount);
            }
            
            return response;
        }else{
            return new ArrayList<Races>();
        }
    }
}
