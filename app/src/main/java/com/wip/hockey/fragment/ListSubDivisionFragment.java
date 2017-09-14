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
import com.wip.hockey.adapter.SubDivisionAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListSubDivisionBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.User;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.viewModel.SubDivisionViewModel;
import com.wip.hockey.viewModel.factory.SubDivisionViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ListSubDivisionFragment extends BaseFragment implements Tageable {

    private final String TAG = ListSubDivisionFragment.class.toString();
    private SubDivisionAdapter subDivisionAdapter;
    private FragmentListSubDivisionBinding binding;
    private SubDivisionViewModel subDivisionViewModel;
    private ViewType type;
    private User user;

    @Inject
    SubDivisionViewModelFactory subDivisionViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showProgress(true);
        subDivisionViewModel = ViewModelProviders.of(this, subDivisionViewModelFactory).get(SubDivisionViewModel.class);
        subDivisionViewModel.getUpdateStatus().observe(this, status -> {
            if (status == Status.ERROR || status == Status.SUCCESS){
                hideLoading();
                showProgress(false);
            }
        });
        subDivisionViewModel.init(this.getArguments().getInt(Constants.PARENT_ID));
        subDivisionViewModel.getSubDivisions().observe(this, subDivisions -> {
            subDivisionAdapter.setSubDivisionList(subDivisions);
        });
        this.type = (ViewType) this.getArguments().getSerializable(Constants.OPERATION_TYPE);
        this.user = (User) this.getArguments().getSerializable(Constants.USER);

        setupRefreshLayout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_sub_division, container, false);

        subDivisionAdapter = new SubDivisionAdapter(this);
        binding.fragmentSubDivisionRecycler.setAdapter(subDivisionAdapter);

        return binding.getRoot();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() ->
            subDivisionViewModel.updateSubDivisions(this.getArguments().getInt(Constants.PARENT_ID)));
    }


    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(SubDivision subDivision) {
        showProgress(true);
        BaseFragment fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_category_recycler);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.PARENT_ID,subDivision.getId());
        bundle.putSerializable(Constants.USER,this.user);
        bundle.putSerializable(Constants.OPERATION_TYPE,this.type);
        bundle.putString(Constants.SUBDIVISION_NAME,subDivision.getName());
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
