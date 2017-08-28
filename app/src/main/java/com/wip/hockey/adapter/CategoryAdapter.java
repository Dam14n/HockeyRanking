package com.wip.hockey.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemCategoryBinding;
import com.wip.hockey.fragment.ListCategoryFragment;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.model.User;
import com.wip.hockey.viewModel.FavoriteViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private static final String TAG = CategoryAdapter.class.getSimpleName();
    private final ListCategoryFragment mFragment;
    private final User user;
    private final ViewType type;
    private final String subDivisionName;
    private List<Category> categoryList;
    private FavoriteViewModel favoriteViewModel;

    public CategoryAdapter(ListCategoryFragment fragment, User user, ViewType type, String subDivisionName) {
        mFragment = fragment;
        this.user = user;
        this.type = type;
        this.subDivisionName = subDivisionName;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemCategoryBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_category,
                        parent, false);
        favoriteViewModel = ViewModelProviders.of(mFragment).get(FavoriteViewModel.class);
        binding.setHandler(mFragment);

        return new CategoryAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {
        holder.binding.setCategory(categoryList.get(position));
        holder.checkFavorite();
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }


    public void setCategoryList(final List<Category> categoryList) {
        if (this.categoryList == null) {
            this.categoryList = categoryList;
            notifyItemRangeInserted(0, categoryList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return CategoryAdapter.this.categoryList.size();
                }

                @Override
                public int getNewListSize() {
                    return categoryList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return CategoryAdapter.this.categoryList.get(oldItemPosition).getId() ==
                            categoryList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Category category = categoryList.get(newItemPosition);
                    Category old = categoryList.get(oldItemPosition);
                    return category.getId() == old.getId();
                }
            });
            this.categoryList = categoryList;
            result.dispatchUpdatesTo(this);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final ListItemCategoryBinding binding;
        private Favorite favorite;

        public MyViewHolder(ListItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.favorite.setOnClickListener(this);
        }

        public void checkFavorite(){
            favoriteViewModel.getFavoriteByCategoryIdAndType(binding.getCategory().getId(),type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(favorite -> {
                        binding.favorite.setImageResource(R.drawable.button_pressed);
                        this.favorite = favorite;
                    },throwable -> Log.d(TAG, "No soy favorito."));
        }

        @Override
        public void onClick(View view) {
            if (favorite != null){
                binding.favorite.setImageResource(R.drawable.button_normal);
                favoriteViewModel.deleteFavorite(favorite)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {},throwable -> {});
                this.favorite = null;
            }else {
                binding.favorite.setImageResource(R.drawable.button_pressed);
                this.favorite = new Favorite();
                this.favorite.setCategoryId(binding.getCategory().getId());
                this.favorite.setUserId(user.getId());
                this.favorite.setFavoriteType(type);
                this.favorite.setSubDivisionName(subDivisionName);
                favoriteViewModel.addFavorite(favorite)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( () -> {});
            }
        }
    }
}
