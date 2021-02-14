package com.example.demo.Voter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.demo.User.User;

import java.sql.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoterController {
    
    private String connectionString = "jdbc:sqlserver://voteshield.database.windows.net;databaseName=voteShield;user=Nate;password=Ghost123";
    //GET
    @RequestMapping(value = "/voters/get", method = RequestMethod.GET)
    public List<Voter> allVoters(@RequestParam(defaultValue = "*") String fullName) {
        List<Voter> response = new ArrayList<Voter>();

        Voter aVoter = new Voter();
        String SQL = "";
        
        if(fullName.equals("*")){
            SQL = "select * from politcal";
        }else{
            SQL = String.format("select * from politcal where Name = '%s'", fullName);
        }

        try {
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(SQL);
            while(result.next()){
                Voter v = new Voter();
                v.setFullName(result.getString("name"));
                v.setVoterID(result.getInt("fk_Id"));
                v.setParty(result.getString("politicalParty"));
                v.setStatus(200);
                response.add(v);
            }
            con.close();
        } catch (SQLException e) {
            aVoter.setFullName(e.getMessage());
            aVoter.setCandidates(SQL);
            aVoter.setStatus(500);
            response.add(aVoter);
        }
        return response;
    }

     //POST
     @RequestMapping(value = "/voters/post", method = RequestMethod.POST)
     public List<Voter> newVoter(@RequestParam String fullName,
     @RequestParam int voterID,
     @RequestParam String candidates,
     @RequestParam String party,
     @RequestParam String race){
        List<Voter> response = new ArrayList<Voter>();
        Voter aVoter = new Voter();
        String SQL = String.format("insert into voter(fullName, voterID, candidates, party, race) values ('%s', '%d', '%s', '%s', '%s')", fullName, voterID, candidates, party, race);

        try {
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            stmt.execute(SQL);
            aVoter.setFullName(fullName);
            aVoter.setVoterID(voterID);
            aVoter.setCandidates(candidates);
            aVoter.setParty(party);
            aVoter.setRace(race);
            aVoter.setStatus(200);
            response.add(aVoter);

            con.close();
        } catch (SQLException e) {
            aVoter.setFullName(e.getMessage());
            aVoter.setCandidates(SQL);
            aVoter.setStatus(500);
            response.add(aVoter);
        }

        return response;
     }


     //Add way to get number of votes and who recieved those votes
     //TODO - Make Java Class to hold Name and number of votes and what race it was in
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public List<User> GetUser(@RequestParam(defaultValue = "*") String race){
        List<User> response = new ArrayList<User>();
        User aUser = new User();
        String SQL = "";

        if (race.equals("*")) {
            // fail
            aUser.setId(null);
            aUser.setUserName("No email sent");
            aUser.setStatus("404");
            response.add(aUser);
        } else {
            SQL = String.format("select count(Canidate) as Votes, Canidate, fk_race as Race from voting where fk_race = '%s' group by Canidate, fk_race order by Votes desc", race);

            try {
                Connection con = DriverManager.getConnection(connectionString);
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(SQL);
                while (result.next()) {
                    User v = new User();
                    
                    response.add(v);
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

     //Races list of people in the race
}
