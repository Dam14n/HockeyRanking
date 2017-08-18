package com.wip.hockey.app;

import android.arch.lifecycle.LifecycleActivity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wip.hockey.R;
import com.wip.hockey.databinding.ActivityMainBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.NavigationDrawerFragment;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.User;

public class MainActivity extends LifecycleActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private HandlerFragment handlerFragment;
    public static FavoriteManager favoriteManager;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (savedInstanceState == null){
            setUpFragment();
        }else{
            handlerFragment = HandlerFragment.getInstance();
            handlerFragment.setContext(this);
        }

        setUpToolbar();
        setUpDrawer();

        setUpFavorite();

        Intent intent = this.getIntent();
        User user = (User) intent.getSerializableExtra("USER");
        Log.d(TAG, "User Id: "+ user.getId());

        //This line must be removed when Database will be determined
        MainActivity.favoriteManager.removeAll();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        createAdBanner();
    }

    private void setUpFavorite() {
        favoriteManager = new FavoriteManager(this);
    }

    private void setUpFragment() {
        handlerFragment = HandlerFragment.getInstance();
        handlerFragment.setContext(this);
        showProgress(true);
        BaseFragment fragment = (BaseFragment) handlerFragment.changeToFragment(R.id.fragment_division_recycler);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.OPERATION_TYPE,ViewType.POSITIONS_VIEW);
        fragment.setArguments(bundle);
    }

    private void setUpToolbar(){
        binding.toolbar.baseToolbar.setTitle("Hockey");
        binding.toolbar.baseToolbar.setSubtitle("Pasion");
        binding.toolbar.baseToolbar.inflateMenu(R.menu.menu_main);
    }

    private void setUpDrawer(){
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment,drawerLayout,binding.toolbar.baseToolbar);
    }

    public void showProgress(boolean isVisible){
        binding.loading.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void createAdBanner(){
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7652174985399137~2420714987");
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        binding.adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed");
            }
        });
    }
}

