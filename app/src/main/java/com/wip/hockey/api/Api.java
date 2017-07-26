package com.wip.hockey.api;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.Team;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by djorda on 08/07/2017.
 */

public class Api implements ServiceApi {

    private static Api api = new Api();
    private final Retrofit retrofit;
    private final IMobile bffApi;
    private final String BASE_URL1 = "http://192.168.0.145/webapi/api/";
    private final String BASE_URL = "http://10.0.2.2/webapi/api/";

    private Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        bffApi = retrofit.create(IMobile.class);
    }

    public static Api getInstance() {
        return api;
    }

    @Override
    public void getDivisions(Callback<List<Division>> callback) {
        bffApi.getDivisions().enqueue(callback);
    }


    @Override
    public void getDivision(Callback<Division> callback, int id) {
        bffApi.getDivision(id).enqueue(callback);
    }


    @Override
    public void getSubDivisionsByDivision(Callback<List<SubDivision>> callback, int divisionId) {
        bffApi.getSubDivisionsByDivision(divisionId).enqueue(callback);
    }

    @Override
    public void getSubDivisionByDivision(Callback<SubDivision> callback, int divisionId, int id) {
        bffApi.getSubDivisionByDivision(divisionId,id).enqueue(callback);
    }

    @Override
    public void getSubDivisions(Callback<List<SubDivision>> callback) {
        bffApi.getSubDivisions().enqueue(callback);
    }

    @Override
    public void getSubDivision(Callback<SubDivision> callback, int id) {
        bffApi.getSubDivision(id).enqueue(callback);
    }

    @Override
    public void getCategoriesBySubDivision(Callback<List<Category>> callback, int subDivisionId) {
        bffApi.getCategoriesBySubDivision(subDivisionId).enqueue(callback);
    }

    @Override
    public void getCategoryBySubDivision(Callback<Category> callback, int subDivisionId, int id) {
        bffApi.getCategoryBySubDivision(subDivisionId,id).enqueue(callback);
    }

    @Override
    public void getCategories(Callback<List<Category>> callback) {
        bffApi.getCategories().enqueue(callback);
    }

    @Override
    public void getCategory(Callback<Category> callback, int id) {
        bffApi.getCategory(id).enqueue(callback);
    }

    @Override
    public void getDatesByCategory(Callback<List<Date>> callback, int categoryId) {
        bffApi.getDatesByCategory(categoryId).enqueue(callback);
    }

    @Override
    public void getMatchesByDate(Callback<List<Match>> callback, int dateId) {
        bffApi.getMatchesByDate(dateId).enqueue(callback);
    }

    @Override
    public void getTeams(Callback<List<Team>> callback) {
        bffApi.getTeams().enqueue(callback);
    }

    @Override
    public void getTeam(Callback<Team> callback, int id) {
        bffApi.getTeam(id).enqueue(callback);
    }

    @Override
    public void getTeamByMatch(Callback<Team> callback, int matchId, int id) {
        bffApi.getTeamByMatch(matchId,id).enqueue(callback);
    }

    @Override
    public void getTeamsByMatch(Callback<List<Team>> callback, int matchId) {
        bffApi.getTeamsByMatch(matchId).enqueue(callback);
    }

    public interface IMobile{

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
    }
}
