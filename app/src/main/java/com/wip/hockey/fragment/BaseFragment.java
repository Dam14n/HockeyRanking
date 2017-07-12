package com.wip.hockey.fragment;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by djorda on 08/06/2017.
 */

public abstract class BaseFragment extends Fragment {
    abstract int getLayoutResourceId();

    private List content;

    public List getContent(){
        return this.content;
    }

    public void setContent(List content){
        this.content = content;
    };
}
