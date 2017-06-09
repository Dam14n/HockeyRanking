package com.wip.hockey.model;

import java.util.ArrayList;

/**
 * Created by djorda on 23/05/2017.
 */

public class Division {

    private ArrayList<SubDivision> subDivision;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubDivision> getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(ArrayList<SubDivision> subDivision) {
        this.subDivision = subDivision;
    }

}
