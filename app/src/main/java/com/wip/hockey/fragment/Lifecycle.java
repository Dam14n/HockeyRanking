package com.wip.hockey.fragment;

import android.support.annotation.NonNull;

public interface Lifecycle {

    interface View {
        ViewType getType();
        void setType(ViewType type);
    }

    interface ViewModel {

        void onViewResumed();
        void onViewAttached(@NonNull Lifecycle.View viewCallback);
        void onViewDetached();
    }
}
