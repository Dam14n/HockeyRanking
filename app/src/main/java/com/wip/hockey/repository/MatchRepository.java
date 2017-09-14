package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.AppExecutors;
import com.wip.hockey.model.Match;
import com.wip.hockey.networking.mock.Status;
import com.wip.hockey.room.dao.MatchDao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class MatchRepository {

    private final WebService webservice;
    private final MatchDao matchDao;
    private final AppExecutors executor;

    private MutableLiveData<Status> statusLiveData = new MutableLiveData<>();

    @Inject
    public MatchRepository(WebService webservice, MatchDao matchDao, AppExecutors executor) {
        this.webservice = webservice;
        this.matchDao = matchDao;
        this.executor = executor;
    }

    public LiveData<List<Match>> getMatchs(int dateId) {
        refreshMatches(dateId);
        return matchDao.getMatchs(dateId);
    }

    private void refreshMatches(int dateId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            boolean datesExists = !matchDao.getMatchsNow(dateId).isEmpty();
            if (!datesExists) {
                getWebMatches(dateId);
            }else {
                statusLiveData.postValue(Status.SUCCESS);
            }
        });
    }

    private void getWebMatches(int dateId) {
        Response response = null;
        try {
            response = webservice.getMatchesByDate(dateId).execute();
            if (response != null) {
                statusLiveData.postValue(response.isSuccessful() ? Status.SUCCESS : Status.ERROR);
                matchDao.insertAll((List<Match>) response.body());
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

    public void updateMatches(int dateId) {
        executor.networkIO().execute(() -> {
            statusLiveData.postValue(Status.LOADING);
            getWebMatches(dateId);
        });
    }
}
