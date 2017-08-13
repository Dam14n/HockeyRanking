package com.wip.hockey.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wip.hockey.BR;

import java.util.List;

public class Team extends BaseObservable implements IIdentificable {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PlayersIds")
    @Expose
    private List<Integer> playersIds;
    @SerializedName("LocalMatchesIds")
    @Expose
    private List<Integer> localMatchesIds;
    @SerializedName("AwayMatchesIds")
    @Expose
    private List<Integer> awayMatchesIds;
    @SerializedName("GoalsIds")
    @Expose
    private List<Integer> goalsIds;
    @SerializedName("LogoId")
    @Expose
    private int logoId;
    private Logo logo;
    private Bitmap image;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPlayersIds() {
        return playersIds;
    }

    public void setPlayersIds(List<Integer> playersIds) {
        this.playersIds = playersIds;
    }

    public List<Integer> getLocalMatchesIds() {
        return localMatchesIds;
    }

    public void setLocalMatchesIds(List<Integer> localMatchesIds) {
        this.localMatchesIds = localMatchesIds;
    }

    public List<Integer> getAwayMatchesIds() {
        return awayMatchesIds;
    }

    public void setAwayMatchesIds(List<Integer> awayMatchesIds) {
        this.awayMatchesIds = awayMatchesIds;
    }

    public List<Integer> getGoalsIds() {
        return goalsIds;
    }

    public void setGoalsIds(List<Integer> goalsIds) {
        this.goalsIds = goalsIds;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        byte[] imgBytes = Base64.decode(logo.getImage().getBytes(),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        this.setImage(bitmap);
        this.logo = logo;
    }

    @Bindable
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
        this.notifyPropertyChanged(BR.image);
    }
}
