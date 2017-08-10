package com.wip.hockey.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemCategoryBinding;
import com.wip.hockey.fragment.ListCategoryFragment;
import com.wip.hockey.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private static final String TAG = CategoryAdapter.class.getSimpleName();
    private final ListCategoryFragment mFragment;
    private List<Category> categoryList;

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

    class MyViewHolder extends RecyclerView.ViewHolder {

        final ListItemCategoryBinding binding;

        public MyViewHolder(ListItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
