package com.wip.hockey.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemCategoryBinding;
import com.wip.hockey.fragment.ListCategoryFragment;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Favorite;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private static final String TAG = CategoryAdapter.class.getSimpleName();
    private final ListCategoryFragment mFragment;
    private List<Category> categoryList;
    private List<Favorite> favorites;

    public CategoryAdapter(ListCategoryFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemCategoryBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_category,
                        parent, false);
        binding.setHandler(mFragment);

        return new CategoryAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {
        holder.binding.setCategory(categoryList.get(position));
        holder.binding.setFavorite(getFavoriteFromCategory(categoryList.get(position)));
        holder.binding.executePendingBindings();
    }

    private Favorite getFavoriteFromCategory(Category category) {
        if (favorites != null) {
            for (Favorite favorite : favorites) {
                if (favorite.getCategoryId() == category.getId())
                    return favorite;
            }
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }


    public void setCategoryList(final List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public void setFavoriteList(List<Favorite> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        final ListItemCategoryBinding binding;

        public MyViewHolder(ListItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
