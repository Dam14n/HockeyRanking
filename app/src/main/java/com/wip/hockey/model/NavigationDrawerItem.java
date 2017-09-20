package com.wip.hockey.model;

import com.wip.hockey.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djorda on 12/05/2017.
 */

public class NavigationDrawerItem {

    private String title;
    private int imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static List<NavigationDrawerItem> getData(){
        List<NavigationDrawerItem> dataList = new ArrayList<>();
        String[] titles = getTitles();

        for ( int i = 0; i < titles.length ; i++){
            NavigationDrawerItem navItem = new NavigationDrawerItem();
            navItem.setTitle(titles[i]);
            dataList.add(navItem);
        }

        return dataList;
    }

    private static String[] getTitles(){

        return  new String[]{
                "Fixtures",
                "Positions",
                "Favorites"
        };
    }
}
