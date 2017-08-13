package com.wip.hockey.fragment.Date;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DateAdapter;
import com.wip.hockey.databinding.FragmentListDateBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.fragment.Tageable;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.viewModel.DateViewModel;

import java.util.List;

public class ListDateFragment extends BaseFragment
        implements Selected,Tageable,DateContract.View, ViewPager.OnPageChangeListener {

    private final String TAG = ListDateFragment.class.toString();
    private DateAdapter dateAdapter;
    private FragmentListDateBinding binding;
    private Category category;
    private DateContract.ViewModel dateViewModel;
    private List<Date> dates;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dateViewModel = ViewModelProviders.of(this).get(DateViewModel.class);
        dateViewModel.setCategory(this.category);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_date, container, false);
        dateAdapter = new DateAdapter(this);
        binding.fragmentPagerDate.addOnPageChangeListener(this);
        binding.fragmentPagerDate.setAdapter(dateAdapter);
        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.category = (Category) object;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return this.dateViewModel;
    }

    @Override
    public void showMessage(String message) {
       // Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void setDates(List<Date> dates) {
        this.dates = dates;
        dateAdapter.setDateList(dates);
        binding.fragmentPagerDate.setCurrentItem(dates.size(),false);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Date date = this.dates.get(position);
        binding.toolbarDate.toolbarTitle.setText(String.valueOf(date.getDateNumber()));
        showProgress(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
