package com.wip.hockey.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wip.hockey.api.Api;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;

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
}
