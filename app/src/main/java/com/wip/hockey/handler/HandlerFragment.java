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
import com.wip.hockey.fragment.DateFragment;
import com.wip.hockey.fragment.DivisionFragment;
import com.wip.hockey.fragment.FavoriteFragment;
import com.wip.hockey.fragment.MatchFragment;
import com.wip.hockey.fragment.SubDivisionFragment;
import com.wip.hockey.model.IIdentificable;

import java.util.HashMap;
import java.util.List;

/**
 * Created by djorda on 08/06/2017.
 */

public class HandlerFragment {

    private FragmentActivity context;
    private List mData;
    private boolean firstCall = true;
    private BaseFragment fragment;
    private HashMap<Integer,BaseFragment> listFragments;
    private BaseFragment lastFragment;

    public HandlerFragment(FragmentActivity context) {
        this.context = context;
        this.listFragments = new HashMap();
    }

    public void setFragment(int id,List data) {
        mData = data;

        fragment = getFragment(id);
        Log.d(MainActivity.TAG,"se seteo el fragment");
        this.setLastFragment(fragment);
        this.setDataFragment();
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
            case R.id.pager:
                fragment = new DateFragment();
                Log.d(MainActivity.TAG,"La data es: pager");
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
        this.fragment = getActualFragment();
    }

    public void updateFragment() {
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(fragment);
        fragmentTransaction.attach(fragment);
        fragmentTransaction.commit();
    }

    public BaseFragment getActualFragment(){
        for (BaseFragment fragment: listFragments.values()) {
            if(fragment!= null && fragment.isVisible()){
                return fragment;
            }
        }
        return null;
    }

    public void setLastFragment(BaseFragment fragment){
        this.lastFragment = fragment;
    }

    public BaseFragment getLastFragment() {
        return lastFragment;
    }
}
