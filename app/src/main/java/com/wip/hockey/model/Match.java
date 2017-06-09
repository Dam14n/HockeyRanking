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

}
