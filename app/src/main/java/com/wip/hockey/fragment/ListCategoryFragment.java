package com.wip.hockey.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.CategoryAdapter;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.databinding.FragmentListCategoryBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.viewModel.CategoryViewModel;

import java.util.List;

public class ListCategoryFragment extends BaseFragment implements Selected {

    private final String TAG = ListCategoryFragment.class.toString();
    private CategoryAdapter categoryAdapter;
    private FragmentListCategoryBinding binding;
    private SubDivision parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_category, container, false);

        categoryAdapter = new CategoryAdapter(this);

        binding.fragmentCategoryRecycler.setAdapter(categoryAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CategoryViewModel categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.setSubDivision(this.parent);
        observeViewModel(categoryViewModel);
    }

    private void observeViewModel(final CategoryViewModel viewModel) {
        // Update the list when the data changes
        final MainActivity mainActivity = (MainActivity) this.getContext();
        viewModel.getCategoryListObservable().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                mainActivity.showProgress(true);
                if (categories != null) {
                    categoryAdapter.setCategoryList(categories);
                }
                mainActivity.showProgress(false);
            }
        });
    }


    public String getTAG(){
        return TAG;
    }

    public void onClick(Category category) {
        Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_pager_date);
        selected.setSelectedFrom(category);
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.parent = (SubDivision) object;
    }
}
