package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DateAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by djorda on 10/07/2017.
 */

public class DateFragment extends BaseFragment {

    public static final String ARG_OBJECT = "object";

    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);

        ButterKnife.bind(this,view);

        DateAdapter adapter = new DateAdapter(this.getFragmentManager(),this.getContent(),view);
        pager.setAdapter(adapter);


        return view;
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_dates;
    }

}
