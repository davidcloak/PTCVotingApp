package com.example.demo.Voter;

public class VoteCount {//Votes, Canidate, fk_race as Race 
    String Votes;
    String Canidate;
    String Race;

    public VoteCount() {
    }

    public VoteCount(String votes, String canidate, String race) {
        Votes = votes;
        Canidate = canidate;
        Race = race;
    }

    public String getVotes() {
        return Votes;
    }

    public void setVotes(String votes) {
        Votes = votes;
    }

    public String getCanidate() {
        return Canidate;
    }

    public void setCanidate(String canidate) {
        Canidate = canidate;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    
}
