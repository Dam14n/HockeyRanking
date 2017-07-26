package com.wip.hockey.handler;

import android.content.Context;
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

/**
 * Created by djorda on 08/06/2017.
 */

public class HandlerFragment {

    private FragmentActivity context;
    private static HandlerFragment handlerFragment = new HandlerFragment();
    private boolean firstCall = true;

    private HandlerFragment() {
    }

    public static HandlerFragment getInstance(){
        return handlerFragment;
    }

    public void setContext(Context context){
        this.context = (FragmentActivity) context;
    }

    public Fragment changeToFragment(int id) {
        BaseFragment fragment = this.getFragment(id);
        try {
            checkContext();
        } catch (ContextNotFoundException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!firstCall){
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack("id: " + fragment.getId());
        }else{
            fragmentTransaction.replace(R.id.fragment, fragment);
            firstCall = false;
        }
        fragmentTransaction.commit();
        return fragment;
    }

    public BaseFragment getFragment(int id) {
        BaseFragment fragment = null;
        switch (id){
            case R.id.fragment_division_recycler:
                fragment = new DivisionFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_sub_division_recycler:
                fragment = new SubDivisionFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_category_recycler:
                fragment = new CategoryFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_match_recycler:
                fragment = new MatchFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_favorite_recycler:
                fragment = new FavoriteFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.pager:
                fragment = new DateFragment();
                Log.d(MainActivity.TAG,"La data es: pager");
                break;
            default:
                fragment = new DivisionFragment();
                Log.d(MainActivity.TAG,"La data es: ");
        }
        return fragment;
    }


    private void checkContext() throws ContextNotFoundException {
        if (context == null){
            throw new ContextNotFoundException();
        }
    }
}
