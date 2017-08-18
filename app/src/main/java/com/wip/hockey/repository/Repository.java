package com.wip.hockey.repository;

import com.wip.hockey.api.ApiService;
import com.wip.hockey.model.Board;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Logo;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Position;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.Team;
import com.wip.hockey.networking.mock.RetrofitFactory;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class Repository {

    private static Repository instance = new Repository();
    private ApiService apiService;

    private Repository() {
        Retrofit retrofit = RetrofitFactory.getAdapter();
        this.apiService = new ApiService(retrofit);
    }

    public static Repository getInstance(){
        return instance;
    }

    public Observable<List<Division>> getDivisions(){
        return apiService.getDivisions();
    }


    public Observable<List<SubDivision>> getSubDivisionsByDivision(int divisionId){
        return apiService.getSubDivisionsByDivision(divisionId);
    }

    public Observable<List<Category>> getCategoriesBySubDivision(int subDivisionId){
        return apiService.getCategoriesBySubDivision(subDivisionId);
    }

    public Observable<List<Date>> getDatesByCategory(int categoryId){
        return apiService.getDatesByCategory(categoryId);
    }

    public Observable<List<Match>> getMatchesByDate(int dateId){
        return apiService.getMatchesByDate(dateId);
    }

    public Observable<List<Team>> getTeamsByMatch(int matchId){
        return apiService.getTeamsByMatch(matchId);
    }

    public Observable<List<Position>> getPositions(){
        return apiService.getPositions();
    }

    public Observable<List<Position>> getPositionsByBoard(int boardId){
        return apiService.getPositionsByBoard(boardId);
    }

    public Observable<List<Board>> getBoards(){
        return apiService.getBoards();
    }

    public Observable<List<Board>> getBoardsByCategory(int categoryId){
        return apiService.getBoardsByCategory(categoryId);
    }

    public Observable<Team> getTeam(int id){
        return apiService.getTeam(id);
    }

    public Observable<Logo> getLogo(int id){
        return apiService.getLogo(id);
    }
}
