package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.wip.hockey.fragment.Date.DateContract;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DateViewModel extends ViewModel implements DateContract.ViewModel{

    private Repository repository;
    private DateContract.View viewCallback;
    private Category category;

    public DateViewModel() {
        repository = Repository.getInstance();
    }

    @Override
    public void getDates() {
        repository.getDatesByCategory(category.getId())
                .subscribe(new DateObserver());
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.viewCallback = (DateContract.View) viewCallback;
        getDates();
    }

    @Override
    public void onViewDetached() {

    }

    private class DateObserver implements Observer<List<Date>>{

        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Date> dates) {
            viewCallback.showMessage("Next");
            viewCallback.setDates(dates);
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

        }
    }
}
