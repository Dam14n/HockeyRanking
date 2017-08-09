package com.wip.hockey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Division implements IIdentificable{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SubDivisionsIds")
    @Expose
    private List<Integer> subDivisionsIds = new ArrayList();
    @SerializedName("Id")
    @Expose
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSubDivisionsIds() {
        return subDivisionsIds;
    }

    public void setSubDivisionsIds(List<Integer> subDivisionsIds) {
        this.subDivisionsIds = subDivisionsIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
