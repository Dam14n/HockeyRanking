package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Position;
import com.wip.hockey.model.Team;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PositionViewModel extends ViewModel{

    private Repository repository;
    private int boardId;

    public PositionViewModel() {
        repository = Repository.getInstance();
    }


    public Observable<List<Position>> getPositions() {
        return repository.getPositionsByBoard(this.boardId);
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void getTeam(List<Position> positions) {
        for (Position position : positions)
            repository.getTeam(position.getTeamId())
                    .subscribe(new TeamPositionObserver(position));
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
