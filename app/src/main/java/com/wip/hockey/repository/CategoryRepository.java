package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.AppExecutors;
import com.wip.hockey.model.Category;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.room.dao.CategoryDao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class CategoryRepository {

    private final WebService webservice;
    private final CategoryDao categoryDao;
    private final AppExecutors executor;

    private MutableLiveData<Status> statusLiveData = new MutableLiveData<>();
    @Inject
    public CategoryRepository(WebService webservice, CategoryDao categoryDao, AppExecutors executor) {
        this.webservice = webservice;
        this.categoryDao = categoryDao;
        this.executor = executor;
    }

    public LiveData<List<Category>> getCategories(int subDivisionId) {
        refreshCategories(subDivisionId);
        return categoryDao.getCategories(subDivisionId);
    }

    private void refreshCategories(int subDivisionId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            boolean categoriesExists = !categoryDao.getCategoriesNow(subDivisionId).isEmpty();
            if (!categoriesExists) {
                getWebCategories(subDivisionId);
            }else {
                statusLiveData.postValue(Status.SUCCESS);
            }
        });
    }

    private void getWebCategories(int subDivisionId) {
        Response response = null;
        try {
            response = webservice.getCategoriesBySubDivision(subDivisionId).execute();
            if (response != null) {
                statusLiveData.postValue(response.isSuccessful() ? Status.SUCCESS : Status.ERROR);
                categoryDao.insertAll((List<Category>) response.body());
            }else{
                statusLiveData.postValue(Status.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLiveData.postValue(Status.ERROR);
        }
    }

    public void updateCategories(int subDivisionId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            getWebCategories(subDivisionId);
        });
    }

    public LiveData<Status> getUpdateStatus() {
        return statusLiveData;
    }
}
