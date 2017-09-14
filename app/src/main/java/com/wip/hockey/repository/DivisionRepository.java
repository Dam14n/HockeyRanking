package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.AppExecutors;
import com.wip.hockey.model.Division;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.room.dao.DivisionDao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class DivisionRepository {

    private final WebService webservice;
    private final DivisionDao divisionDao;
    private final AppExecutors executors;

    private MutableLiveData<Status> statusLiveData = new MutableLiveData<>();

    @Inject
    public DivisionRepository(WebService webservice, DivisionDao divisionDao, AppExecutors executors) {
        this.webservice = webservice;
        this.divisionDao = divisionDao;
        this.executors = executors;
    }

    public LiveData<List<Division>> getDivisions() {
        refreshDivisions();
        return divisionDao.getDivisions();
    }

    private void refreshDivisions(){
        executors.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            boolean divisionsExists = !divisionDao.getDivisionsNow().isEmpty();
            if (!divisionsExists) {
                getWebDivisions();
            }else {
                statusLiveData.postValue(Status.SUCCESS);
            }
        });
    }

    public void getWebDivisions(){
        Response response = null;
        try {
            response = webservice.getDivisions().execute();
            if (response != null) {
                statusLiveData.postValue(response.isSuccessful() ? Status.SUCCESS : Status.ERROR);
                divisionDao.insertAll((List<Division>) response.body());
            }else{
                statusLiveData.postValue(Status.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLiveData.postValue(Status.ERROR);
        }
    }

    public void updateDivisions() {
        executors.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            getWebDivisions();
        });
    }

    public LiveData<Status> getUpdateStatus() {
        return statusLiveData;
    }
}
