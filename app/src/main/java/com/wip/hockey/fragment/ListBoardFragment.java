package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.BoardAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListBoardBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Board;
import com.wip.hockey.viewModel.BoardViewModel;

public class ListBoardFragment extends BaseFragment implements Tageable{

    private final String TAG = ListBoardFragment.class.toString();
    private BoardAdapter boardAdapter;
    private FragmentListBoardBinding binding;
    private BoardViewModel boardViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        boardViewModel = ViewModelProviders.of(this).get(BoardViewModel.class);
        boardViewModel.setCategoryId(this.getArguments().getInt(Constants.PARENT_ID));

        setupRefreshLayout();
        subscribeUi(boardViewModel);
    }

    private void subscribeUi(BoardViewModel boardViewModel) {
       // boardViewModel.getBoards().subscribe(observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_board, container, false);

        boardAdapter = new BoardAdapter(this);

        binding.fragmentBoardRecycler.setAdapter(boardAdapter);

        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Board board) {
        showProgress(true);
        BaseFragment fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_table_positions);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.PARENT_ID,board.getId());
        fragment.setArguments(bundle);
    }

    private void setupRefreshLayout() {
        //binding.swipeRefresh.setOnRefreshListener(() -> boardViewModel.getBoards().subscribe(observer));
    }

    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }
}
