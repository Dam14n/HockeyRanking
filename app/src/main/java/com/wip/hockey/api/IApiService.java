package com.wip.hockey.api;

import com.wip.hockey.model.*;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiService {

    @GET("divisions")
    Observable<List<Division>> getDivisions();

    @GET("divisions/{id}")
    Observable<Division> getDivision(@Path("id") int divisionId);

    @GET("divisions/{divisionId}/subdivisions")
    Observable<List<SubDivision>> getSubDivisionsByDivision(@Path("divisionId") int divisionId);

    @GET("divisions/{divisionId}/subdivisions/{id}")
    Observable<SubDivision> getSubDivisionByDivision(@Path("divisionId") int divisionId, @Path("id") int subDivisionId);

    @GET("subdivisions")
    Observable<List<SubDivision>> getSubDivisions();

    @GET("subdivisions/{id}")
    Observable<SubDivision> getSubDivision(@Path("id") int subdivisionId);

    @GET("subdivisions/{subDivisionId}/categories")
    Observable<List<Category>> getCategoriesBySubDivision(@Path("subDivisionId") int subDivisionId);

    @GET("subdivisions/{subDivisionId}/categories/{categoryId}")
    Observable<Category> getCategoryBySubDivision(@Path("subDivisionId") int subDivisionId, @Path("id") int categoryId);

    @GET("categories")
    Observable<List<Category>> getCategories();

    @GET("categories/{id}")
    Observable<Category> getCategory(@Path("id") int categoryId);

    @GET("categories/{categoryId}/dates")
    Observable<List<Date>> getDatesByCategory(@Path("categoryId") int categoryId);

    @GET("dates/{dateId}/matches")
    Observable<List<Match>> getMatchesByDate(@Path("dateId") int dateId);

    @GET("teams")
    Observable<List<Team>> getTeams();

    @GET("teams/{id}")
    Observable<Team> getTeam(@Path("id") int id);

    @GET("matches/{matchId}/teams/{id}")
    Observable<Team> getTeamByMatch(@Path("matchId") int matchId, @Path("id") int id);

    @GET("matches/{matchId}/teams")
    Observable<List<Team>> getTeamsByMatch(@Path("matchId") int matchId);

    @GET("positions")
    Observable<List<Position>> getPositions();

    @GET("boards/{boardId}/positions")
    Observable<List<Position>> getPositionsByBoard(@Path("boardId") int boardId);

    @GET("boards")
    Observable<List<Board>> getBoards();
}
