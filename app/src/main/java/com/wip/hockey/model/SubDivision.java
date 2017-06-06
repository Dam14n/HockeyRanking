package com.wip.hockey.model;

import java.util.ArrayList;

/**
 * Created by djorda on 23/05/2017.
 */

public class SubDivision {

    private Category[] categories;
    private String name;
    private String division;

    public SubDivision(String division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public ArrayList<SubDivision> getData(){
        ArrayList<SubDivision> dataList = new ArrayList<>();
        String[] names = getNames();

        for (int i = 0 ; i < names.length ; i++){
            SubDivision subDivision = new SubDivision(names[i]);

            subDivision.setName(names[i]);

            dataList.add(subDivision);
        }

        return dataList;
    }

    private String[] getNames() {

        String[] names = {
                division + "1",
                division + "2",
                division + "3",
                division + "4"
        };

        return names;
    }
}
