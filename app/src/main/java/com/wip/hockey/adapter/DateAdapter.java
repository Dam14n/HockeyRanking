package com.wip.hockey.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.api.ApiRealState;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.MatchFragment;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Match;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateAdapter extends FragmentStatePagerAdapter {

    @BindView(R.id.toolbar_date)
    Toolbar toolbarDate;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private List<Date> mdata;

    public DateAdapter(FragmentManager fm, List<Date> data, View view) {
        super(fm);
        this.mdata = data;
        ButterKnife.bind(this,view);
        toolbarTitle.setText("Date: 1");
    }

    @Override
    public Fragment getItem(int position) {
        final BaseFragment fragment = new MatchFragment();
        final Date currentObj = this.mdata.get(position);
        ServiceApi serviceApi = ApiRealState.getInstance();
        serviceApi.getMatchesByDate(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                fragment.setContent(response.body());
                MatchAdapter adapter = new MatchAdapter(fragment.getContext(), fragment.getContent());
                ((MatchFragment)fragment).setAdapter(adapter);
                toolbarTitle.setText("Date: "+currentObj.getDateNumber());
                Log.d(MainActivity.TAG,"se actualizo la info");
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        },currentObj.getId());
        Log.d(MainActivity.TAG,"cambia el id es: "+mdata.get(position).getId());
        return fragment;
    }

    @Override
    public int getCount() {
        return this.mdata.size();
    }
}
