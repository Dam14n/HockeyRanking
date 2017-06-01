package com.wip.hockey.model;

import com.wip.hockey.R;

import java.util.ArrayList;

/**
 * Created by djorda on 15/05/2017.
 */

public class Match {

    private Team localTeam,enemyTeam;
    private Goal localGoals;
    private Goal enemyGoals;

    public String getNameLocalTeam() {
        return localTeam.getName();
    }

    public String getNameEnemyTeam() {
        return enemyTeam.getName();
    }

    public int getLogoLocalTeam() {
        return localTeam.getLogo();
    }

    public int getLogoEnemyTeam() {
        return enemyTeam.getLogo();
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
    }

    public Team getEnemyTeam() {
        return enemyTeam;
    }

    public void setEnemyTeam(Team enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    public Goal getLocalGoals() {
        return localGoals;
    }

    public void setLocalGoals(Goal localGoals) {
        this.localGoals = localGoals;
    }

    public Goal getEnemyGoals() {
        return enemyGoals;
    }

    public void setEnemyGoals(Goal enemyGoals) {
        this.enemyGoals = enemyGoals;
    }

    public static ArrayList<Match> getData(){
        ArrayList<Match> dataList = new ArrayList<>();
        int[] images = getImages();
        String[] names = getNames();

        for (int i = 0 ; i < images.length ; i+=2){
            Match match = new Match();

            Team localTeam = new Team();
            localTeam.setLogo(images[i]);
            localTeam.setName(names[i]);

            Team enemyTeam = new Team();
            enemyTeam.setLogo(images[i+1]);
            enemyTeam.setName(names[i+1]);

            match.setEnemyTeam(enemyTeam);
            match.setLocalTeam(localTeam);

            dataList.add(match);
        }

        return dataList;
    }

    private static int[] getImages() {

        int[] images = {
                R.drawable.regatas,
                R.drawable.san_luis,
                R.drawable.alem_quilmes,
                R.drawable.bacrc

        };

        return images;
    }

    private static String[] getNames() {

        String[] names = {
                "REGATAS DE AVELLANEDA",
                "SAN LUIS",
                "ALEMAN DE QUILMES",
                "BACRC"
        };

        return names;
    }
}
