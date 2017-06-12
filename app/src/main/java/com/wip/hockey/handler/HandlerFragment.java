package com.wip.hockey.handler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.CategoryFragment;
import com.wip.hockey.fragment.DivisionFragment;
import com.wip.hockey.fragment.FavoriteFragment;
import com.wip.hockey.fragment.MatchFragment;
import com.wip.hockey.fragment.SubDivisionFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by djorda on 08/06/2017.
 */

public class HandlerFragment {

    private FragmentActivity context;
    private ArrayList mData;
    private boolean firstCall = true;
    private BaseFragment fragment;
    private HashMap<Integer,BaseFragment> listFragments;

    public HandlerFragment(FragmentActivity context) {
        this.context = context;
        this.listFragments = new HashMap();
    }

    public void setFragment(int id,ArrayList data) {
        mData = data;

        fragment = getFragment(id);
        setDataFragment();
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!firstCall){
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack("id:" + id);
        }else{
            fragmentTransaction.replace(R.id.fragment, fragment);
            firstCall = false;
        }
        fragmentTransaction.commit();
    }

    private void setDataFragment() {
        this.fragment.setContent(mData);
    }

    public BaseFragment getFragment(int id) {
        if(listFragments.containsKey(id)){
            return listFragments.get(id);
        }
        BaseFragment fragment = null;
        switch (id){
            case R.id.fragment_division_recycler:
                fragment = new DivisionFragment();
                Log.d(MainActivity.TAG,"La data es: "+mData);
                break;
            case R.id.fragment_sub_division_recycler:
                fragment = new SubDivisionFragment();
                Log.d(MainActivity.TAG,"La data es: "+mData);
                break;
            case R.id.fragment_category_recycler:
                fragment = new CategoryFragment();
                Log.d(MainActivity.TAG,"La data es: "+mData);
                break;
            case R.id.fragment_match_recycler:
                fragment = new MatchFragment();
                Log.d(MainActivity.TAG,"La data es: "+mData);
                break;
            case R.id.fragment_favorite_recycler:
                fragment = new FavoriteFragment();
                Log.d(MainActivity.TAG,"La data es: "+mData);
                break;
            default:
                fragment = new DivisionFragment();
                Log.d(MainActivity.TAG,"La data es: "+mData);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragment.setArguments(bundle);
        listFragments.put(id,fragment);
        return fragment;
    }

    public void onBackPressed() {
        for (BaseFragment fragment: listFragments.values()) {
            if(fragment!= null && fragment.isVisible()){
                this.fragment = fragment;
            }
        }
    }

    public void updateFragment() {
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(fragment);
        fragmentTransaction.attach(fragment);
        fragmentTransaction.commit();
    }
}
