package com.wip.hockey.fragment.Date;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;

import java.util.List;

public interface DateContract {

    interface View extends Lifecycle.View{
        void showProgress(boolean isVisible);
        void showMessage(String message);
        void hideLoading();
        void setDates(List<Date> dates);
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void getDates();
        void setCategory(Category category);
    }
}
