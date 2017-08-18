package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
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
import com.wip.hockey.viewModel.CategoryViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListCategoryFragment extends BaseFragment implements Tageable{

    private final String TAG = ListCategoryFragment.class.toString();
    private CategoryAdapter categoryAdapter;
    private FragmentListCategoryBinding binding;
    private CategoryViewModel categoryViewModel;
    private ViewType type;
    private CategoryObserver observer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.setSubDivisionId(this.getArguments().getInt(Constants.PARENT_ID));
        this.type = (ViewType) this.getArguments().getSerializable(Constants.OPERATION_TYPE);
        
        setupRefreshLayout();
        subscribeUi(categoryViewModel);
    }

    private void subscribeUi(CategoryViewModel categoryViewModel) {
        categoryViewModel.getCategories().subscribe(observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_category, container, false);

        categoryAdapter = new CategoryAdapter(this);
        binding.fragmentCategoryRecycler.setAdapter(categoryAdapter);

        this.observer = new CategoryObserver();
        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Category category) {
        showProgress(true);
        BaseFragment fragment;
        if (this.type == ViewType.POSITIONS_VIEW) {
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_pager_date);
        }else{
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_board_recycler);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.OPERATION_TYPE,this.type);
        bundle.putInt(Constants.PARENT_ID,category.getId());
        fragment.setArguments(bundle);
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> categoryViewModel.getCategories().subscribe(observer));
    }

    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    private class CategoryObserver implements Observer<List<Category>> {

        @Override
        public void onSubscribe(Disposable d) {
            showMessage("Suscribe");
        }

        @Override
        public void onNext(List<Category> categories) {
            showMessage("Next");
            categoryAdapter.setCategoryList(categories);
            showProgress(false);
        }

        @Override
        public void onError(Throwable e) {
            showMessage("Error");
            showProgress(false);
            hideLoading();
        }

        @Override
        public void onComplete() {
            hideLoading();
        }
    }

}