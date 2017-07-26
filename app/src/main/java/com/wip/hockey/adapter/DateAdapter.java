package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.ISelected;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.listeners.DatePagerListener;
import com.wip.hockey.model.Date;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateAdapter extends FragmentStatePagerAdapter implements DataListener {

    private DatePagerListener mListener;
    private MainActivity context;
    @BindView(R.id.toolbar_date)
    Toolbar toolbarDate;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.pager)
    ViewPager pager;
    private List<Date> mData;

    public DateAdapter(Context context, View view) {
        super(((MainActivity)context).getSupportFragmentManager());
        this.context = (MainActivity)context;
        ButterKnife.bind(this,view);
        this.mListener = new DatePagerListener();
    }

    @Override
    public Fragment getItem(int position) {
        context.showProgress(true);
        ISelected fragment = (ISelected) HandlerFragment.getInstance().getFragment(R.id.fragment_match_recycler);
        Date currentObj = this.mData.get(position);
        fragment.setParent(currentObj);
        mListener.setmData(mData);
        mListener.setToolbarTitle(toolbarTitle);
        pager.addOnPageChangeListener(mListener);
        return (BaseFragment)fragment;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public void dataHasChanged(List list) {
        this.mData = list;
        this.notifyDataSetChanged();
        if(list.isEmpty()){
            mListener.setmData(mData);
            mListener.setToolbarTitle(toolbarTitle);
            mListener.onNonPage();
            updateFinish();
        }
    }

    @Override
    public void updateFinish() {
        context.showProgress(false);
    }
}
