package com.wip.hockey.model;

import java.util.List;

/**
 * Created by djorda on 08/07/2017.
 */

public class User implements IIdentificable {

    private int id;
    private List<Favorite> favorites;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
}
