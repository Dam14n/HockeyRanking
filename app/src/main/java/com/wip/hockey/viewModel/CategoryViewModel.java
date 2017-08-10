package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.wip.hockey.fragment.Category.CategoryContract;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CategoryViewModel extends ViewModel implements CategoryContract.ViewModel{

    private Repository repository;
    private CategoryContract.View viewCallback;
    private SubDivision subDivision;


    public CategoryViewModel() {
        repository = Repository.getInstance();
    }

    @Override
    public void getCategories() {
        repository.getCategoriesBySubDivision(subDivision.getId())
                .subscribe(new CategoryObserver());
    }

    public void setSubDivision(SubDivision subDivision) {
        this.subDivision = subDivision;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.viewCallback = (CategoryContract.View) viewCallback;
        getCategories();
    }

    @Override
    public void onViewDetached() {

    }

    private class CategoryObserver implements Observer<List<Category>> {

        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Suscribe");
        }

        @Override
        public void onNext(List<Category> categories) {
            viewCallback.showMessage("Next");
            viewCallback.setCategories(categories);
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
