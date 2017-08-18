package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
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
import com.wip.hockey.viewModel.MatchViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListMatchFragment extends BaseFragment
        implements Tageable{

    private final String TAG = ListMatchFragment.class.toString();
    private MatchAdapter matchAdapter;
    private FragmentListMatchBinding binding;
    private MatchViewModel matchViewModel;
    private MatchObserver observer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        matchViewModel.setDateId(this.getArguments().getInt(Constants.PARENT_ID));

        setupRefreshLayout();
        subscribeUi(matchViewModel);
    }

    private void subscribeUi(MatchViewModel matchViewModel) {
        matchViewModel.getMatches().subscribe(observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_match, container, false);

        matchAdapter = new MatchAdapter(this);

        binding.fragmentMatchRecycler.setAdapter(matchAdapter);

        this.observer = new MatchObserver();

        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> matchViewModel.getMatches().subscribe(observer));
    }

    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    public void setMatches(List<Match> matches) {
        matchAdapter.setMatchList(matches);
    }

    private class MatchObserver implements Observer<List<Match>> {
        @Override
        public void onSubscribe(Disposable d) {
            showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Match> matches) {
            matchViewModel.getTeams(matches);
            showMessage("Next");
            setMatches(matches);
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
