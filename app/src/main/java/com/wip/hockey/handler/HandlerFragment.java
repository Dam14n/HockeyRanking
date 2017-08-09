package com.wip.hockey.handler;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.Board.ListBoardFragment;
import com.wip.hockey.fragment.Division.ListDivisionFragment;
import com.wip.hockey.fragment.FavoriteFragment;
import com.wip.hockey.fragment.Category.ListCategoryFragment;
import com.wip.hockey.fragment.Date.ListDateFragment;
import com.wip.hockey.fragment.Match.ListMatchFragment;
import com.wip.hockey.fragment.SubDivision.ListSubDivisionFragment;
import com.wip.hockey.fragment.Position.TablePositionFragment;
import com.wip.hockey.fragment.Tageable;


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
        Tageable fragment = this.getFragment(id);
        try {
            checkContext();
        } catch (ContextNotFoundException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!firstCall){
            fragmentTransaction.replace(R.id.fragment, (LifecycleFragment) fragment,fragment.getTAG());
            fragmentTransaction.addToBackStack("id: " + fragment.getTAG());
        }else{
            fragmentTransaction.replace(R.id.fragment, (LifecycleFragment) fragment, fragment.getTAG());
            firstCall = false;
        }
        fragmentTransaction.commit();
        return (LifecycleFragment) fragment;
    }

    public Tageable getFragment(int id) {
        Tageable fragment = null;
        switch (id){
            case R.id.fragment_division_recycler:
                fragment = new ListDivisionFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_sub_division_recycler:
                fragment = new ListSubDivisionFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_category_recycler:
                fragment = new ListCategoryFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_match_recycler:
                fragment = new ListMatchFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_favorite_recycler:
                fragment = new FavoriteFragment();
                Log.d(MainActivity.TAG,"La data es: ");
                break;
            case R.id.fragment_pager_date:
                fragment = new ListDateFragment();
                Log.d(MainActivity.TAG,"La data es: pager");
                break;
            case R.id.fragment_board_recycler:
                fragment = new ListBoardFragment();
                Log.d(MainActivity.TAG,"La data es: pager");
                break;
            case R.id.fragment_table_positions:
                fragment = new TablePositionFragment();
                Log.d(MainActivity.TAG,"La data es: pager");
                break;
            default:
                fragment = new ListDivisionFragment();
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
