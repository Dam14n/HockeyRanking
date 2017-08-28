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
    private final User user;
    private FavoriteViewModel favoriteViewModel;
    private List<Favorite> favoriteList;
    private List<Category> categoryList;

    public PositionFavoriteAdapter(ListFavoriteFragment fragment, User user, FavoriteViewModel favoriteViewModel){
        this.mFragment = fragment;
        this.user = user;
        this.favoriteViewModel = favoriteViewModel;
        this.categoryList = new ArrayList<>();
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
        holder.binding.setCategory(categoryList.get(position));
        holder.binding.executePendingBindings();
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        if (this.favoriteList == null){
            this.favoriteList = favoriteList;
            getCategories();
        }
    }

    private void getCategories() {
        for (Favorite favorite: favoriteList ) {
            favoriteViewModel.getCategory(favorite.getCategoryId())
                    .subscribe(new PositionFavoriteObserver(favorite));
        }
    }

    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }

    private class PositionFavoriteObserver implements Observer<Category> {

        private final Favorite favorite;

        public PositionFavoriteObserver(Favorite favorite) {
            this.favorite = favorite;
        }

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Category category) {
            category.setFavorite(favorite);
            categoryList.add(category);
            Collections.sort(categoryList, (left, right) -> left.getId() - right.getId());
            notifyItemInserted(categoryList.indexOf(category));
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {

        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final ListItemFavoriteBinding binding;

        public MyViewHolder(ListItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.favorite.setImageResource(R.drawable.button_pressed);
            binding.favorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            favoriteViewModel.deleteFavorite(binding.getCategory().getFavorite())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        int index = categoryList.indexOf(binding.getCategory());
                        categoryList.remove(index);
                        notifyDataSetChanged();
                    },throwable -> {});
        }
    }
}
