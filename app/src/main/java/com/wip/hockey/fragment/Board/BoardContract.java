package com.wip.hockey.fragment.Board;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Board;
import com.wip.hockey.model.Category;

import java.util.List;

public interface BoardContract {

    interface View extends Lifecycle.View{
        void showProgress(boolean isVisible);
        void showMessage(String message);
        void hideLoading();
        void setBoards(List<Board> boards);
    }

    interface ViewModel extends Lifecycle.ViewModel{
        void getBoards();
        void setCategory(Category category);
    }
}
