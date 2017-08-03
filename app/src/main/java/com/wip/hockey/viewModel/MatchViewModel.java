package com.wip.hockey.viewModel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.wip.hockey.model.Date;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Team;
import com.wip.hockey.repository.Repository;

import java.util.List;

public class MatchViewModel extends ViewModel {

    private LiveData<List<Match>> matchLiveData;
    private MutableLiveData<Date> date = new MutableLiveData<Date>();

    public MatchViewModel() {
        Date date = this.date.getValue() == null ? new Date() : this.date.getValue();
        matchLiveData = Repository.getInstance().getMatches(date);
    }

    public void setDate(Date date) {
        this.date.setValue(date);
        matchLiveData = Repository.getInstance().getMatches(this.date.getValue());

    }

    public LiveData<List<Match>> getMatchListObservable(){
        return matchLiveData;
    }

    public void updateTeams(){
        if (matchLiveData.getValue() != null) {
            for (Match match : matchLiveData.getValue()) {
                Repository.getInstance().updateTeams(match);
            }
        }
    }
}
