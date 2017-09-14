package com.wip.hockey.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.wip.hockey.fragment.ViewType;

import java.util.List;

@Entity
public class Favorite implements IIdentificable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = User.class,parentColumns = "id",childColumns = "userId")
    private int userId;
    private int categoryId;
    @Ignore
    private List<Category> categories;
    private ViewType favoriteType;
    private String subDivisionName;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setFavoriteType(ViewType favoriteType) {
        this.favoriteType = favoriteType;
    }

    public ViewType getFavoriteType() {
        return favoriteType;
    }

    public String getSubDivisionName() {
        return subDivisionName;
    }

    public void setSubDivisionName(String subDivisionName) {
        this.subDivisionName = subDivisionName;
    }
}

