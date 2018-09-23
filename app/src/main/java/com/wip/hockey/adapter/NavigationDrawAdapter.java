package com.wip.hockey.adapter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.NavDrawerListItemBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.NavigationDrawerItem;
import com.wip.hockey.model.User;

import java.util.Collections;
import java.util.List;

public class NavigationDrawAdapter extends RecyclerView.Adapter<NavigationDrawAdapter.MyViewHolder>{

    private final User user;
    private List<NavigationDrawerItem> mDataList = Collections.emptyList();
    private DrawerLayout mDrawerLayout;

    public NavigationDrawAdapter(List<NavigationDrawerItem> mDataList, User user) {
        this.mDataList = mDataList;
        this.user = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NavDrawerListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.nav_drawer_list_item,
                        parent, false);
        return new NavigationDrawAdapter.MyViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NavigationDrawerItem current = mDataList.get(position);
        holder.binding.imgIcon.setImageResource(current.getImageId());
        holder.binding.listItemTitle.setText(current.getTitle());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.USER,this.user);
        holder.itemView.setOnClickListener(v -> {
            switch (holder.binding.listItemTitle.getText().toString()){
                case "Fixtures":
                    BaseFragment divisionFragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_division_recycler);
                    bundle.putSerializable(Constants.OPERATION_TYPE, ViewType.FIXTURE_VIEW);
                    divisionFragment.setArguments(bundle);
                    break;
                case "Favorites":
                    Fragment favoriteFragment = HandlerFragment.getInstance().changeToFragment(R.id.fragment_favorite_fixture_recycler);
                    favoriteFragment.setArguments(bundle);
                    break;
                case "Positions":
                    BaseFragment tableFragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_division_recycler);
                    bundle.putSerializable(Constants.OPERATION_TYPE, ViewType.POSITIONS_VIEW);
                    tableFragment.setArguments(bundle);
                default:
                    break;
            }
            mDrawerLayout.closeDrawers();
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setLayout(DrawerLayout drawerLayout) {
        this.mDrawerLayout = drawerLayout;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final NavDrawerListItemBinding binding;

        public MyViewHolder(NavDrawerListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
