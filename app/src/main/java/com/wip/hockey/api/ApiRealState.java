package com.wip.hockey.api;

import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.SubDivision;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by djorda on 08/07/2017.
 */

public class ApiRealState implements ServiceApi {

    private static ApiRealState apiRealState = new ApiRealState();
    private final Retrofit retrofit;
    private final RealestateBffMobile bffMobileApi;
    private final String BASE_URL1 = "http://10.0.2.2:2491/api/";
    private final String BASE_URL = "http://10.0.2.2/webapi/api/";

    private ApiRealState() {
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
        bffMobileApi = retrofit.create(RealestateBffMobile.class);
    }

    public static ApiRealState getInstance() {
        return apiRealState;
    }

    @Override
    public void getDivisions(Callback<List<Division>> callback) {
        bffMobileApi.getDivisions("application/json").enqueue(callback);
    }


    @Override
    public void getDivision(Callback<Division> callback, int id) {
        bffMobileApi.getDivision("application/json",id).enqueue(callback);
    }


    @Override
    public void getSubDivisionsByDivision(Callback<List<SubDivision>> callback, int divisionId) {
        bffMobileApi.getSubDivisionsByDivision("application/json",divisionId).enqueue(callback);
    }

    @Override
    public void getSubDivisionByDivision(Callback<SubDivision> callback, int divisionId, int id) {
        bffMobileApi.getSubDivisionByDivision("application/json",divisionId,id).enqueue(callback);
    }

    @Override
    public void getSubDivisions(Callback<List<SubDivision>> callback) {
        bffMobileApi.getSubDivisions("application/json").enqueue(callback);
    }

    @Override
    public void getSubDivision(Callback<SubDivision> callback, int id) {
        bffMobileApi.getSubDivision("application/json",id).enqueue(callback);
    }

    @Override
    public void getCategoriesBySubDivision(Callback<List<Category>> callback, int subDivisionId) {
        bffMobileApi.getCategoriesBySubDivision("application/json",subDivisionId).enqueue(callback);
    }

    @Override
    public void getCategoryBySubDivision(Callback<Category> callback, int subDivisionId, int id) {
        bffMobileApi.getCategoryBySubDivision("application/json",subDivisionId,id).enqueue(callback);
    }

    @Override
    public void getCategories(Callback<List<Category>> callback) {
        bffMobileApi.getCategories("application/json").enqueue(callback);
    }

    @Override
    public void getCategory(Callback<Category> callback, int id) {
        bffMobileApi.getCategory("application/json",id).enqueue(callback);
    }

    @Override
    public void getDatesByCategory(Callback<List<Date>> callback, int categoryId) {
        bffMobileApi.getDatesByCategory("application/json",categoryId).enqueue(callback);
    }

    @Override
    public void getMatchesByDate(Callback<List<Match>> callback, int dateId) {
        bffMobileApi.getMatchesByDate("application/json",dateId).enqueue(callback);
    }

    public interface RealestateBffMobile{

        @GET("divisions")
        Call<List<Division>> getDivisions(@Header("Content-Type") String contentType);

        @GET("divisions/{id}")
        Call<Division> getDivision(@Header("Content-Type") String contentType, @Path("id") int divisionId);

        @GET("divisions/{divisionId}/subdivisions")
        Call<List<SubDivision>> getSubDivisionsByDivision(@Header("Content-Type") String contentType,@Path("divisionId") int divisionId);

        @GET("divisions/{divisionId}/subdivisions/{id}")
        Call<SubDivision> getSubDivisionByDivision(@Header("Content-Type") String contentType,@Path("divisionId") int divisionId,@Path("id") int subDivisionId);

        @GET("subdivisions")
        Call<List<SubDivision>> getSubDivisions(@Header("Content-Type") String contentType);

        @GET("subdivisions/{id}")
        Call<SubDivision> getSubDivision(@Header("Content-Type") String contentType, @Path("id") int subdivisionId);

        @GET("subdivisions/{subDivisionId}/categories")
        Call<List<Category>> getCategoriesBySubDivision(@Header("Content-Type") String contentType, @Path("subDivisionId") int subDivisionId);

        @GET("subdivisions/{subDivisionId}/categories/{categoryId}")
        Call<Category> getCategoryBySubDivision(@Header("Content-Type") String contentType,@Path("subDivisionId") int subDivisionId,@Path("id") int categoryId);

        @GET("categories")
        Call<List<Category>> getCategories(@Header("Content-Type") String contentType);

        @GET("categories/{id}")
        Call<Category> getCategory(@Header("Content-Type") String contentType, @Path("id") int categoryId);

        @GET("categories/{categoryId}/dates")
        Call<List<Date>> getDatesByCategory(@Header("Content-Type") String contentType, @Path("categoryId") int categoryId);

        @GET("dates/{dateId}/matches")
        Call<List<Match>> getMatchesByDate(@Header("Content-Type") String contentType, @Path("dateId") int dateId);
    }
}
