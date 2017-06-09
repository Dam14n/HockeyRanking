package com.wip.hockey.model;

import java.util.ArrayList;

/**
 * Created by djorda on 23/05/2017.
 */

public class Category {

    private String id;
    private Team teams;
    private Date[] dates;
    private String name;
    private ArrayList<Match> match;
    private boolean favorite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Team getTeams() {
        return teams;
    }

    public ArrayList<Match> getMatch() {
        return match;
    }

    public void setMatch(ArrayList<Match> match) {
        this.match = match;
    }

    public void setTeams(Team teams) {
        this.teams = teams;
    }

    public Date[] getDates() {
        return dates;
    }

    public void setDates(Date[] dates) {
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
