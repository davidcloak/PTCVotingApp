package com.example.votingapp.RacesHolders;

public class Pol {
    String Name;
    String politicalParty;
    String imageURL;
    String description;
    
    public Pol() {
    }

    public Pol(String name, String politicalParty, String imageURL, String description) {
        Name = name;
        this.politicalParty = politicalParty;
        this.imageURL = imageURL;
        this.description = description;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    
}
