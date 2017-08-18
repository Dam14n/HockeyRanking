package com.wip.hockey.app;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wip.hockey.R;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.NavigationDrawerFragment;
import com.wip.hockey.fragment.ViewType;
import com.wip.hockey.handler.HandlerFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private  HandlerFragment handlerFragment;
    public static FavoriteManager favoriteManager;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpDrawer();
        setUpFragment();
        setUpFavorite();
        //This line must be removed when Database will be determined
        MainActivity.favoriteManager.removeAll();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7652174985399137~2420714987");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
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

    private void setUpFavorite() {
        favoriteManager = new FavoriteManager(this);
    }

    private void setUpFragment() {
        handlerFragment = HandlerFragment.getInstance();
        handlerFragment.setContext(this);
        progressBar = findViewById(R.id.loading);
        showProgress(true);
        Lifecycle.View view = (Lifecycle.View) handlerFragment.changeToFragment(R.id.fragment_division_recycler);
        view.setType(ViewType.POSITIONS_VIEW);
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hockey");
        toolbar.setSubtitle("Pasion");
        toolbar.inflateMenu(R.menu.menu_main);
    }

    private void setUpDrawer(){
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment,drawerLayout,toolbar);
    }

    public void showProgress(boolean isVisible){
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}

