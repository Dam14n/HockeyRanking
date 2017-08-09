package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.wip.hockey.fragment.Board.BoardContract;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Board;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BoardViewModel extends ViewModel implements BoardContract.ViewModel{

    private Repository repository;
    private BoardContract.View viewCallback;


    public BoardViewModel() {
        repository = Repository.getInstance();
    }


    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.viewCallback = (BoardContract.View) viewCallback;
        getBoards();
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void getBoards() {
        repository.getBoards()
                .subscribe(new BoardObserver());
    }

    private class BoardObserver implements Observer<List<Board>> {

        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Board> boards) {
            viewCallback.showMessage("Next");
            viewCallback.setBoards(boards);
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
}
