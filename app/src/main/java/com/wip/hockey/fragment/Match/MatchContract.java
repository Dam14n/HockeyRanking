package com.wip.hockey.fragment.Match;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Match;

import java.util.List;

public interface MatchContract {

    interface View extends Lifecycle.View{
        void showProgress(boolean isVisible);
        void showMessage(String message);
        void hideLoading();
        void setMatches(List<Match> matches);
    }

    interface ViewModel extends Lifecycle.ViewModel{
        void getMatches();
        void setDate(Date date);
    }
}
