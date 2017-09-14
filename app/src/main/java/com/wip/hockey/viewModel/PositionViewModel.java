package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Position;
import com.wip.hockey.networking.Status;
import com.wip.hockey.repository.PositionRepository;

import java.util.List;

public class PositionViewModel extends ViewModel{

    private PositionRepository positionRepository;
    private MutableLiveData<Integer> category = new MutableLiveData<>();
    private LiveData<List<Position>> positions =
            Transformations.switchMap( category , (categoryId) ->
                    positionRepository.getPositions(categoryId));


    public PositionViewModel(PositionRepository repository) {
        this.positionRepository = repository;
    }

    public void init(int categoryId){
        if (this.category.getValue() != null){
            return;
        }
        category.setValue(categoryId);
    }

    public LiveData<List<Position>> getPositions(){
        return positions;
    }

    public void updatePositions(int categoryId) {
        positionRepository.updatePositions(categoryId);
    }

    public LiveData<Status> getUpdateStatus() {
        return positionRepository.getUpdateStatus();
    }

}
