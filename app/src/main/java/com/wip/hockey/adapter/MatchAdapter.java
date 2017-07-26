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
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> implements DataListener{

    private static final String TAG = MatchAdapter.class.getSimpleName();
    private List<Match> mData;
    private LayoutInflater mInflater;
    private MainActivity context;

    public MatchAdapter(Context context){
        this.context = (MainActivity) context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewGroup row = (ViewGroup) mInflater.inflate(R.layout.list_item_match,parent,false);
        return new MyViewHolder(row,this);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        if(mData != null) {
            Match currentObj = mData.get(position);
            holder.setData(currentObj, position);
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public void dataHasChanged(List list) {
        this.mData = list;
        this.notifyDataSetChanged();
        if(list.isEmpty()){
            updateFinish();
        }
    }

    @Override
    public void updateFinish() {
        context.showProgress(false);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private DataListener mListener;
        TextView textLocalTeam, textEnemyTeam,localTeamGoals,enemyTeamGoals;
        ImageView imgLocalTeam,imgEnemyTeam;
        int position;
        Match current;
        Team localTeam;
        Team enemyTeam;


        public  MyViewHolder(View itemView,DataListener mListener) {
            super(itemView);
            this.mListener = mListener;
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
                    textLocalTeam.setText(localTeam != null ? localTeam.getName() : "");
                    finishUpdate(textLocalTeam,textEnemyTeam);
                }

                @Override
                public void onFailure(Call<Team> call, Throwable t) {

                }
            },current.getId(),current.getLocalTeamId());
            api.getTeamByMatch(new Callback<Team>() {
                @Override
                public void onResponse(Call<Team> call, Response<Team> response) {
                    enemyTeam = response.body();
                    textEnemyTeam.setText(enemyTeam != null ? enemyTeam.getName() : "");
                    finishUpdate(textLocalTeam,textEnemyTeam);
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

        private void finishUpdate(TextView textLocalTeam, TextView textEnemyTeam){
            if (!textEnemyTeam.getText().equals("") && !textLocalTeam.getText().equals("")){
                mListener.updateFinish();
            }
        }

    }
}
