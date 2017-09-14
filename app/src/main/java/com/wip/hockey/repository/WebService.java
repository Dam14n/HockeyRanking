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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class WebService {

    private ApiService apiService;

    @Inject
    public WebService(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<List<Division>> getDivisions(){
        return apiService.getDivisions();
    }

    public Call<List<SubDivision>> getSubDivisionsByDivision(int divisionId){
        return apiService.getSubDivisionsByDivision(divisionId);
    }

    public Call<List<Category>> getCategoriesBySubDivision(int subDivisionId){
        return apiService.getCategoriesBySubDivision(subDivisionId);
    }

    public Call<List<Date>> getDatesByCategory(int categoryId){
        return apiService.getDatesByCategory(categoryId);
    }

    public Call<List<Match>> getMatchesByDate(int dateId){
        return apiService.getMatchesByDate(dateId);
    }

    public Call<List<Team>> getTeamsByMatch(int matchId){
        return apiService.getTeamsByMatch(matchId);
    }

    public Call<List<Position>> getPositionsByBoard(int boardId){
        return apiService.getPositionsByBoard(boardId);
    }

    public Call<List<Position>> getPositionsByCategory(int categoryId){
        return apiService.getPositionsByCategory(categoryId);
    }

    public Call<List<Board>> getBoardsByCategory(int categoryId){
        return apiService.getBoardsByCategory(categoryId);
    }

    public Call<Team> getTeam(int id){
        return apiService.getTeam(id);
    }

    public Call<Logo> getLogo(int id){
        return apiService.getLogo(id);
    }

    public Call<Category> getCategory(int categoryId){
        return apiService.getCategory(categoryId);
    }
}
