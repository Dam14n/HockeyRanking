package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.SubDivisionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DJORDA on 05/06/2017.
 */

public class SubDivisionFragment extends BaseFragment {

    @BindView(R.id.fragment_sub_division_recycler)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);

        ButterKnife.bind(this,view);

        SubDivisionAdapter adapter = new SubDivisionAdapter(this.getContext(), getContent());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        return view;
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_list_sub_division;
    }

}
