package com.wip.hockey.model;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity(tableName = "matches")
public class Match implements  IIdentificable{

    @SerializedName("Id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("DateId")
    @Expose
    private int dateId;
    @SerializedName("LocalTeamId")
    @Expose
    private int localTeamId;
    @SerializedName("LocalTeamName")
    @Expose
    private String localTeamName;
    @SerializedName("LocalTeamLogo")
    @Expose
    @Embedded(prefix = "local_logo_")
    private Logo localTeamLogo;
    @SerializedName("EnemyTeamId")
    @Expose
    private int enemyTeamId;
    @SerializedName("EnemyTeamName")
    @Expose
    private String enemyTeamName;
    @SerializedName("EnemyTeamLogo")
    @Expose
    @Embedded(prefix = "enemy_logo_")
    private Logo EnemyTeamLogo;
    @SerializedName("LocalGoalsIds")
    @Expose
    @Ignore
    private List<Integer> localGoalsIds;
    @SerializedName("LocalGoals")
    @Expose
    private int localGoals;
    @SerializedName("EnemyGoalsIds")
    @Expose
    @Ignore
    private List<Integer> enemyGoalsIds;
    @SerializedName("EnemyGoals")
    @Expose
    private int enemyGoals;
    @SerializedName("Played")
    @Expose
    private boolean played;
    @SerializedName("DateMatch")
    @Expose
    private java.util.Date dateMatch;



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

    public String getLocalTeamName() {
        return localTeamName;
    }

    public void setLocalTeamName(String localTeamName) {
        this.localTeamName = localTeamName;
    }

    public Logo getLocalTeamLogo() {
        return localTeamLogo;
    }

    public void setLocalTeamLogo(Logo localTeamLogo) {
        this.localTeamLogo = localTeamLogo;
    }

    public String getEnemyTeamName() {
        return enemyTeamName;
    }

    public void setEnemyTeamName(String enemyTeamName) {
        this.enemyTeamName = enemyTeamName;
    }

    public Logo getEnemyTeamLogo() {
        return EnemyTeamLogo;
    }

    public void setEnemyTeamLogo(Logo enemyTeamLogo) {
        EnemyTeamLogo = enemyTeamLogo;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public int getLocalGoals() {
        return localGoals;
    }

    public void setLocalGoals(int localGoals) {
        this.localGoals = localGoals;
    }

    public int getEnemyGoals() {
        return enemyGoals;
    }

    public void setEnemyGoals(int enemyGoals) {
        this.enemyGoals = enemyGoals;
    }
}
