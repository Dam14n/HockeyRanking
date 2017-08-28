package com.wip.hockey.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.app.Constants;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.NavigationDrawerItem;
import com.wip.hockey.model.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by djorda on 12/05/2017.
 */

public class NavigationDrawAdapter extends RecyclerView.Adapter<NavigationDrawAdapter.MyViewHolder> {

    private final User user;
    private List<NavigationDrawerItem> mDataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawAdapter(Context context, List<NavigationDrawerItem> mDataList, User user) {
        this.mDataList = mDataList;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.user = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NavigationDrawerItem current = mDataList.get(position);
        holder.imgIcon.setImageResource(current.getImageId());
        holder.title.setText(current.getTitle());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.USER,this.user);
        holder.itemView.setOnClickListener(v -> {
            switch (holder.title.getText().toString()){
                case "Inicio":
                    BaseFragment divisionFragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_division_recycler);
                    bundle.putSerializable(Constants.OPERATION_TYPE, ViewType.FIXTURE_VIEW);
                    divisionFragment.setArguments(bundle);
                    break;
                case "Favoritos":
                    Fragment favoriteFragment = HandlerFragment.getInstance().changeToFragment(R.id.fragment_favorite_fixture_recycler);
                    favoriteFragment.setArguments(bundle);
                    break;
                case "Posiciones":
                    BaseFragment tableFragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_division_recycler);
                    bundle.putSerializable(Constants.OPERATION_TYPE, ViewType.POSITIONS_VIEW);
                    tableFragment.setArguments(bundle);
                default:
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imgIcon = itemView.findViewById(R.id.imgIcon);
        }
    }
}
