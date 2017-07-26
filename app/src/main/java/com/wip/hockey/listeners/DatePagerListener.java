package com.wip.hockey.listeners;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.wip.hockey.model.Date;

import java.util.List;


public class DatePagerListener implements ViewPager.OnPageChangeListener {


    private List mData;
    private TextView toolbarTitle;

    public void setmData(List mData) {
        this.mData = mData;
    }

    public void setToolbarTitle(TextView toolbarTitle) {
        this.toolbarTitle = toolbarTitle;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Date currentObj = (Date) mData.get(position);
        toolbarTitle.setText("Date: " + currentObj.getDateNumber());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void onNonPage(){
        toolbarTitle.setText("Invalid Date");
    }
}
