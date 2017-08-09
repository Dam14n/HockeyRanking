package com.wip.hockey.model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wip.hockey.BR;

import java.util.List;

public class Match extends BaseObservable implements  IIdentificable{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("DateId")
    @Expose
    private int dateId;
    @SerializedName("LocalTeamId")
    @Expose
    private int localTeamId;
    private Team localTeam;
    @SerializedName("EnemyTeamId")
    @Expose
    private int enemyTeamId;
    private Team enemyTeam;
    @SerializedName("LocalGoalsIds")
    @Expose
    private List<Integer> localGoalsIds;
    @SerializedName("EnemyGoalsIds")
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

    @Bindable
    public Team getLocalTeam() {
        return this.localTeam;
    }

    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
        notifyPropertyChanged(BR.localTeam);
    }

    @Bindable
    public Team getEnemyTeam() {
        return enemyTeam;
    }

    public void setEnemyTeam(Team enemyTeam) {
        this.enemyTeam = enemyTeam;
        notifyPropertyChanged(BR.enemyTeam);
    }
}
