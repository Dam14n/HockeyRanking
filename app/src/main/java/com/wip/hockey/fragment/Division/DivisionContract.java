package com.wip.hockey.fragment.Division;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Division;

import java.util.List;

public interface DivisionContract {

    interface View extends Lifecycle.View{
        void showProgress(boolean isVisible);
        void showMessage(String message);
        void hideLoading();
        void setDivisions(List<Division> divisions);
    }

    interface ViewModel extends Lifecycle.ViewModel{
        void getDivisions();
    }
}
