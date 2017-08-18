package com.wip.hockey.fragment;

import android.arch.lifecycle.LifecycleFragment;

import com.wip.hockey.app.MainActivity;

public abstract class BaseFragment extends LifecycleFragment {

    public void showMessage(String message) {
        /*if (this.isVisible())
            Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();*/
    }

    public void showProgress(boolean isVisible) {
        if (this.isVisible()) {
            final MainActivity mainActivity = (MainActivity) this.getContext();
            mainActivity.showProgress(isVisible);
        }
    }
}
