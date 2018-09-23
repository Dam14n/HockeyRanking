package com.wip.hockey.fragment;

import android.support.v4.app.Fragment;
import com.wip.hockey.app.MainActivity;

public abstract class BaseFragment extends Fragment {

    public void showProgress(boolean isVisible) {
        if (this.isVisible()) {
            final MainActivity mainActivity = (MainActivity) this.getContext();
            mainActivity.showProgress(isVisible);
        }
    }
}
