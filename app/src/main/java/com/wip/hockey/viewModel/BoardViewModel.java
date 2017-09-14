package com.wip.hockey.viewModel;

import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Board;
import com.wip.hockey.repository.WebService;

import java.util.List;

import retrofit2.Call;

public class BoardViewModel extends ViewModel{

    private WebService webService;
    private int categoryId;


    public BoardViewModel() {
        //webService = WebService.getInstance();
    }


    public Call<List<Board>> getBoards() {
        return webService.getBoardsByCategory(this.categoryId);
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
