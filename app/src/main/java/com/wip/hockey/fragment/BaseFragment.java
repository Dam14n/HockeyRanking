package com.wip.hockey.fragment;

import android.arch.lifecycle.LifecycleFragment;

import com.wip.hockey.app.MainActivity;

public abstract class BaseFragment extends LifecycleFragment implements Lifecycle.View{

    protected abstract Lifecycle.ViewModel getViewModel();

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().onViewResumed();
    }

    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onViewDetached();
    }

    public void showProgress(boolean isVisible) {
        if (this.isVisible()) {
            final MainActivity mainActivity = (MainActivity) this.getContext();
            mainActivity.showProgress(isVisible);
        }
    }

}
