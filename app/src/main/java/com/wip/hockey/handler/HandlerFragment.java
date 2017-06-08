package com.wip.hockey.handler;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wip.hockey.R;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.CategoryFragment;
import com.wip.hockey.fragment.DivisionFragment;
import com.wip.hockey.fragment.FavoriteFragment;
import com.wip.hockey.fragment.MatchFragment;
import com.wip.hockey.fragment.SubDivisionFragment;

import java.util.ArrayList;

/**
 * Created by djorda on 08/06/2017.
 */

public class HandlerFragment {

    private FragmentActivity context;
    private ArrayList mData;
    private int lastId;
    private boolean firstCall = true;

    public HandlerFragment(FragmentActivity context) {
        this.context = context;
    }

    public void setFragment(int id,ArrayList data) {
        mData = data;

        BaseFragment fragment = getFragment(id);

        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (lastId != id) {
            if (!firstCall){
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack("id:" + id);
            }else{
                fragmentTransaction.replace(R.id.fragment, fragment);
                firstCall = false;
            }
            lastId = id;
            fragmentTransaction.commit();
        }

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
            case R.id.fragment_match_recycler:
                fragment = new MatchFragment();
                fragment.setContent(mData);
                break;
            case R.id.fragment_favorite_recycler:
                fragment = new FavoriteFragment();
                fragment.setContent(mData);
                break;
            default:
                fragment = new DivisionFragment();
                fragment.setContent(mData);
        }
        return fragment;
    }
}
