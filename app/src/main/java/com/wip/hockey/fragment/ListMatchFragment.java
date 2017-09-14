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
import com.wip.hockey.adapter.MatchAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListMatchBinding;
import com.wip.hockey.model.Match;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.viewModel.MatchViewModel;
import com.wip.hockey.viewModel.factory.MatchViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ListMatchFragment extends BaseFragment
        implements Tageable {

    private final String TAG = ListMatchFragment.class.toString();
    private MatchAdapter matchAdapter;
    private FragmentListMatchBinding binding;
    private MatchViewModel matchViewModel;

    @Inject
    MatchViewModelFactory matchViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        matchViewModel = ViewModelProviders.of(this,matchViewModelFactory).get(MatchViewModel.class);
        matchViewModel.getUpdateStatus().observe(this, status -> {
            if (status == Status.ERROR || status == Status.SUCCESS){
                hideLoading();
                showProgress(false);
            }
        });
        matchViewModel.init(this.getArguments().getInt(Constants.PARENT_ID));
        matchViewModel.getMatchs().observe(this, matches -> {
            matchAdapter.setMatchList(matches);
        });

        setupRefreshLayout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_match, container, false);

        matchAdapter = new MatchAdapter(this);

        binding.fragmentMatchRecycler.setAdapter(matchAdapter);

        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() ->
                matchViewModel.updateMatches(this.getArguments().getInt(Constants.PARENT_ID)));
    }


    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    public void setMatches(List<Match> matches) {
        matchAdapter.setMatchList(matches);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


}
