package com.wip.hockey.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Logo implements IIdentificable {

    @SerializedName("Id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Width")
    @Expose
    private int width;
    @SerializedName("Height")
    @Expose
    private int height;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Size")
    @Expose
    private int size;
    @SerializedName("Type")
    @Expose
    private String type;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
