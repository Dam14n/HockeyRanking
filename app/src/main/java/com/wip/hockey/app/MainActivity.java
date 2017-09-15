package com.wip.hockey.app;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.wip.hockey.R;
import com.wip.hockey.databinding.ActivityMainBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.CloseAppDialogFragment;
import com.wip.hockey.fragment.NavigationDrawerFragment;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.User;
import com.wip.hockey.networking.Google.GoogleFactory;
import com.wip.hockey.room.RoomFactory;
import com.wip.hockey.room.database.AppDataBase;
import com.wip.hockey.viewModel.MainActivityViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends LifecycleActivity implements Toolbar.OnMenuItemClickListener, HasSupportFragmentInjector{

    public static final String TAG = MainActivity.class.getSimpleName();
    private HandlerFragment handlerFragment;
    public static FavoriteManager favoriteManager;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ActivityMainBinding binding;
    private GoogleApiClient mGoogleApiClient;
    private AppDataBase db;
    private MainActivityViewModel mViewModel;
    private User user;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        user = (User)  this.getIntent().getSerializableExtra(Constants.USER);

        if (savedInstanceState == null){
            setUpFragment();
        }else{
            handlerFragment = HandlerFragment.getInstance();
            handlerFragment.setContext(this);
        }

        mGoogleApiClient = new GoogleFactory().getAdapter(this);

        setUpToolbar();
        setUpDrawer();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        createAdBanner();

        db = RoomFactory.getAdapter(this);

        mViewModel.findById(user.getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userDb -> {
                    if (userDb == null){
                        insertUser(user);
                    }
                });
    }

    public void insertUser(User user){
        mViewModel.updateUserName(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, throwable -> {});
    }

    private void setUpFragment() {
        handlerFragment = HandlerFragment.getInstance();
        handlerFragment.setContext(this);
        showProgress(true);
        BaseFragment fragment = (BaseFragment) handlerFragment.changeToFragment(R.id.fragment_division_recycler);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.OPERATION_TYPE,ViewType.FIXTURE_VIEW);
        bundle.putSerializable(Constants.USER,this.user);
        fragment.setArguments(bundle);
    }

    private void setUpToolbar(){
        binding.toolbar.baseToolbar.setTitle("Hockey");
        binding.toolbar.baseToolbar.setSubtitle("Pasion");
        binding.toolbar.baseToolbar.inflateMenu(R.menu.menu_main);
        binding.toolbar.baseToolbar.setOnMenuItemClickListener(this);
    }

    private void setUpDrawer(){
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.USER,this.user);
        drawerFragment.setArguments(bundle);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment,drawerLayout,binding.toolbar.baseToolbar);
    }

    public void showProgress(boolean isVisible){
        binding.loading.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }

    private void logout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(status -> {});
        Intent i = this.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(this.getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(i);
    }

    public void createAdBanner(){
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7652174985399137~2420714987");
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out:
                this.logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (handlerFragment.isBackEmpty()){
            CloseAppDialogFragment closeAppDialogFragment = new CloseAppDialogFragment();
            closeAppDialogFragment.show(getSupportFragmentManager(),Constants.EXIT);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}

