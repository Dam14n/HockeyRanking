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

        int[] imageIds = getImages();
        String[] titles = getTitles();

        for ( int i = 0; i < titles.length ; i++){
            NavigationDrawerItem navItem = new NavigationDrawerItem();
            navItem.setTitle(titles[i]);
            navItem.setImageId(imageIds[i]);
            dataList.add(navItem);
        }

        return dataList;
    }
    private  static int[] getImages(){

        return new int[]{
                R.drawable.images_1,
                R.drawable.images_2,
                R.drawable.images_3,
                R.drawable.images_4,
                R.drawable.images_5,
                R.drawable.images_6,
                R.drawable.images_7
        };
    }

    private static String[] getTitles(){

        return  new String[]{
                "Birds",
                "Animals",
                "Forest",
                "Ocean",
                "Planets",
                "Landscape",
                "Navigate"
        };
    }
}
