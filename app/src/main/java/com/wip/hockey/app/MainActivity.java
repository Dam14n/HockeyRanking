package com.wip.hockey.app;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DivisionAdapter;
import com.wip.hockey.adapter.MatchAdapter;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Match;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpDrawer();
        setUpRecyclewrView();

    }

    private void setUpToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Welcome !");
        toolbar.setSubtitle("Folks !");

        toolbar.inflateMenu(R.menu.menu_main);
    }

    private void setUpDrawer(){

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment,drawerLayout,toolbar);
    }
    private void setUpRecyclewrView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //MatchAdapter adapter = new MatchAdapter(this, Match.getData());
        DivisionAdapter adapter = new DivisionAdapter(this, Division.getData());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}

