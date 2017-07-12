package com.wip.hockey.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wip.hockey.R;
import com.wip.hockey.adapter.FavoriteAdapter;
import com.wip.hockey.model.Category;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.fragment_favorite_recycler)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);
        ButterKnife.bind(this,view);
        getFavorites();
        setRecyclerView();
        return view;
    }

    private void getFavorites(){
        SharedPreferences sharedPrefs = this.getContext().getSharedPreferences("Favorite", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Favorite" , null);
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        ArrayList data = gson.fromJson(json, type);
       // this.setContent(data);
    }

    public void setRecyclerView(){
        FavoriteAdapter adapter = new FavoriteAdapter(this.getContext(),getContent());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_list_favorite;
    }

}
