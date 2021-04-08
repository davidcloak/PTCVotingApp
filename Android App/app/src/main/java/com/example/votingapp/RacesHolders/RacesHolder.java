package com.example.votingapp.RacesHolders;

import java.util.List;

public class RacesHolder {
    List<Races> races;

    public RacesHolder(){}

    public RacesHolder(List<Races> races) {
        this.races = races;
    }

    public List<Races> getRaces() {
        return races;
    }

    public void setRaces(List<Races> races) {
        this.races = races;
    }
}
