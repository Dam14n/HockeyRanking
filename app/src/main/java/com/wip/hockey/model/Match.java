package com.wip.hockey.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by djorda on 15/05/2017.
 */

public class Match implements  IIdentificable{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("DateId")
    @Expose
    private int dateId;
    @SerializedName("LocalTeamId")
    @Expose
    private int localTeamId;
    @SerializedName("EnemyTeamId")
    @Expose
    private int enemyTeamId;
    @SerializedName("LocalGoalsIds")
    @Expose
    private List<Integer> localGoalsIds;
    @SerializedName("AwayGoalsIds")
    @Expose
    private List<Integer> enemyGoalsIds;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public int getLocalTeamId() {
        return localTeamId;
    }

    public void setLocalTeamId(int localTeamId) {
        this.localTeamId = localTeamId;
    }

    public int getEnemyTeamId() {
        return enemyTeamId;
    }

    public void setEnemyTeamId(int enemyTeamId) {
        this.enemyTeamId = enemyTeamId;
    }

    public List<Integer> getLocalGoalsIds() {
        return localGoalsIds;
    }

    public void setLocalGoalsIds(List<Integer> localGoalsIds) {
        this.localGoalsIds = localGoalsIds;
    }

    public List<Integer> getEnemyGoalsIds() {
        return enemyGoalsIds;
    }

    public void setEnemyGoalsIds(List<Integer> enemyGoalsIds) {
        this.enemyGoalsIds = enemyGoalsIds;
    }
}
