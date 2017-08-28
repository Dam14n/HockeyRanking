package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
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
import com.wip.hockey.viewModel.DivisionViewModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ListDivisionFragment extends BaseFragment implements Tageable {

    private final String TAG = ListDivisionFragment.class.toString();
    private DivisionAdapter divisionAdapter;
    private FragmentListDivisionBinding binding;
    private DivisionViewModel divisionViewModel;
    private ViewType type;
    private DivisionsObserver observer;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_division, container, false);

        divisionAdapter = new DivisionAdapter(this);
        binding.fragmentDivisionRecycler.setAdapter(divisionAdapter);

        this.type = (ViewType)this.getArguments().getSerializable(Constants.OPERATION_TYPE);
        this.user = (User) this.getArguments().getSerializable(Constants.USER);
        this.observer = new DivisionsObserver();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        divisionViewModel = ViewModelProviders.of(this).get(DivisionViewModel.class);

        setupRefreshLayout();
        subscribeUi(divisionViewModel);
    }

    private void subscribeUi(DivisionViewModel divisionViewModel) {
        divisionViewModel.getDivisions().subscribe(observer);
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> divisionViewModel.getDivisions().subscribe(observer));
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

    public void showMessage(String message) {
        //Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    private class DivisionsObserver implements io.reactivex.Observer<List<Division>> {
        @Override
        public void onSubscribe(Disposable d) {
            showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Division> divisions) {
            showMessage("Next");
            divisionAdapter.setDivisionList(divisions);
            showProgress(false);
            hideLoading();
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
