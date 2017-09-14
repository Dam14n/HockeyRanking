package com.wip.hockey.dagger;

import android.content.Context;

import com.wip.hockey.App;
import com.wip.hockey.AppExecutors;
import com.wip.hockey.api.ApiService;
import com.wip.hockey.networking.mock.RetrofitFactory;
import com.wip.hockey.repository.CategoryRepository;
import com.wip.hockey.repository.DateRepository;
import com.wip.hockey.repository.DivisionRepository;
import com.wip.hockey.repository.MatchRepository;
import com.wip.hockey.repository.SubDivisionRepository;
import com.wip.hockey.repository.WebService;
import com.wip.hockey.room.RoomFactory;
import com.wip.hockey.room.dao.CategoryDao;
import com.wip.hockey.room.dao.DateDao;
import com.wip.hockey.room.dao.DivisionDao;
import com.wip.hockey.room.dao.MatchDao;
import com.wip.hockey.room.dao.SubDivisionDao;
import com.wip.hockey.room.database.AppDataBase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    ApiService provideApiService(){
        Retrofit retrofit = RetrofitFactory.getAdapter();
        return new ApiService(retrofit);
    }

    @Provides
    AppDataBase provideDataBase(Context context){
        return RoomFactory.getAdapter(context.getApplicationContext());
    }

    @Provides
    DivisionDao provideDivisionDao(AppDataBase db){
        return db.divisionDao();
    }

    @Provides
    SubDivisionDao provideSubDivisionDao(AppDataBase db){
        return db.subDivisionDao();
    }

    @Provides
    CategoryDao provideCategoryDao(AppDataBase db){
        return db.categoryDao();
    }

    @Provides
    DateDao provideDateDao(AppDataBase db){
        return db.dateDao();
    }

    @Provides
    MatchDao provideMatchDao(AppDataBase db){
        return db.matchDao();
    }

    @Provides
    Executor provideExecutor(){
        return Executors.newCachedThreadPool();
    }

    @Singleton
    @Provides
    DivisionRepository provideDivisionRepository(WebService webService, DivisionDao divisionDao, AppExecutors executor ){
        return new DivisionRepository(webService,divisionDao,executor);
    }

    @Singleton
    @Provides
    SubDivisionRepository provideSubDivisionRepository(WebService webService, SubDivisionDao subDivisionDao, AppExecutors executor ){
        return new SubDivisionRepository(webService,subDivisionDao,executor);
    }

    @Singleton
    @Provides
    CategoryRepository provideCategoryRepository(WebService webService, CategoryDao categoryDao, AppExecutors executor ){
        return new CategoryRepository(webService,categoryDao,executor);
    }

    @Singleton
    @Provides
    DateRepository provideDateRepository(WebService webService, DateDao dateDao, AppExecutors executor ){
        return new DateRepository(webService,dateDao,executor);
    }

    @Singleton
    @Provides
    MatchRepository provideMatchRepository(WebService webService, MatchDao dateDao, AppExecutors executor ){
        return new MatchRepository(webService,dateDao,executor);
    }
}
