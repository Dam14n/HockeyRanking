package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.model.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djorda on 11/05/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {

    private static final String TAG = MatchAdapter.class.getSimpleName();
    private List<Match> mData;
    private LayoutInflater mInflater;

    public MatchAdapter(Context context, List<Match> data){
        if (data == null){
            data = new ArrayList<Match>();
        }
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

        Match currentObj = mData.get(position);
        holder.setData(currentObj,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView localTeam, enemyTeam;
        ImageView imgLocalTeam,imgEnemyTeam;
        int position;
        Match current;

        public  MyViewHolder(View itemView) {
            super(itemView);

            localTeam = (TextView) itemView.findViewById(R.id.local_team);
            enemyTeam = (TextView) itemView.findViewById(R.id.enemy_team);
            imgLocalTeam = (ImageView) itemView.findViewById(R.id.img_local_team);
            imgEnemyTeam = (ImageView) itemView.findViewById(R.id.img_enemy_team);
        }

        public void setData(Match current, int position) {
/*            Log.d(TAG, "Equipo local : " + current.getNameLocalTeam()+ ", Equipo visitante : " + current.getNameEnemyTeam());

            this.localTeam.setText(current.getNameLocalTeam());
            this.enemyTeam.setText(current.getNameEnemyTeam());
            this.imgLocalTeam.setImageResource(current.getLogoLocalTeam());
            this.imgEnemyTeam.setImageResource(current.getLogoEnemyTeam());
            this.position = position;
            this.current = current;
            */
        }

    }
}
