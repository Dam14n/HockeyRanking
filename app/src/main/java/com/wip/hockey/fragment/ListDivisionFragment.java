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
import com.wip.hockey.adapter.DivisionAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListDivisionBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.User;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.viewModel.DivisionViewModel;
import com.wip.hockey.viewModel.factory.DivisionViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ListDivisionFragment extends BaseFragment implements Tageable {

    private final String TAG = ListDivisionFragment.class.toString();
    private DivisionAdapter divisionAdapter;
    private FragmentListDivisionBinding binding;
    private DivisionViewModel divisionViewModel;
    private ViewType type;
    private User user;

    @Inject
    DivisionViewModelFactory divisionViewModelFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_division, container, false);

        divisionAdapter = new DivisionAdapter(this);
        binding.fragmentDivisionRecycler.setAdapter(divisionAdapter);

        this.type = (ViewType)this.getArguments().getSerializable(Constants.OPERATION_TYPE);
        this.user = (User) this.getArguments().getSerializable(Constants.USER);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showProgress(true);
        divisionViewModel = ViewModelProviders.of(this, divisionViewModelFactory).get(DivisionViewModel.class);
        divisionViewModel.getUpdateStatus().observe(this, status -> {
            if (status == Status.ERROR || status == Status.SUCCESS){
                hideLoading();
                showProgress(false);
            }
        });
        divisionViewModel.init();
        divisionViewModel.getDivisions().observe(this, divisions -> {
            divisionAdapter.setDivisionList(divisions);
        });
        setupRefreshLayout();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> {
            divisionViewModel.updateDivisions();
        });
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Division division) {
        showProgress(true);
        BaseFragment fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_sub_division_recycler);        
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.PARENT_ID,division.getId());
        bundle.putSerializable(Constants.OPERATION_TYPE,this.type);
        bundle.putSerializable(Constants.USER,this.user);
        fragment.setArguments(bundle);
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
