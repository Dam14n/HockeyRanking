package com.wip.hockey.fragment.Category;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.CategoryAdapter;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.databinding.FragmentListCategoryBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.fragment.Tageable;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.viewModel.CategoryViewModel;

import java.util.List;

public class ListCategoryFragment extends BaseFragment implements Selected,Tageable,CategoryContract.View{

    private final String TAG = ListCategoryFragment.class.toString();
    private CategoryAdapter categoryAdapter;
    private FragmentListCategoryBinding binding;
    private SubDivision subDivision;
    private CategoryContract.ViewModel categoryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.setSubDivision(this.subDivision);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_category, container, false);

        categoryAdapter = new CategoryAdapter(this);

        binding.fragmentCategoryRecycler.setAdapter(categoryAdapter);
        setupRefreshLayout();
        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Category category) {
        showProgress(true);
        if (this.getType() == ViewType.POSITIONS_VIEW) {
            Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_pager_date);
            selected.setSelectedFrom(category);
        }else{
            Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_board_recycler);
            selected.setSelectedFrom(category);
        }
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> categoryViewModel.getCategories());
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.subDivision = (SubDivision) object;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return this.categoryViewModel;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void setCategories(List<Category> categories) {
        categoryAdapter.setCategoryList(categories);
    }

}
