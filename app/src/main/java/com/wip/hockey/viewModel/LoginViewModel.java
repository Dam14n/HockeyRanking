package com.wip.hockey.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.wip.hockey.repository.Repository;

public class LoginViewModel extends AndroidViewModel{

    private FirebaseAuth mAuth;
    private Repository repository;

    public LoginViewModel(Application application) {
        super(application);
        repository = Repository.getInstance();
    }
}
