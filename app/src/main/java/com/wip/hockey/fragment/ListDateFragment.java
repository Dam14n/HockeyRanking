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
import com.wip.hockey.adapter.DateAdapter;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.databinding.FragmentListDateBinding;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.viewModel.DateViewModel;

import java.util.List;

public class ListDateFragment extends BaseFragment implements Selected {

    private final String TAG = ListDateFragment.class.toString();
    private DateAdapter dateAdapter;
    private FragmentListDateBinding binding;
    private Category parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_date, container, false);
        dateAdapter = new DateAdapter(this,binding);
        binding.fragmentPagerDate.setAdapter(dateAdapter);
        binding.fragmentPagerDate.addOnPageChangeListener(dateAdapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DateViewModel dateViewModel = ViewModelProviders.of(this).get(DateViewModel.class);
        dateViewModel.setCategory(this.parent);
        observeViewModel(dateViewModel);
    }

    private void observeViewModel(final DateViewModel viewModel) {
        // Update the list when the data changes
        final MainActivity mainActivity = (MainActivity) this.getContext();
        viewModel.getDateListObservable().observe(this, new Observer<List<Date>>() {
            @Override
            public void onChanged(@Nullable List<Date> dates) {
                mainActivity.showProgress(true);
                if (dates != null) {
                    dateAdapter.setDateList(dates);
                    binding.fragmentPagerDate.setCurrentItem(dates.size(),false);
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
        this.parent = (Category) object;
    }
}
