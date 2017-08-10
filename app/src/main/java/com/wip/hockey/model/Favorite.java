package com.wip.hockey.model;

import java.util.List;

public class Favorite implements IIdentificable {

    private int id;
    private List<Category> categories;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

