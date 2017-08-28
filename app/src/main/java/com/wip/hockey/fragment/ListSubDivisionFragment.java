package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
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
import com.wip.hockey.viewModel.SubDivisionViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListSubDivisionFragment extends BaseFragment implements Tageable {

    private final String TAG = ListSubDivisionFragment.class.toString();
    private SubDivisionAdapter subDivisionAdapter;
    private FragmentListSubDivisionBinding binding;
    private SubDivisionViewModel subDivisionViewModel;
    private ViewType type;
    private SubDivisionObserver observer;
    private User user;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        subDivisionViewModel = ViewModelProviders.of(this).get(SubDivisionViewModel.class);
        subDivisionViewModel.setDivisionId(this.getArguments().getInt(Constants.PARENT_ID));

        this.type = (ViewType) this.getArguments().getSerializable(Constants.OPERATION_TYPE);
        this.user = (User) this.getArguments().getSerializable(Constants.USER);

        setupRefreshLayout();
        subscribeUi(subDivisionViewModel);
    }

    private void subscribeUi(SubDivisionViewModel subDivisionViewModel) {
        subDivisionViewModel.getSubDivisions().subscribe(observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_sub_division, container, false);

        subDivisionAdapter = new SubDivisionAdapter(this);
        binding.fragmentSubDivisionRecycler.setAdapter(subDivisionAdapter);

        this.observer = new SubDivisionObserver();

        return binding.getRoot();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> subDivisionViewModel.getSubDivisions().subscribe(observer));
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
        fragment.setArguments(bundle);
    }

    public void showMessage(String message) {
        //Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    private class SubDivisionObserver implements Observer<List<SubDivision>> {
        @Override
        public void onSubscribe(Disposable d) {
            showMessage("Subscribe");
        }

        @Override
        public void onNext(List<SubDivision> subDivisions) {
            showMessage("Next");
            subDivisionAdapter.setSubDivisionList(subDivisions);
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
