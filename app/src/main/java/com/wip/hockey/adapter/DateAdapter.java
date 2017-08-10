package com.wip.hockey.adapter;

import android.arch.lifecycle.LifecycleFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wip.hockey.R;
import com.wip.hockey.fragment.Date.ListDateFragment;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Date;

import java.util.List;

public class DateAdapter extends FragmentStatePagerAdapter {


    private static final String TAG = DateAdapter.class.getSimpleName();
    private List<Date> dateList;

    public DateAdapter(ListDateFragment fragment) {
        super(fragment.getFragmentManager());
    }

    @Override
    public Fragment getItem(int position) {
        Selected fragment = (Selected) HandlerFragment.getInstance().getFragment(R.id.fragment_match_recycler);
        Date currentObj = this.dateList.get(position);
        fragment.setSelectedFrom(currentObj);
        return (LifecycleFragment)fragment;
    }

    @Override
    public int getCount() {
        return dateList != null ? dateList.size() : 0;
    }


    public void setDateList(final List<Date> dateList) {
        this.dateList = dateList;
        notifyDataSetChanged();
    }
}
