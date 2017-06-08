package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.CategoryAdapter;
import com.wip.hockey.adapter.DivisionAdapter;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Division;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {

    @BindView(R.id.fragment_category_recycler)
    RecyclerView recyclerView;
    private ArrayList<Category> content;

    public CategoryFragment(ArrayList<Category> content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);

        ButterKnife.bind(this,view);

        CategoryAdapter adapter = new CategoryAdapter(this.getContext(), content);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        return view;
    }


    protected int getLayoutResourceId() {
        return R.layout.fragment_list_category;
    }
}