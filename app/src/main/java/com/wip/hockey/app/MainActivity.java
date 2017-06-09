package com.wip.hockey.app;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.wip.hockey.R;
import com.wip.hockey.fragment.NavigationDrawerFragment;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.repository.Repository;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static HandlerFragment handlerFragment;
    public static FavoriteManager favoriteManager;
    public static Repository repository;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new Repository();
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpDrawer();
        setUpFragment();
        setUpFavorite();
    }

    private void setUpFavorite() {
        favoriteManager = new FavoriteManager(this);
    }

    private void setUpFragment() {
        handlerFragment = new HandlerFragment(this);
        handlerFragment.setFragment(R.id.fragment_division_recycler,repository.getDivisions());
    }

    private void setUpToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hockey");
        toolbar.setSubtitle("Ranking");
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
        handlerFragment.onBackPressed();
    }
}

