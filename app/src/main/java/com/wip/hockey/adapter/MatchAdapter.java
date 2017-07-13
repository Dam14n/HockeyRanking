package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.api.Api;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by djorda on 11/05/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {

    private static final String TAG = MatchAdapter.class.getSimpleName();
    private List<Match> mData;
    private LayoutInflater mInflater;
    private MainActivity context;

    public MatchAdapter(Context context, List<Match> data){
        if (data == null){
            data = new ArrayList<Match>();
        }
        this.context = (MainActivity) context;
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewGroup row = (ViewGroup) mInflater.inflate(R.layout.list_item_match,parent,false);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        if(!mData.isEmpty()) {
            Match currentObj = mData.get(position);
            holder.setData(currentObj, position);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textLocalTeam, textEnemyTeam,localTeamGoals,enemyTeamGoals;
        ImageView imgLocalTeam,imgEnemyTeam;
        int position;
        Match current;
        Team localTeam;
        Team enemyTeam;

        public  MyViewHolder(View itemView) {
            super(itemView);

            LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.result);
            localTeamGoals = (TextView) layout.findViewById(R.id.local_team_goals);
            enemyTeamGoals = (TextView) layout.findViewById(R.id.enemy_team_goals);
            textLocalTeam = (TextView) itemView.findViewById(R.id.local_team);
            textEnemyTeam = (TextView) itemView.findViewById(R.id.enemy_team);
            imgLocalTeam = (ImageView) itemView.findViewById(R.id.img_local_team);
            imgEnemyTeam = (ImageView) itemView.findViewById(R.id.img_enemy_team);
        }

        public void setData(Match current, int position) {
           Log.d(TAG, "Equipo local : " + current.getLocalTeamId()+ ", Equipo visitante : " + current.getEnemyTeamId());

            Api api = Api.getInstance();
            api.getTeamByMatch(new Callback<Team>() {
                @Override
                public void onResponse(Call<Team> call, Response<Team> response) {
                    localTeam = response.body();
                    textLocalTeam.setText(localTeam.getName());
                }

                @Override
                public void onFailure(Call<Team> call, Throwable t) {

                }
            },current.getId(),current.getLocalTeamId());
            api.getTeamByMatch(new Callback<Team>() {
                @Override
                public void onResponse(Call<Team> call, Response<Team> response) {
                    enemyTeam = response.body();
                    textEnemyTeam.setText(enemyTeam.getName());
                }

                @Override
                public void onFailure(Call<Team> call, Throwable t) {

                }
            },current.getId(),current.getEnemyTeamId());

            //this.imgLocalTeam.setImageResource(current.getLogoLocalTeam());
            //this.imgEnemyTeam.setImageResource(current.getLogoEnemyTeam());
            this.enemyTeamGoals.setText(Integer.toString(current.getEnemyGoalsIds().size()));
            this.localTeamGoals.setText(Integer.toString(current.getLocalGoalsIds().size()));
            this.position = position;
            this.current = current;

        }

    }
}
