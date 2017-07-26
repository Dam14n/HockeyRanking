package com.wip.hockey.app;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.wip.hockey.R;
import com.wip.hockey.fragment.NavigationDrawerFragment;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.repository.Repository;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private  HandlerFragment handlerFragment;
    public static FavoriteManager favoriteManager;
    public static Repository repository;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    repository = new Repository();
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpDrawer();
        setUpFragment();
        setUpFavorite();
        //This line must be removed when Database will be determined
        MainActivity.favoriteManager.removeAll();
    }

    private void setUpFavorite() {
        favoriteManager = new FavoriteManager(this);
    }

    private void setUpFragment() {
        handlerFragment = HandlerFragment.getInstance();
        handlerFragment.setContext(this);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        showProgress(true);
        handlerFragment.changeToFragment(R.id.fragment_division_recycler);
    }

    private void setUpToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hockey");
        toolbar.setSubtitle("Pasion");
        toolbar.inflateMenu(R.menu.menu_main);
    }

    private void setUpDrawer(){
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment,drawerLayout,toolbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //handlerFragment.onBackPressed();
    }

    public void showProgress(boolean isVisible){
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}

