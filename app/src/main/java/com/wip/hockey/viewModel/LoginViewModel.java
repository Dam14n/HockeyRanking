package com.wip.hockey.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.wip.hockey.R;
import com.wip.hockey.app.LoginActivity;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.model.Board;
import com.wip.hockey.model.User;
import com.wip.hockey.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginViewModel extends AndroidViewModel{

    private FirebaseAuth mAuth;
    private Repository repository;

    public LoginViewModel(Application application) {
        super(application);
        repository = Repository.getInstance();
    }
}
