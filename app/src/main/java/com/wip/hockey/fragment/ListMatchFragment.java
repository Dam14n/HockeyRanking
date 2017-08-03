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
import com.wip.hockey.adapter.MatchAdapter;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.databinding.FragmentListMatchBinding;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Team;
import com.wip.hockey.viewModel.MatchViewModel;

import java.util.List;

public class ListMatchFragment extends BaseFragment implements Selected{

    private final String TAG = ListMatchFragment.class.toString();
    private MatchAdapter matchAdapter;
    private FragmentListMatchBinding binding;
    private Date parent;
    private MatchViewModel matchViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_match, container, false);

        matchAdapter = new MatchAdapter(this);

        binding.fragmentMatchRecycler.setAdapter(matchAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        matchViewModel.setDate(this.parent);
        observeViewModel(matchViewModel);
    }

    private void observeViewModel(final MatchViewModel viewModel) {
        // Update the list when the data changes
        final MainActivity mainActivity = (MainActivity) this.getContext();
        viewModel.getMatchListObservable().observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(@Nullable List<Match> matches) {
                mainActivity.showProgress(true);
                if (matches != null) {
                    matchAdapter.setMatchList(matches);
                    viewModel.updateTeams();
                }
                mainActivity.showProgress(false);
            }
        });
    }

    public String getTAG(){
        return TAG;
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.parent = (Date) object;
    }
}
