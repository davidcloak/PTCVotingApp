package com.example.demo.Voter;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.User.Pol;

public class Races {
    List<Pol> Runners;
    String Race;
    String state;
    String City;

    public Races() {
    }

    public Races(List<Pol> runners, String race, String state, String city) {
        Runners = runners;
        Race = race;
        this.state = state;
        City = city;
    }

    public List<Pol> getRunners() {
        return Runners;
    }

    public void setRunners(List<Pol> runners) {
        Runners = runners;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
    
    
}
