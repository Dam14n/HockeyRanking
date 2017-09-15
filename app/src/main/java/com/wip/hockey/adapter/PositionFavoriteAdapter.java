package com.wip.hockey.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemFavoriteBinding;
import com.wip.hockey.fragment.ListFavoriteFragment;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.model.User;
import com.wip.hockey.viewModel.FavoriteViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PositionFavoriteAdapter extends RecyclerView.Adapter<PositionFavoriteAdapter.MyViewHolder> {

    private static final String TAG = PositionFavoriteAdapter.class.getSimpleName();
    private final ListFavoriteFragment mFragment;
    private List<Favorite> favoriteList;

    public PositionFavoriteAdapter(ListFavoriteFragment fragment){
        this.mFragment = fragment;
    }

    @Override
    public PositionFavoriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemFavoriteBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_favorite,
                        parent, false);
        binding.setHandler(mFragment);
        return new PositionFavoriteAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final PositionFavoriteAdapter.MyViewHolder holder, int position) {
        holder.binding.setFavorite(favoriteList.get(position));
        holder.binding.executePendingBindings();
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
       this.favoriteList = favoriteList;
       notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favoriteList != null ? favoriteList.size() : 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        final ListItemFavoriteBinding binding;

        public MyViewHolder(ListItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
