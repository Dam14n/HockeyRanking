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
import com.wip.hockey.adapter.DateAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListDateBinding;
import com.wip.hockey.model.Date;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.viewModel.DateViewModel;
import com.wip.hockey.viewModel.factory.DateViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ListDateFragment extends BaseFragment
        implements Tageable {

    private final String TAG = ListDateFragment.class.toString();
    private DateAdapter dateAdapter;
    private FragmentListDateBinding binding;
    private DateViewModel dateViewModel;
    private ViewType type;

    @Inject
    DateViewModelFactory dateViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dateViewModel = ViewModelProviders.of(this,dateViewModelFactory).get(DateViewModel.class);
        this.type = (ViewType) this.getArguments().getSerializable(Constants.OPERATION_TYPE);
        dateViewModel.getUpdateStatus().observe(this, status -> {
            if (status == Status.ERROR || status == Status.SUCCESS){
                //TODO
            }
        });
        dateViewModel.init(this.getArguments().getInt(Constants.PARENT_ID));
        dateViewModel.getDates().observe(this, dates -> {
            dateAdapter.setDateList(dates);
            binding.fragmentPagerDate.setCurrentItem(getActualDate(dates),false);
        });
    }

    private int getActualDate(List<Date> dates) {
        for (Date date:dates) {
            if (date.isCurrentDate())
                return dates.indexOf(date);
        }
        return dates.size();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_date, container, false);
        dateAdapter = new DateAdapter(this);
        binding.fragmentPagerDate.setAdapter(dateAdapter);
        binding.fragmentPagerDate.addOnPageChangeListener(dateAdapter);

        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void setTitle(String title){
        binding.toolbarDate.toolbarTitle.setText(title);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
