package com.wip.hockey.model;

import java.util.ArrayList;

/**
 * Created by djorda on 23/05/2017.
 */

public class Category {

    private Team teams;
    private Date[] dates;
    private String name;
    private Match match;

    public Team getTeams() {
        return teams;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
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

    public ArrayList<Category> getData(){
        ArrayList<Category> dataList = new ArrayList<>();
        String[] names = getNames();

        for (int i = 0 ; i < names.length ; i++){
            Category category = new Category();

            Match match = new Match();

            category.setMatch(match);
            category.setName(names[i]);

            dataList.add(category);
        }

        return dataList;
    }

    private String[] getNames() {

        String[] names = {
                "Primera",
                "Intermedia"
        };

        return names;
    }
}
