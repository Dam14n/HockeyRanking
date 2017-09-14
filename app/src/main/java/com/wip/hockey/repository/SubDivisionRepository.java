package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.AppExecutors;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.room.dao.SubDivisionDao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class SubDivisionRepository {

    private final WebService webservice;
    private final SubDivisionDao subDivisionDao;
    private final AppExecutors executor;

    private MutableLiveData<Status> statusLiveData = new MutableLiveData<>();
    @Inject
    public SubDivisionRepository(WebService webservice, SubDivisionDao subDivisionDao, AppExecutors executor) {
        this.webservice = webservice;
        this.subDivisionDao = subDivisionDao;
        this.executor = executor;
    }

    public LiveData<List<SubDivision>> getSubDivisions(int divisionId) {
        refreshSubDivisions(divisionId);
        return subDivisionDao.getSubDivisions(divisionId);
    }

    private void refreshSubDivisions(int divisionId){
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            boolean subDivisionsExists = !subDivisionDao.getSubDivisionsNow(divisionId).isEmpty();
            if (!subDivisionsExists) {
                getWebSubDivisions(divisionId);
            }else {
                statusLiveData.postValue(Status.SUCCESS);
            }
        });
    }

    public void getWebSubDivisions(int divisionId){
        Response response = null;
        try {
            response = webservice.getSubDivisionsByDivision(divisionId).execute();
            if (response != null) {
                statusLiveData.postValue(response.isSuccessful() ? Status.SUCCESS : Status.ERROR);
                subDivisionDao.insertAll((List<SubDivision>) response.body());
            }else{
                statusLiveData.postValue(Status.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLiveData.postValue(Status.ERROR);
        }
    }

    public void updateSubDivisions(int divisionId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            getWebSubDivisions(divisionId);
        });
    }

    public LiveData<Status> getUpdateStatus() {
        return statusLiveData;
    }
}
