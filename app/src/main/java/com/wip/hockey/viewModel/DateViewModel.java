package com.wip.hockey.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.wip.hockey.model.Date;
import com.wip.hockey.networking.Status;
import com.wip.hockey.repository.DateRepository;

import java.util.List;

public class DateViewModel extends ViewModel{

    private DateRepository dateRepository;
    private MutableLiveData<Integer> category = new MutableLiveData<>();
    private LiveData<List<Date>> dates =
            Transformations.switchMap( category , (categoryId) ->
                    dateRepository.getDates(categoryId));

    public DateViewModel(DateRepository repository) {
        this.dateRepository = repository;
    }


    public LiveData<List<Date>> getDates() {
        return dates;
    }

    public LiveData<Status> getUpdateStatus() {
        return dateRepository.getUpdateStatus();
    }

    public void init(int categoryId) {
        if (this.category.getValue() != null){
            return;
        }
        category.setValue(categoryId);
    }
}
