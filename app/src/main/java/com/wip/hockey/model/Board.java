package com.wip.hockey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Board implements  IIdentificable{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PositionsIds")
    @Expose
    private List<Integer> positionsIds;

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

    public List<Integer> getPositionsIds() {
        return positionsIds;
    }

    public void setPositionsIds(List<Integer> positionsIds) {
        this.positionsIds = positionsIds;
    }
}
