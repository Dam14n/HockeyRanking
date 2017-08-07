package com.wip.hockey.fragment.SubDivision;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;

import java.util.List;

public interface SubDivisionContract {

    interface View extends Lifecycle.View{
        void showProgress(boolean isVisible);
        void showMessage(String message);
        void hideLoading();
        void setSubDivisions(List<SubDivision> subDivisions);
    }

    interface ViewModel extends Lifecycle.ViewModel{
        void getSubDivisions();
        void setDivision(Division division);
    }
}
