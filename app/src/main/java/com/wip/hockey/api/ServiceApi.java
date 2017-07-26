package com.wip.hockey.api;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.Team;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by djorda on 08/07/2017.
 */

public interface ServiceApi {
    void getDivisions(Callback<List<Division>> callback);
    void getDivision(Callback<Division> callback, int id);
    void getSubDivisionsByDivision(Callback<List<SubDivision>> callback, int divisionId);
    void getSubDivisionByDivision(Callback<SubDivision> callback, int divisionId, int id);
    void getSubDivisions(Callback<List<SubDivision>> callback);
    void getSubDivision(Callback<SubDivision> callback, int id);
    void getCategoriesBySubDivision(Callback<List<Category>> callback, int subDivisionId);
    void getCategoryBySubDivision(Callback<Category> callback, int subDivisionId, int id);
    void getCategories(Callback<List<Category>> callback);
    void getCategory(Callback<Category> callback, int id);
    void getDatesByCategory(Callback<List<Date>> callback, int categoryId);
    void getMatchesByDate(Callback<List<Match>> callback, int dateId);
    void getTeams(Callback<List<Team>> callback);
    void getTeam(Callback<Team> callback,int id);
    void getTeamByMatch(Callback<Team> callback,int matchId, int id);
    void getTeamsByMatch(Callback<List<Team>> callback,int matchId);
}
