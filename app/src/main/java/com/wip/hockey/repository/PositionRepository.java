package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.AppExecutors;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Position;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.room.dao.CategoryDao;
import com.wip.hockey.room.dao.PositionDao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class PositionRepository {

    private final WebService webservice;
    private final PositionDao positionDao;
    private final AppExecutors executor;

    private MutableLiveData<Status> statusLiveData = new MutableLiveData<>();
    @Inject
    public PositionRepository(WebService webservice, PositionDao positionDao, AppExecutors executor) {
        this.webservice = webservice;
        this.positionDao = positionDao;
        this.executor = executor;
    }

    public LiveData<List<Position>> getPositions(int categoryId) {
        refreshPositions(categoryId);
        return positionDao.getPositions(categoryId);
    }

    private void refreshPositions(int categoryId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            boolean categoriesExists = !positionDao.getPositionsNow(categoryId).isEmpty();
            if (!categoriesExists) {
                getWebPositions(categoryId);
            }else {
                statusLiveData.postValue(Status.SUCCESS);
            }
        });
    }

    private void getWebPositions(int categoryId) {
        Response response = null;
        try {
            response = webservice.getPositionsByCategory(categoryId).execute();
            if (response != null) {
                statusLiveData.postValue(response.isSuccessful() ? Status.SUCCESS : Status.ERROR);
                for (Position position:(List<Position>)response.body()) {
                    position.setCategoryId(categoryId);
                    positionDao.insert(position);
                }
            }else{
                statusLiveData.postValue(Status.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLiveData.postValue(Status.ERROR);
        }
    }

    public void updatePositions(int categoryId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            getWebPositions(categoryId);
        });
    }

    public LiveData<Status> getUpdateStatus() {
        return statusLiveData;
    }

}
