package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Match.MatchContract;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Logo;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Team;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MatchViewModel extends ViewModel implements MatchContract.ViewModel {

    private Repository repository;
    private MatchContract.View viewCallback;
    private Date date;

    public MatchViewModel() {
        repository = Repository.getInstance();
    }

    @Override
    public void getMatches() {
        repository.getMatchesByDate(date.getId())
                .subscribe(new MatchObserver());
    }

    private void getTeams(List<Match> matches) {
        for (Match match : matches)
            repository.getTeamsByMatch(match.getId())
                    .subscribe(new TeamsMatchObserver(match));
    }

    private void getLogo(Team team) {
            repository.getLogo(team.getLogoId())
                    .subscribe(new LogoTeamObserver(team));
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.viewCallback = (MatchContract.View) viewCallback;
        getMatches();
    }



    @Override
    public void onViewDetached() {

    }

    private class MatchObserver implements Observer<List<Match>> {
        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Match> matches) {
            getTeams(matches);
            viewCallback.showMessage("Next");
            viewCallback.setMatches(matches);
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

    private class TeamsMatchObserver implements Observer<List<Team>> {
        private final Match match;

        public TeamsMatchObserver(Match match) {
            this.match = match;
        }

        @Override
        public void onSubscribe(Disposable d) {
            viewCallback.showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Team> teams) {
            viewCallback.showMessage("updated teams");
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
            viewCallback.showMessage("Error update teams");
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
            viewCallback.showMessage("Subscribe");
        }

        @Override
        public void onNext(Logo logo) {
            viewCallback.showMessage("updated teams");
            this.team.setLogo(logo);

        }

        @Override
        public void onError(Throwable e) {
            viewCallback.showMessage("Error update teams");
        }

        @Override
        public void onComplete() {

        }
    }
}
