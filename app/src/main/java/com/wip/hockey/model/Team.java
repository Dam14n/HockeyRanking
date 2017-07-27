package com.wip.hockey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by djorda on 15/05/2017.
 */

public class Team implements IIdentificable {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Logo")
    @Expose
    private int logo;
    @SerializedName("PlayersIds")
    @Expose
    private List<Integer> playersIds;
    @SerializedName("LocalMatchesIds")
    @Expose
    private List<Integer> localMatchesIds;
    @SerializedName("AwayMatchesIds")
    @Expose
    private List<Integer> awayMatchesIds;
    @SerializedName("GoalsIds")
    @Expose
    private List<Integer> goalsIds;
    @SerializedName("LogoId")
    @Expose
    private int logoId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public List<Integer> getPlayersIds() {
        return playersIds;
    }

    public void setPlayersIds(List<Integer> playersIds) {
        this.playersIds = playersIds;
    }

    public List<Integer> getLocalMatchesIds() {
        return localMatchesIds;
    }

    public void setLocalMatchesIds(List<Integer> localMatchesIds) {
        this.localMatchesIds = localMatchesIds;
    }

    public List<Integer> getAwayMatchesIds() {
        return awayMatchesIds;
    }

    public void setAwayMatchesIds(List<Integer> awayMatchesIds) {
        this.awayMatchesIds = awayMatchesIds;
    }

    public List<Integer> getGoalsIds() {
        return goalsIds;
    }

    public void setGoalsIds(List<Integer> goalsIds) {
        this.goalsIds = goalsIds;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }
}
