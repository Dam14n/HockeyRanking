package com.wip.hockey.app;

import com.wip.hockey.model.User;

public class FavoriteManager {


    private FavoriteManager favoriteManager;
    private User user;

    private FavoriteManager() {
        this.favoriteManager = new FavoriteManager();
    }

    public FavoriteManager getInstance(){
        return  favoriteManager;
    }

    public void setUser(User user){
        this.user = user;
    }





}
