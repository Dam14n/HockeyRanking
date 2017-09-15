package com.wip.hockey.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.NavigationDrawAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentNavigationDrawerBinding;
import com.wip.hockey.model.NavigationDrawerItem;
import com.wip.hockey.model.User;

public class NavigationDrawerFragment extends BaseFragment{

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private FragmentNavigationDrawerBinding binding;
    private NavigationDrawAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation_drawer, container, false);
        
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        User user = (User) this.getArguments().getSerializable(Constants.USER);
        adapter = new NavigationDrawAdapter(NavigationDrawerItem.getData(),user);
        binding.drawerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.drawerList.setAdapter(adapter);
        adapter.setLayout(mDrawerLayout);
    }

    public void setUpDrawer(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar){

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerLayout.post(() -> mDrawerToggle.syncState());

    }


}
