package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Match;
import com.wip.hockey.networking.Status;
import com.wip.hockey.repository.MatchRepository;

import java.util.List;

public class MatchViewModel extends ViewModel {

    private MatchRepository matchRepository;
    private MutableLiveData<Integer> date = new MutableLiveData<>();
    private LiveData<List<Match>> matchs =
            Transformations.switchMap( date , (dateId) ->
                    matchRepository.getMatchs(dateId));

    public MatchViewModel(MatchRepository repository) {
        this.matchRepository = repository;
    }


    public LiveData<List<Match>> getMatchs() {
        return matchs;
    }

    public LiveData<Status> getUpdateStatus() {
        return matchRepository.getUpdateStatus();
    }

    public void init(int dateId) {
        if (this.date.getValue() != null){
            return;
        }
        date.setValue(dateId);
    }

    public void updateMatches(int dateId) {
        matchRepository.updateMatches(dateId);
    }
}
