package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.CategoryAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListCategoryBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.model.User;
import com.wip.hockey.networking.Status;
import com.wip.hockey.viewModel.CategoryViewModel;
import com.wip.hockey.viewModel.FavoriteViewModel;
import com.wip.hockey.viewModel.factory.CategoryViewModelFactory;
import com.wip.hockey.viewModel.factory.FavoriteViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ListCategoryFragment extends BaseFragment implements Tageable{

    private final String TAG = ListCategoryFragment.class.toString();
    private CategoryAdapter categoryAdapter;
    private FragmentListCategoryBinding binding;
    private CategoryViewModel categoryViewModel;
    private ViewType type;
    private User user;
    private String subDivisionName;
    private FavoriteViewModel favoriteViewModel;

    @Inject
    CategoryViewModelFactory categoryViewModelFactory;

    @Inject
    FavoriteViewModelFactory favoriteViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        favoriteViewModel = ViewModelProviders.of(this, favoriteViewModelFactory).get(FavoriteViewModel.class);
        favoriteViewModel.getFavoritesByTypeAndUser(type,user.getId())
                .observe(this, favorites -> {
                    categoryAdapter.setFavoriteList(favorites);
                });

        categoryViewModel = ViewModelProviders.of(this, categoryViewModelFactory).get(CategoryViewModel.class);
        categoryViewModel.getUpdateStatus().observe(this, status -> {
            if (status == Status.ERROR || status == Status.SUCCESS){
                hideLoading();
                showProgress(false);
            }
        });
        categoryViewModel.init(this.getArguments().getInt(Constants.PARENT_ID));
        categoryViewModel.getCategories().observe(this, categories -> {
            categoryAdapter.setCategoryList(categories);
        });


        setupRefreshLayout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_category, container, false);

        this.type = (ViewType) this.getArguments().getSerializable(Constants.OPERATION_TYPE);
        this.user = (User) this.getArguments().getSerializable(Constants.USER);
        this.subDivisionName = this.getArguments().getString(Constants.SUBDIVISION_NAME);

        categoryAdapter = new CategoryAdapter(this);
        binding.fragmentCategoryRecycler.setAdapter(categoryAdapter);

       return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Category category) {
        showProgress(true);
        BaseFragment fragment;
        if (this.type == ViewType.FIXTURE_VIEW) {
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_pager_date);
        }else{
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_table_positions);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.OPERATION_TYPE,this.type);
        bundle.putInt(Constants.PARENT_ID,category.getId());
        fragment.setArguments(bundle);
    }

    public void onClickFavorite(Category category, Favorite favorite) {
        if (favorite != null){
            favoriteViewModel.deleteFavorite(favorite);
        }else {
            favoriteViewModel.newFavorite(category,user,type,subDivisionName);
        }
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() ->
                categoryViewModel.updateCategories(this.getArguments().getInt(Constants.PARENT_ID)));
    }

    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

}
