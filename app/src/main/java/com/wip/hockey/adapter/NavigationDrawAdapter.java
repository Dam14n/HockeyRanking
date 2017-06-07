package com.wip.hockey.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.DivisionFragment;
import com.wip.hockey.fragment.SubDivisionFragment;
import com.wip.hockey.model.Division;
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
                if ( holder.title.getText() == "Inicio") {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",100);

                    Fragment fragment = null;

                    fragment = new DivisionFragment(Division.getData());
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.replace(R.id.fragment, fragment);
                    fragmentTransaction.addToBackStack(holder.title.getText().toString());

                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(context, holder.title.getText().toString(), Toast.LENGTH_SHORT).show();
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
