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
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentTablePositionBinding;
import com.wip.hockey.databinding.TableRowPositionBinding;
import com.wip.hockey.model.Position;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.viewModel.PositionViewModel;
import com.wip.hockey.viewModel.factory.PositionViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class TablePositionFragment extends BaseFragment implements Tageable {

    private final String TAG = TablePositionFragment.class.toString();
    private FragmentTablePositionBinding binding;
    private PositionViewModel positionViewModel;
    private List<View> tableRows = new ArrayList();

    @Inject
    PositionViewModelFactory positionViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        positionViewModel = ViewModelProviders.of(this, positionViewModelFactory).get(PositionViewModel.class);
        positionViewModel.getUpdateStatus().observe(this, status -> {
            if (status == Status.ERROR || status == Status.SUCCESS) {
                hideLoading();
                showProgress(false);
            }
        });
        positionViewModel.init(this.getArguments().getInt(Constants.PARENT_ID));
        positionViewModel.getPositions().observe(this, positions -> {
            setPositions(positions);
        });

        setupRefreshLayout();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_position, container, false);

        return binding.getRoot();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() ->
                positionViewModel.updatePositions(this.getArguments().getInt(Constants.PARENT_ID)));
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    public void setPositions(List<Position> positions) {
        this.cleanTable();
        this.addPositionsToTable(positions);
    }

    private void cleanTable() {
        for (View view : this.tableRows) {
            binding.fragmentTablePositions.removeView(view);
        }
        this.tableRows.clear();
    }

    private void addPositionsToTable(List<Position> positions) {
        for (Position position : positions) {
            TableRowPositionBinding tableRowPositionBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.table_row_position, (ViewGroup) this.binding.getRoot(), false);
            tableRowPositionBinding.setPosition(position);
            this.tableRows.add(tableRowPositionBinding.getRoot());
            binding.fragmentTablePositions.addView(tableRowPositionBinding.getRoot());
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
