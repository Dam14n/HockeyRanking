package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DivisionAdapter;
import com.wip.hockey.model.Division;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DivisionFragment extends Fragment {

    @BindView(R.id.fragment_division_recycler)
    RecyclerView recyclerView;
    private ArrayList<Division> content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);

        ButterKnife.bind(this,view);

        content = Division.getData();

        DivisionAdapter adapter = new DivisionAdapter(this.getContext(), content);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        return view;
    }


    protected int getLayoutResourceId() {
        return R.layout.fragment_list_division;
    }
}
