package com.wip.hockey.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.wip.hockey.interfaces.Listable;

import java.util.ArrayList;

/**
 * Created by djorda on 08/06/2017.
 */

public class BaseFragment extends Fragment {


    private ArrayList content;

    public ArrayList getContent(){
        return this.content;
    }

    public void setContent(ArrayList content){
        this.content = content;
    };

}
