package com.wip.hockey.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wip.hockey.model.Category;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by djorda on 09/06/2017.
 */

public class FavoriteManager {


    private Context context;

    public FavoriteManager(Context context) {
        this.context = context;
    }

    public void deleteFavorite(Category category){
        SharedPreferences sharedPrefs = context.getSharedPreferences("Favorite", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        ArrayList<Category> data = getData(sharedPrefs);
        Category cat = exist(data,category);
        if( cat != null){
            data.remove(cat);
        }
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("Favorite", json);
        editor.commit();
        Log.d(MainActivity.TAG,data.toString());

    }

    public Category exist(ArrayList<Category> data, Category category){
        for (Category cat: data) {
            if (cat.getId().equals(category.getId())){
                return cat;
            }

        }
        return null;
    }

    public void saveFavorite(Category category){
        SharedPreferences sharedPrefs = context.getSharedPreferences("Favorite", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        ArrayList<Category> data = getData(sharedPrefs);
        data.add(category);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("Favorite", json);
        editor.commit();
        Log.d(MainActivity.TAG,data.toString());
    }

    private ArrayList getData(SharedPreferences sharedPrefs){
        String json = sharedPrefs.getString("Favorite" , null);
        if(json == null){
            return new ArrayList();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        ArrayList data = gson.fromJson(json, type);
        return data;
    }

    public void removeAll() {
        SharedPreferences sharedPrefs = context.getSharedPreferences("Favorite", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        ArrayList<Category> data = getData(sharedPrefs);
        data.clear();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("Favorite", json);
        editor.commit();
        Log.d(MainActivity.TAG,data.toString());
    }
}
