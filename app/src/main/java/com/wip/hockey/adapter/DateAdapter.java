package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.api.Api;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.MatchFragment;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Match;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateAdapter extends FragmentStatePagerAdapter {

    private MainActivity context;
    @BindView(R.id.toolbar_date)
    Toolbar toolbarDate;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.pager)
    ViewPager pager;
    private List<Date> mdata;

    public DateAdapter(Context context, List<Date> data, View view) {
        super(((MainActivity)context).getSupportFragmentManager());
        this.context = (MainActivity)context;
        this.mdata = data;
        ButterKnife.bind(this,view);
    }

    @Override
    public Fragment getItem(int position) {
        final BaseFragment fragment = HandlerFragment.getInstance().getFragment(R.id.fragment_match_recycler);
        final Date currentObj = this.mdata.get(position);
        context.showProgress(true);
        ServiceApi serviceApi = Api.getInstance();
        serviceApi.getMatchesByDate(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                fragment.setContent(response.body());
                MatchAdapter adapter = new MatchAdapter(fragment.getContext(), fragment.getContent());
                ((MatchFragment)fragment).setAdapter(adapter);
                Log.d(MainActivity.TAG,"se actualizo la info");
                context.showProgress(false);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        },currentObj.getId());
        Log.d(MainActivity.TAG,"cambia el id es: "+fragment.getId());
        return fragment;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        if(!mdata.isEmpty()) {
            Date currentObj = this.mdata.get(pager.getCurrentItem());
            toolbarTitle.setText("Date: " + currentObj.getDateNumber());
        }
    }

    @Override
    public int getCount() {
        return this.mdata.size();
    }
}
