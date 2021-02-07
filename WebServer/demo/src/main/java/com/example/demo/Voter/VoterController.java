package com.example.demo.Voter;

import java.util.ArrayList;
import java.util.List;
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
            SQL = "select * from voter";
        }else{
            SQL = String.format("select * from voter where fullname = '%s'", fullName);
        }

        try {
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(SQL);
            while(result.next()){
                Voter v = new Voter();
                v.setFullName(result.getString("fullName"));
                v.setVoterID(result.getInt("voterID"));
                v.setCandidates(result.getString("candidates"));
                v.setParty(result.getString("party"));
                v.setRace(result.getString("race"));
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
}
