package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Position;
import com.wip.hockey.model.Team;
import com.wip.hockey.repository.WebService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;

public class PositionViewModel extends ViewModel{

    private WebService webService;
    private int boardId;
    private int categoryId;

    public PositionViewModel() {
       // webService = WebService.getInstance();
    }


    public Call<List<Position>> getPositions() {
        return  webService.getPositionsByCategory(categoryId);
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setCategoryId(int categoryId){ this.categoryId = categoryId; }

    public void getTeam(List<Position> positions) {
       /* for (Position position : positions)
            webService.getTeam(position.getTeamId())
                    .subscribe(new TeamPositionObserver(position));*/
    }

    private class TeamPositionObserver implements Observer<Team> {
        private final Position position;

        public TeamPositionObserver(Position position) {
            this.position = position;
        }

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Team team) {
            if (team != null)
                position.setTeam(team);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {

        }
    }
}
