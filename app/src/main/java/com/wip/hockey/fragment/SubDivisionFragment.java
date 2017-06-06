package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * Created by DJORDA on 05/06/2017.
 */

public class SubDivisionFragment extends Fragment {

    private TextView statusBar;
    private AndroidTreeView tView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
}
