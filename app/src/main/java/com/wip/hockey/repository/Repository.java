package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.api.Api;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository instance = new Repository();
    private Api api = Api.getInstance();

    private Repository() {
    }

    public static Repository getInstance(){
        return instance;
    }

    public MutableLiveData<List<Division>> getDivisions(){
        final MutableLiveData<List<Division>> data = new MutableLiveData<>();
        api.getDivisions(new Callback<List<Division>>() {
            @Override
            public void onResponse(Call<List<Division>> call, Response<List<Division>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Division>> call, Throwable t) {
                System.out.println(t.getMessage());
            //    Toast.makeText(getContext(), "Error al buscar datos", Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

    public LiveData<List<SubDivision>> getSubDivisions(Division division) {
        final MutableLiveData<List<SubDivision>> data = new MutableLiveData<>();
        api.getSubDivisionsByDivision(new Callback<List<SubDivision>>() {
            @Override
            public void onResponse(Call<List<SubDivision>> call, Response<List<SubDivision>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SubDivision>> call, Throwable t) {
                System.out.println(t.getMessage());
            //    Toast.makeText(getContext(), "Error al buscar datos", Toast.LENGTH_SHORT).show();
            }
        },division.getId());
        return data;
    }

    public LiveData<List<Category>> getCategories(SubDivision subDivision) {
        final MutableLiveData<List<Category>> data = new MutableLiveData<>();
        api.getCategoriesBySubDivision(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        },subDivision.getId());
        return data;
    }


    public LiveData<List<Date>> getDates(Category category) {
        final MutableLiveData<List<Date>> data = new MutableLiveData<>();
        api.getDatesByCategory(new Callback<List<Date>>() {
            @Override
            public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Date>> call, Throwable t) {

            }
        },category.getId());
        return data;
    }

    public LiveData<List<Match>> getMatches(Date date) {
        final MutableLiveData<List<Match>> data = new MutableLiveData<>();
        api.getMatchesByDate(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        },date.getId());
        return data;
    }

    public void updateTeams(final Match match) {
        api.getTeamByMatch(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                match.setLocalTeam(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {

            }
        },match.getId(),match.getLocalTeamId());
        api.getTeamByMatch(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                match.setEnemyTeam(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {

            }
        },match.getId(),match.getEnemyTeamId());
    }
}
