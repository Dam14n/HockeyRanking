package com.wip.hockey.fragment.Position;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.databinding.FragmentTablePositionBinding;
import com.wip.hockey.databinding.ListItemDivisionBinding;
import com.wip.hockey.databinding.TableRowPositionBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.fragment.Tageable;
import com.wip.hockey.model.Board;
import com.wip.hockey.model.Position;
import com.wip.hockey.viewModel.PositionViewModel;

import java.util.List;

public class TablePositionFragment extends BaseFragment implements Tageable,Selected,PositionContract.View{

    private final String TAG = TablePositionFragment.class.toString();
    private FragmentTablePositionBinding binding;
    private PositionContract.ViewModel positionViewModel;
    private Board board;
    private ViewGroup mContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positionViewModel = ViewModelProviders.of(this).get(PositionViewModel.class);
        positionViewModel.setBoard(this.board);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_position, container, false);
        this.mContainer = container;
        setupRefreshLayout();

        return binding.getRoot();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> positionViewModel.getPositions());
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return this.positionViewModel;
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
    public void setPositions(List<Position> positions) {
        for (Position position : positions) {
            TableRowPositionBinding tableRowPositionBinding = DataBindingUtil.inflate(getLayoutInflater() , R.layout.table_row_position, (ViewGroup) this.binding.getRoot(), false);
            tableRowPositionBinding.setPosition(position);
            binding.fragmentTablePositions.addView(tableRowPositionBinding.getRoot());
        }
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.board = (Board) object;
    }
}
