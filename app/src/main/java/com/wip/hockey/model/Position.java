package com.wip.hockey.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Position implements IIdentificable {

    @SerializedName("Id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("Rank")
    @Expose
    private int rank;
    @SerializedName("TeamId")
    @Expose
    private int teamId;
    @SerializedName("BoardId")
    @Expose
    private int boardId;
    @SerializedName("Points")
    @Expose
    private int points;
    @SerializedName("PlayedMatches")
    @Expose
    private int playedMatches;
    @SerializedName("WinMatches")
    @Expose
    private int winMatches;
    @SerializedName("TieMatches")
    @Expose
    private int tieMatches;
    @SerializedName("LoseMatches")
    @Expose
    private int loseMatches;
    @SerializedName("FavorGoals")
    @Expose
    private int favorGoals;
    @SerializedName("AgainstGoals")
    @Expose
    private int againstGoals;
    @SerializedName("DifferenceGoals")
    @Expose
    private int differenceGoals;
    @SerializedName("TeamName")
    @Expose
    private String teamName;
    private int categoryId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(int playedMatches) {
        this.playedMatches = playedMatches;
    }

    public int getWinMatches() {
        return winMatches;
    }

    public void setWinMatches(int winMatches) {
        this.winMatches = winMatches;
    }

    public int getTieMatches() {
        return tieMatches;
    }

    public void setTieMatches(int tieMatches) {
        this.tieMatches = tieMatches;
    }

    public int getLoseMatches() {
        return loseMatches;
    }

    public void setLoseMatches(int loseMatches) {
        this.loseMatches = loseMatches;
    }

    public int getFavorGoals() {
        return favorGoals;
    }

    public void setFavorGoals(int favorGoals) {
        this.favorGoals = favorGoals;
    }

    public int getAgainstGoals() {
        return againstGoals;
    }

    public void setAgainstGoals(int againstGoals) {
        this.againstGoals = againstGoals;
    }

    public int getDifferenceGoals() {
        return differenceGoals;
    }

    public void setDifferenceGoals(int differenceGoals) {
        this.differenceGoals = differenceGoals;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
