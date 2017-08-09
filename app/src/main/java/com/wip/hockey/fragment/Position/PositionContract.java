package com.wip.hockey.fragment.Position;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Board;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Position;
import com.wip.hockey.model.SubDivision;

import java.util.List;

public interface PositionContract {

    interface View extends Lifecycle.View{
        void showProgress(boolean isVisible);
        void showMessage(String message);
        void hideLoading();
        void setPositions(List<Position> positions);
    }

    interface ViewModel extends Lifecycle.ViewModel{
        void getPositions();
        void setBoard(Board board);
    }
}
