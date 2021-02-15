package com.example.demo.User;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Voter.Races;
import com.example.demo.Voter.VoteCount;

import java.sql.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private String connectionString = "jdbc:sqlserver://voteshield.database.windows.net;databaseName=voteShield;user=Nate;password=Ghost123";

    // Made By David Please to test UserPassing for android please leave alone for now
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public List<User> GetUser(@RequestParam(defaultValue = "*") String email) {
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
    }

    //Add way to get number of votes and who recieved those votes
     // - Make Java Class to hold Name and number of votes and what race it was in
     @RequestMapping(value = "/getVoteCount", method = RequestMethod.GET)
     public List<VoteCount> GetVoteCount(@RequestParam(defaultValue = "*") String race){
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
     }
 
      //Races list of people in the race
    @RequestMapping(value = "/getRaces", method = RequestMethod.GET)
    public List<Races> GetRaces(){
        List<Races> response = new ArrayList<Races>();
        Races aVoteCount = new Races();
        String SQL = "select race from race group by race";

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

                ArrayList<String[]> runners = new ArrayList<String[]>();
                while(result2.next()){
                    String[] runner = new String[2];
                    runner[0] = result2.getString("Name");
                    runner[1] = result2.getString("politicalParty");
                    runners.add(runner);
                }

                Races v = new Races();
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
    }
}
