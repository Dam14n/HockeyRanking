package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Configuration;
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
import com.wip.hockey.networking.Status;
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

    private int selectedPage;

    @Inject
    DateViewModelFactory dateViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            this.selectedPage = savedInstanceState.getInt(Constants.PAGE_POSITION);
        }
        dateViewModel = ViewModelProviders.of(this,dateViewModelFactory).get(DateViewModel.class);
        dateViewModel.getUpdateStatus().observe(this, status -> {
            if (status == Status.ERROR || status == Status.SUCCESS){
                //TODO
            }
        });
        dateViewModel.init(this.getArguments().getInt(Constants.PARENT_ID));
        dateViewModel.getDates().observe(this, dates -> {
            dateAdapter.setDateList(dates);
            binding.fragmentPagerDate.setCurrentItem(savedInstanceState != null ? this.selectedPage : getActualDate(dates),false);
        });
        dateViewModel.getCurrentPage().observe(this, currentPage -> {
            binding.toolbarDate.toolbarTitle.setText(String.valueOf(currentPage));
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

    public void setTitle(int title){
        dateViewModel.setCurrentPage(title);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constants.PAGE_POSITION,binding.fragmentPagerDate.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

}
