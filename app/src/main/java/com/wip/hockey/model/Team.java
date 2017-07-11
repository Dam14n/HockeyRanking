package com.wip.hockey.model;

import java.util.List;

/**
 * Created by djorda on 15/05/2017.
 */

public class Team implements IIdentificable {

    private int id;
    private String name;
    private String location;
    private int logo;
    private List<Player> players;
    private List<Match> localMatches;
    private List<Match> awayMatches;
    private List<Goal> goals;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Match> getLocalMatches() {
        return localMatches;
    }

    public void setLocalMatches(List<Match> localMatches) {
        this.localMatches = localMatches;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
