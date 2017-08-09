package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Position.PositionContract;
import com.wip.hockey.model.Board;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Position;
import com.wip.hockey.model.Team;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PositionViewModel extends ViewModel implements PositionContract.ViewModel{

    private Repository repository;
    private PositionContract.View viewCallback;
    private Board board;

    public PositionViewModel() {
        repository = Repository.getInstance();
    }


    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.viewCallback = (PositionContract.View) viewCallback;
        getPositions();
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void getPositions() {
        repository.getPositionsByBoard(this.board.getId())
                .subscribe(new PositionObserver());
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    private class PositionObserver implements Observer<List<Position>> {

        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Suscribe");
        }

        @Override
        public void onNext(List<Position> positions) {
            getTeam(positions);
            viewCallback.showMessage("Next");
            viewCallback.setPositions(positions);
            viewCallback.showProgress(false);
        }

        @Override
        public void onError(Throwable e) {
            viewCallback.showMessage("Error");
            viewCallback.showProgress(false);
            viewCallback.hideLoading();
        }

        @Override
        public void onComplete() {
            viewCallback.hideLoading();
        }
    }

    private void getTeam(List<Position> positions) {
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
            viewCallback.showMessage("Suscribe");
        }

        @Override
        public void onNext(Team team) {
            viewCallback.showMessage("updated teams");
            if (team != null)
                position.setTeam(team);
        }

        @Override
        public void onError(Throwable e) {
            viewCallback.showMessage("Error update teams");
        }

        @Override
        public void onComplete() {

        }
    }
}
