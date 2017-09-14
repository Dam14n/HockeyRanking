package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.AppExecutors;
import com.wip.hockey.model.Date;
import com.wip.hockey.networking.Status;
import com.wip.hockey.room.dao.DateDao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class DateRepository {

    private final WebService webservice;
    private final DateDao dateDao;
    private final AppExecutors executor;

    private MutableLiveData<Status> statusLiveData = new MutableLiveData<>();
    @Inject
    public DateRepository(WebService webservice, DateDao dateDao, AppExecutors executor) {
        this.webservice = webservice;
        this.dateDao = dateDao;
        this.executor = executor;
    }

    public LiveData<List<Date>> getDates(int categoryId) {
        refreshDates(categoryId);
        return dateDao.getDates(categoryId);
    }

    private void refreshDates(int categoryId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            boolean datesExists = !dateDao.getDatesNow(categoryId).isEmpty();
            if (!datesExists) {
                getWebDates(categoryId);
            }else {
                statusLiveData.postValue(Status.SUCCESS);
            }
        });
    }

    private void getWebDates(int categoryId) {
        Response response = null;
        try {
            response = webservice.getDatesByCategory(categoryId).execute();
            if (response != null) {
                statusLiveData.postValue(response.isSuccessful() ? Status.SUCCESS : Status.ERROR);
                dateDao.insertAll((List<Date>) response.body());
            }else{
                statusLiveData.postValue(Status.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLiveData.postValue(Status.ERROR);
        }
    }

    public LiveData<Status> getUpdateStatus() {
        return statusLiveData;
    }
}
