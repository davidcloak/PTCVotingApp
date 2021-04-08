package com.example.demo.Voter;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Tools.Tools;

import java.sql.*;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoterController {
    Tools t = new Tools();
    private String connectionString = t.connection;
    
    //GET
    @RequestMapping(value = "/voters/get", method = RequestMethod.GET)
    public List<Voter> allVoters(@RequestHeader("Authorization") String auth, @RequestParam(defaultValue = "*") String fullName) {
        if(t.isUser(auth).getAccessLevel().equals("Good")){
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
                    v.setVoterID(result.getString("fk_Id"));
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
        }else{
            return new ArrayList<Voter>();
        }
    }

     //POST
     @RequestMapping(value = "/voters/post", method = RequestMethod.POST)
     public List<Voter> newVoter(@RequestHeader("Authorization") String auth, @RequestParam String fullName,
     @RequestParam String voterID,
     @RequestParam String candidates,
     @RequestParam String party,
     @RequestParam String race){
        if(t.isUser(auth).getAccessLevel().equals("Good")){
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
        }else{
            return new ArrayList<Voter>();
        }
     }


     
}
