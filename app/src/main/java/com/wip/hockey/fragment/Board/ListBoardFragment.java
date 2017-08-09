package com.wip.hockey.fragment.Board;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.BoardAdapter;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.databinding.FragmentListBoardBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.fragment.Tageable;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Board;
import com.wip.hockey.viewModel.BoardViewModel;

import java.util.List;

public class ListBoardFragment extends BaseFragment implements Tageable,BoardContract.View{

    private final String TAG = ListBoardFragment.class.toString();
    private BoardAdapter boardAdapter;
    private FragmentListBoardBinding binding;
    private BoardContract.ViewModel boardViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boardViewModel = ViewModelProviders.of(this).get(BoardViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_board, container, false);

        boardAdapter = new BoardAdapter(this);

        binding.fragmentBoardRecycler.setAdapter(boardAdapter);
        setupRefreshLayout();
        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Board board) {
        final MainActivity mainActivity = (MainActivity) this.getContext();
        mainActivity.showProgress(true);
        Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_table_positions);
        selected.setSelectedFrom(board);
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> boardViewModel.getBoards());
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return this.boardViewModel;
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
    public void setBoards(List<Board> boards) {
        this.boardAdapter.setBoardList(boards);
    }

}
