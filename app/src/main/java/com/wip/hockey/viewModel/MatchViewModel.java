package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Logo;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Team;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MatchViewModel extends ViewModel {

    private Repository repository;
    private int dateId;

    public MatchViewModel() {
        repository = Repository.getInstance();
    }

    public Observable<List<Match>> getMatches() {
       return repository.getMatchesByDate(dateId);
    }

    public void getTeams(List<Match> matches) {
        for (Match match : matches)
            repository.getTeamsByMatch(match.getId())
                    .subscribe(new TeamsMatchObserver(match));
    }

    private void getLogo(Team team) {
            repository.getLogo(team.getLogoId())
                    .subscribe(new LogoTeamObserver(team));
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }




    private class TeamsMatchObserver implements Observer<List<Team>> {
        private final Match match;

        public TeamsMatchObserver(Match match) {
            this.match = match;
        }

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(List<Team> teams) {
            for (Team team : teams ){
                if (match.getEnemyTeamId() == team.getId()){
                    match.setEnemyTeam(team);
                }else{
                    match.setLocalTeam(team);
                }
                getLogo(team);
            }
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {

        }
    }

    private class LogoTeamObserver implements Observer<Logo> {

        private final Team team;

        public LogoTeamObserver(Team team) {
            this.team = team;
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Logo logo) {
            this.team.setLogo(logo);

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
