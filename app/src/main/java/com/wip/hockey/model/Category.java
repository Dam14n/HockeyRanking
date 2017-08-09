package com.wip.hockey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category implements  IIdentificable{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SubDivisionId")
    @Expose
    private int subDivisionId;
    @SerializedName("DatesIds")
    @Expose
    private List<Integer> datesIds;

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

    public int getSubDivisionId() {
        return subDivisionId;
    }

    public void setSubDivisionId(int subDivisionId) {
        this.subDivisionId = subDivisionId;
    }

    public List<Integer> getDatesIds() {
        return datesIds;
    }

    public void setDatesIds(List<Integer> datesIds) {
        this.datesIds = datesIds;
    }
}
