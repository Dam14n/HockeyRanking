package com.wip.hockey.fragment;

import android.arch.lifecycle.LifecycleFragment;

import com.wip.hockey.app.MainActivity;

public abstract class BaseFragment extends LifecycleFragment {

    public void showProgress(boolean isVisible) {
        if (this.isVisible()) {
            final MainActivity mainActivity = (MainActivity) this.getContext();
            mainActivity.showProgress(isVisible);
        }
    }
}
