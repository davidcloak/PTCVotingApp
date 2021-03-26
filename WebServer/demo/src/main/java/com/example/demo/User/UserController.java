package com.example.demo.User;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Tools.Tools;
import com.example.demo.Voter.Races;
import com.example.demo.Voter.VoteCount;

import java.sql.*;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

@RestController
public class UserController {
    Tools t = new Tools();
    private String connectionString = t.connection;

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

    //Add way to get number of votes and who recieved those votes
     // - Make Java Class to hold Name and number of votes and what race it was in
     @RequestMapping(value = "/getVoteCount", method = RequestMethod.GET)
     public List<VoteCount> GetVoteCount(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String race){
         if(t.isUser(auth).getAccessLevel().equals("Good")){
            List<VoteCount> response = new ArrayList<VoteCount>();
            VoteCount aVoteCount = new VoteCount();
            String SQL = "";
    
            if (race.equals("*")) {
                // fail
            } else {
                SQL = String.format("select count(Canidate) as Votes, Canidate, fk_race as Race from voting where fk_race = '%s' group by Canidate, fk_race order by Votes desc", race);
    
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

    private String getLocationID(String state, String city){
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
                String SQL = "select * from Politcal where politcal not in (select fk_politcal from race)";
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
}
