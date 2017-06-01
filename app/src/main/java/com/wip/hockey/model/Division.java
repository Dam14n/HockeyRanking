package com.wip.hockey.model;

import java.util.ArrayList;

/**
 * Created by djorda on 23/05/2017.
 */

public class Division {

    private SubDivision subDivision;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubDivision getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(SubDivision subDivision) {
        this.subDivision = subDivision;
    }

    public static ArrayList<Division> getData(){
        ArrayList<Division> dataList = new ArrayList<>();
        String[] names = getNames();

        for (int i = 0 ; i < names.length ; i++){
            Division division = new Division();

            SubDivision primera = new SubDivision();
            SubDivision inter = new SubDivision();
            division.setSubDivision(primera);
            division.setSubDivision(inter);

            division.setName(names[i]);

            dataList.add(division);
        }

        return dataList;
    }

    private static String[] getNames() {

        String[] names = {
                "A",
                "B",
                "C",
                "D"
        };

        return names;
    }
}
