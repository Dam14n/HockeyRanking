package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.SubDivision.SubDivisionContract;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SubDivisionViewModel extends ViewModel implements SubDivisionContract.ViewModel{

    private Repository repository;
    private SubDivisionContract.View viewCallback;
    private Division division;

    public SubDivisionViewModel() {
        repository = Repository.getInstance();
    }

    @Override
    public void getSubDivisions() {
        repository.getSubDivisionsByDivision(division.getId())
                .subscribe(new SubDivisionObserver());
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.viewCallback = (SubDivisionContract.View) viewCallback;
        getSubDivisions();
    }

    @Override
    public void onViewDetached() {

    }

    private class SubDivisionObserver implements Observer<List<SubDivision>>{
        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Suscribe");
        }

        @Override
        public void onNext(List<SubDivision> subDivisions) {
            viewCallback.showMessage("Next");
            viewCallback.setSubDivisions(subDivisions);
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
