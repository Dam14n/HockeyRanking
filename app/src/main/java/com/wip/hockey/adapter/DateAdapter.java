package com.wip.hockey.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.wip.hockey.R;
import com.wip.hockey.app.Constants;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.ListDateFragment;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Date;
import com.wip.hockey.viewModel.DateViewModel;

import java.util.List;

public class DateAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {


    private static final String TAG = DateAdapter.class.getSimpleName();
    private final ListDateFragment mFragment;
    private List<Date> dateList;

    public DateAdapter(ListDateFragment fragment) {
        super(fragment.getFragmentManager());
        this.mFragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = (BaseFragment) HandlerFragment.getInstance().getFragment(R.id.fragment_match_recycler);
        Date currentObj = this.dateList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.PARENT_ID,currentObj.getId());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return dateList != null ? dateList.size() : 0;
    }


    public void setDateList(final List<Date> dateList) {
        this.dateList = dateList;
        notifyDataSetChanged();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Date date = this.dateList.get(position);
        mFragment.setTitle(date.getDateNumber());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
