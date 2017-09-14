package com.wip.hockey.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SubDivision implements IIdentificable{

    @SerializedName("Id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DivisionId")
    @Expose
    private int divisionId;
    @SerializedName("CategoriesIds")
    @Expose
    @Ignore
    private List<Integer> categoriesIds = new ArrayList();

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

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public List<Integer> getCategoriesIds() {
        return categoriesIds;
    }

    public void setCategoriesIds(List<Integer> categoriesIds) {
        this.categoriesIds = categoriesIds;
    }
}
