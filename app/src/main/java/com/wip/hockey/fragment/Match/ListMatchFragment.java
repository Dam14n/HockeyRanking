package com.wip.hockey.fragment.Match;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.MatchAdapter;
import com.wip.hockey.databinding.FragmentListMatchBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.fragment.Tageable;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Match;
import com.wip.hockey.viewModel.MatchViewModel;

import java.util.List;

public class ListMatchFragment extends BaseFragment
        implements Selected, Tageable,MatchContract.View {

    private final String TAG = ListMatchFragment.class.toString();
    private MatchAdapter matchAdapter;
    private FragmentListMatchBinding binding;
    private Date date;
    private MatchContract.ViewModel matchViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        matchViewModel.setDate(this.date);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_match, container, false);

        matchAdapter = new MatchAdapter(this);

        binding.fragmentMatchRecycler.setAdapter(matchAdapter);
        setupRefreshLayout();
        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.date = (Date) object;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return matchViewModel;
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> matchViewModel.getMatches());
    }

    @Override
    public void showMessage(String message) {
        if (this.isVisible())
            Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void setMatches(List<Match> matches) {
        matchAdapter.setMatchList(matches);
    }
}
