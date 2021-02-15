package com.example.demo.Voter;

import java.util.ArrayList;

public class Races {
    ArrayList<String[]> Runners;
    String Race;

    public Races() {
    }

    public Races(ArrayList<String[]> runners, String race) {
        Runners = runners;
        Race = race;
    }

    public ArrayList<String[]> getRunners() {
        return Runners;
    }

    public void setRunners(ArrayList<String[]> runners) {
        Runners = runners;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    
}
