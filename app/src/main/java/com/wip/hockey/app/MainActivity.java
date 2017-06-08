package com.wip.hockey.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wip.hockey.R;
import com.wip.hockey.fragment.NavigationDrawerFragment;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static HandlerFragment handlerFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpDrawer();
        setUpFragment();

    }

    private void setUpFragment() {
        handlerFragment = new HandlerFragment(this);
        handlerFragment.setFragment(R.id.fragment_division_recycler,Division.getData());
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

}

