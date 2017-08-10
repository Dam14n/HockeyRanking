package com.wip.hockey.fragment.Category;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;

import java.util.List;

public interface CategoryContract {

    interface View extends Lifecycle.View{
        void showProgress(boolean isVisible);
        void showMessage(String message);
        void hideLoading();
        void setCategories(List<Category> categories);
    }

    interface ViewModel extends Lifecycle.ViewModel{
        void getCategories();
        void setSubDivision(SubDivision subDivision);
    }
}
