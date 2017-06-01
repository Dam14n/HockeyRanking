package com.wip.hockey.model;

/**
 * Created by djorda on 23/05/2017.
 */

public class Category {

    private Team[] teams;
    private Date[] dates;

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public Date[] getDates() {
        return dates;
    }

    public void setDates(Date[] dates) {
        this.dates = dates;
    }
}
