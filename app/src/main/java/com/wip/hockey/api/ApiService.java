package com.wip.hockey.api;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.Team;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ApiService implements IApiService {

    private static IApiService apiService;

    public ApiService(Retrofit retrofit) {
        this.apiService = retrofit.create(IApiService.class);
    }

    @Override
    public Observable<List<Division>> getDivisions() {
        return apiService.getDivisions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Division> getDivision(int divisionId) {
        return null;
    }

    @Override
    public Observable<List<SubDivision>> getSubDivisionsByDivision(int divisionId) {
        return apiService.getSubDivisionsByDivision(divisionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SubDivision> getSubDivisionByDivision(int divisionId, int subDivisionId) {
        return null;
    }

    @Override
    public Observable<List<SubDivision>> getSubDivisions() {
        return apiService.getSubDivisions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SubDivision> getSubDivision(int subdivisionId) {
        return null;
    }

    @Override
    public Observable<List<Category>> getCategoriesBySubDivision(int subDivisionId) {
        return apiService.getCategoriesBySubDivision(subDivisionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Category> getCategoryBySubDivision(int subDivisionId, int categoryId) {
        return null;
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return null;
    }

    @Override
    public Observable<Category> getCategory(int categoryId) {
        return null;
    }

    @Override
    public Observable<List<Date>> getDatesByCategory(int categoryId) {
        return apiService.getDatesByCategory(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Match>> getMatchesByDate(int dateId) {
        return apiService.getMatchesByDate(dateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Team>> getTeams() {
        return null;
    }

    @Override
    public Observable<Team> getTeam(int id) {
        return null;
    }

    @Override
    public Observable<Team> getTeamByMatch(int matchId, int id) {
        return null;
    }

    @Override
    public Observable<List<Team>> getTeamsByMatch(int matchId) {
        return apiService.getTeamsByMatch(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
