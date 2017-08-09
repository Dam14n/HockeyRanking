package com.wip.hockey.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wip.hockey.BR;

public class Position extends BaseObservable implements IIdentificable {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("TeamId")
    @Expose
    private int teamId;
    private Team team;
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

    @Bindable
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        notifyPropertyChanged(BR.team);
    }
}
