package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.wip.hockey.fragment.Division.DivisionContract;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Division;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class DivisionViewModel extends ViewModel implements DivisionContract.ViewModel {

    private Repository repository;
    private DivisionContract.View viewCallback;

    public DivisionViewModel() {
        repository = Repository.getInstance();
    }

    @Override
    public void getDivisions(){
        repository.getDivisions()
                .subscribe(new DivisionsObserver());
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.viewCallback = (DivisionContract.View) viewCallback;
        getDivisions();
    }

    @Override
    public void onViewDetached() {

    }

    private class DivisionsObserver implements io.reactivex.Observer<List<Division>> {
        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Suscribe");
        }

        @Override
        public void onNext(List<Division> divisions) {
            viewCallback.showMessage("Next");
            viewCallback.setDivisions(divisions);
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
