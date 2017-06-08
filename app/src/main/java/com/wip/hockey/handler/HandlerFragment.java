package com.wip.hockey.handler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wip.hockey.R;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.CategoryFragment;
import com.wip.hockey.fragment.DivisionFragment;
import com.wip.hockey.fragment.SubDivisionFragment;
import com.wip.hockey.model.Division;

import java.util.ArrayList;

/**
 * Created by djorda on 08/06/2017.
 */

public class HandlerFragment {

    private Context context;
    private ArrayList mData;
    private boolean firstCall = true;

    public HandlerFragment(Context context) {
        this.context = context;
    }

    public void setFragment(int id,ArrayList data) {
        mData = data;
        BaseFragment fragment = getFragment(id);

        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment, fragment);
        if (firstCall) {
            firstCall = false;
        }else{
            fragmentTransaction.addToBackStack("id:" + id);
        }
        fragmentTransaction.commit();
    }

    public BaseFragment getFragment(int id) {
        BaseFragment fragment = null;
        switch (id){
            case R.id.fragment_division_recycler:
                fragment = new DivisionFragment();
                fragment.setContent(mData);
                break;
            case R.id.fragment_sub_division_recycler:
                fragment = new SubDivisionFragment();
                fragment.setContent(mData);
                break;
            case R.id.fragment_category_recycler:
                fragment = new CategoryFragment();
                fragment.setContent(mData);
                break;
            default:
                fragment = new DivisionFragment();
                fragment.setContent(mData);
        }
        return fragment;
    }
}
