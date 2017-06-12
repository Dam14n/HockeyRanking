package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.model.NavigationDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by djorda on 12/05/2017.
 */

public class NavigationDrawAdapter extends RecyclerView.Adapter<NavigationDrawAdapter.MyViewHolder> {

    private List<NavigationDrawerItem> mDataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawAdapter(Context context, List<NavigationDrawerItem> mDataList) {
        this.mDataList = mDataList;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NavigationDrawerItem current = mDataList.get(position);

        holder.imgIcon.setImageResource(current.getImageId());
        holder.title.setText(current.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (holder.title.getText().toString()){
                    case "Inicio":
                        MainActivity.handlerFragment.setFragment(R.id.fragment_division_recycler,MainActivity.repository.getDivisions());
                        break;
                    case "Favoritos":
                        MainActivity.handlerFragment.setFragment(R.id.fragment_favorite_recycler,null);
                        break;
                    case "Borrar Favoritos":
                        MainActivity.favoriteManager.removeAll();
                        MainActivity.handlerFragment.updateFragment();
                        Toast.makeText(context,"Se han removido todos los favoritos!!",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }
    }
}
