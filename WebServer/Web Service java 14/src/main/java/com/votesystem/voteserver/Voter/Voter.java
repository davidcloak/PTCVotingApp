package com.votesystem.voteserver.Voter;

public class Voter {
    private String fullName;
    private int voterID;
    private String candidates;
    private String party;
    private String race;
    private int status;

    public Voter() {
    }

    public Voter(String fullName, int voterID, String candidates, String party, String race, int status) {
        this.fullName = fullName;
        this.voterID = voterID;
        this.candidates = candidates;
        this.party = party;
        this.race = race;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getVoterID() {
        return voterID;
    }

    public void setVoterID(int voterID) {
        this.voterID = voterID;
    }

    public String getCandidates() {
        return candidates;
    }

    public void setCandidates(String candidates) {
        this.candidates = candidates;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Voter [candidates=" + candidates + ", fullName=" + fullName + ", party=" + party + ", race=" + race
                + ", status=" + status + ", voterID=" + voterID + "]";
    }
    
    
}
