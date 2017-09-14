package com.wip.hockey.api;

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

import retrofit2.Call;
import retrofit2.Retrofit;

public class ApiService implements IApiService {

    private static IApiService apiService;

    public ApiService(Retrofit retrofit) {
        apiService = retrofit.create(IApiService.class);
    }

    @Override
    public Call<List<Division>> getDivisions() {
        return apiService.getDivisions();
    }

    @Override
    public Call<Division> getDivision(int divisionId) {
        return null;
    }

    @Override
    public Call<List<SubDivision>> getSubDivisionsByDivision(int divisionId) {
        return apiService.getSubDivisionsByDivision(divisionId);
    }

    @Override
    public Call<SubDivision> getSubDivisionByDivision(int divisionId, int subDivisionId) {
        return null;
    }

    @Override
    public Call<List<SubDivision>> getSubDivisions() {
        return apiService.getSubDivisions();
    }

    @Override
    public Call<SubDivision> getSubDivision(int subdivisionId) {
        return null;
    }

    @Override
    public Call<List<Category>> getCategoriesBySubDivision(int subDivisionId) {
        return apiService.getCategoriesBySubDivision(subDivisionId);
    }

    @Override
    public Call<Category> getCategoryBySubDivision(int subDivisionId, int categoryId) {
        return null;
    }

    @Override
    public Call<List<Category>> getCategories() {
        return null;
    }

    @Override
    public Call<Category> getCategory(int categoryId) {
        return apiService.getCategory(categoryId);
    }

    @Override
    public Call<List<Date>> getDatesByCategory(int categoryId) {
        return apiService.getDatesByCategory(categoryId);
    }

    @Override
    public Call<List<Match>> getMatchesByDate(int dateId) {
        return apiService.getMatchesByDate(dateId);
    }

    @Override
    public Call<List<Team>> getTeams() {
        return null;
    }

    @Override
    public Call<Team> getTeam(int id) {
        return apiService.getTeam(id);
    }

    @Override
    public Call<Team> getTeamByMatch(int matchId, int id) {
        return null;
    }

    @Override
    public Call<List<Team>> getTeamsByMatch(int matchId) {
        return apiService.getTeamsByMatch(matchId);
    }

    @Override
    public Call<List<Position>> getPositions() {
        return apiService.getPositions();
    }

    @Override
    public Call<List<Position>> getPositionsByBoard(int boardId) {
        return apiService.getPositionsByBoard(boardId);
    }

    @Override
    public Call<List<Position>> getPositionsByCategory(int categoryId) {
        return apiService.getPositionsByCategory(categoryId);
    }

    @Override
    public Call<List<Board>> getBoards() {
        return apiService.getBoards();
    }

    @Override
    public Call<List<Board>> getBoardsByCategory(int categoryId) {
        return apiService.getBoardsByCategory(categoryId);
    }

    @Override
    public Call<Logo> getLogo(int id) {
        return apiService.getLogo(id);
    }
}
