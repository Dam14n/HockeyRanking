package com.wip.hockey.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewParent;

import com.wip.hockey.R;
import com.wip.hockey.databinding.FragmentListDateBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.ListDateFragment;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Date;

import java.util.List;

public class DateAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener{


    private static final String TAG = DateAdapter.class.getSimpleName();
    private final FragmentListDateBinding binding;
    private List<Date> dateList;

    public DateAdapter(ListDateFragment fragment, FragmentListDateBinding binding) {
        super(fragment.getFragmentManager());
        this.binding = binding;
    }

    @Override
    public Fragment getItem(int position) {
        Selected fragment = (Selected) HandlerFragment.getInstance().getFragment(R.id.fragment_match_recycler);
        Date currentObj = this.dateList.get(position);
        fragment.setSelectedFrom(currentObj);
        return (BaseFragment)fragment;
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
        Date currentObj = dateList.get(position);
        binding.toolbarDate.toolbarTitle.setText(String.valueOf(currentObj.getDateNumber()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
