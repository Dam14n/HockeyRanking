package com.wip.hockey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Date implements IIdentificable {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("CategoryId")
    @Expose
    private int categoryId;
    @SerializedName("MatchesIds")
    @Expose
    private List<Integer> matchesIds;
    @SerializedName("DateNumber")
    @Expose
    private int dateNumber;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getMatchesIds() {
        return matchesIds;
    }

    public void setMatchesIds(List<Integer> matchesIds) {
        this.matchesIds = matchesIds;
    }

    public int getDateNumber() {
        return dateNumber;
    }

    public void setDateNumber(int dateNumber) {
        this.dateNumber = dateNumber;
    }
}
