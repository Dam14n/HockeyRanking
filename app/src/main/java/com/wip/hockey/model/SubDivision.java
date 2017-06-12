package com.wip.hockey.model;

import java.util.ArrayList;

/**
 * Created by djorda on 23/05/2017.
 */

public class SubDivision {

    private ArrayList<Category> categories;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}
