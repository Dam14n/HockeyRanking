package com.wip.hockey.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Date implements IIdentificable {

    @SerializedName("Id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("CategoryId")
    @Expose
    private int categoryId;
    @SerializedName("MatchesIds")
    @Expose
    @Ignore
    private List<Integer> matchesIds;
    @SerializedName("DateNumber")
    @Expose
    private int dateNumber;
    @SerializedName("CurrentDate")
    @Expose
    private boolean currentDate;


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

    public boolean isCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(boolean currentDate) {
        this.currentDate = currentDate;
    }
}
