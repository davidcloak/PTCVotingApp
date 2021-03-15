package com.example.demo.User;

public class Pol {
    String Name;
    String politicalParty;
    
    public Pol() {
    }
    public Pol(String name, String politicalParty) {
        Name = name;
        this.politicalParty = politicalParty;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getPoliticalParty() {
        return politicalParty;
    }
    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    
}
