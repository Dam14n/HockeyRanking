package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
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
import com.wip.hockey.viewModel.PositionViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TablePositionFragment extends BaseFragment implements Tageable{

    private final String TAG = TablePositionFragment.class.toString();
    private FragmentTablePositionBinding binding;
    private PositionViewModel positionViewModel;
    private List<View> tableRows = new ArrayList();
    private PositionObserver observer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        positionViewModel = ViewModelProviders.of(this).get(PositionViewModel.class);
        positionViewModel.setCategoryId(this.getArguments().getInt(Constants.PARENT_ID));

        setupRefreshLayout();
        subscribeUi(positionViewModel);
    }

    private void subscribeUi(PositionViewModel positionViewModel) {
        positionViewModel.getPositions().subscribe(observer);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_position, container, false);

        this.observer = new PositionObserver();
        return binding.getRoot();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> positionViewModel.getPositions().subscribe(observer));
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void showMessage(String message) {
       // if (this.isVisible())
           // Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
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
        for (View view : this.tableRows ) {
            binding.fragmentTablePositions.removeView(view);
        }
        this.tableRows.clear();
    }

    private void addPositionsToTable(List<Position> positions){
        for (Position position : positions) {
            TableRowPositionBinding tableRowPositionBinding = DataBindingUtil.inflate(getLayoutInflater() , R.layout.table_row_position, (ViewGroup) this.binding.getRoot(), false);
            tableRowPositionBinding.setPosition(position);
            this.tableRows.add(tableRowPositionBinding.getRoot());
            binding.fragmentTablePositions.addView(tableRowPositionBinding.getRoot());
        }
    }

    private class PositionObserver implements Observer<List<Position>> {

        @Override
        public void onSubscribe(Disposable d) {
            showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Position> positions) {
            positionViewModel.getTeam(positions);
            showMessage("Next");
            setPositions(positions);
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
