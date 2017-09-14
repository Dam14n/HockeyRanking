package com.wip.hockey.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Division implements IIdentificable{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SubDivisionsIds")
    @Expose
    @Ignore
    private List<Integer> subDivisionsIds = new ArrayList();
    @SerializedName("Id")
    @Expose
    @PrimaryKey
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
