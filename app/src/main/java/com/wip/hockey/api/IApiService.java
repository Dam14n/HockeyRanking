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
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiService {

    @GET("divisions")
    Call<List<Division>> getDivisions();

    @GET("divisions/{id}")
    Call<Division> getDivision(@Path("id") int divisionId);

    @GET("divisions/{divisionId}/subdivisions")
    Call<List<SubDivision>> getSubDivisionsByDivision(@Path("divisionId") int divisionId);

    @GET("divisions/{divisionId}/subdivisions/{id}")
    Call<SubDivision> getSubDivisionByDivision(@Path("divisionId") int divisionId, @Path("id") int subDivisionId);

    @GET("subdivisions")
    Call<List<SubDivision>> getSubDivisions();

    @GET("subdivisions/{id}")
    Call<SubDivision> getSubDivision(@Path("id") int subdivisionId);

    @GET("subdivisions/{subDivisionId}/categories")
    Call<List<Category>> getCategoriesBySubDivision(@Path("subDivisionId") int subDivisionId);

    @GET("subdivisions/{subDivisionId}/categories/{categoryId}")
    Call<Category> getCategoryBySubDivision(@Path("subDivisionId") int subDivisionId, @Path("id") int categoryId);

    @GET("categories")
    Call<List<Category>> getCategories();

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") int categoryId);

    @GET("categories/{categoryId}/dates")
    Call<List<Date>> getDatesByCategory(@Path("categoryId") int categoryId);

    @GET("dates/{dateId}/matches")
    Call<List<Match>> getMatchesByDate(@Path("dateId") int dateId);

    @GET("teams")
    Call<List<Team>> getTeams();

    @GET("teams/{id}")
    Call<Team> getTeam(@Path("id") int id);

    @GET("matches/{matchId}/teams/{id}")
    Call<Team> getTeamByMatch(@Path("matchId") int matchId, @Path("id") int id);

    @GET("matches/{matchId}/teams")
    Call<List<Team>> getTeamsByMatch(@Path("matchId") int matchId);

    @GET("positions")
    Call<List<Position>> getPositions();

    @GET("boards/{boardId}/positions")
    Call<List<Position>> getPositionsByBoard(@Path("boardId") int boardId);

    @GET("categories/{categoryId}/positions")
    Call<List<Position>> getPositionsByCategory(@Path("categoryId") int categoryId);

    @GET("boards")
    Call<List<Board>> getBoards();

    @GET("categories/{categoryId}/boards")
    Call<List<Board>> getBoardsByCategory(@Path("categoryId") int categoryId);

    @GET("logos/{id}")
    Call<Logo> getLogo(@Path("id") int id);


}
