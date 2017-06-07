package com.wip.hockey.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.CategoryFragment;
import com.wip.hockey.fragment.SubDivisionFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;

import java.util.List;

/**
 * Created by djorda on 11/05/2017.
 */

public class SubDivisionAdapter extends RecyclerView.Adapter<SubDivisionAdapter.MyViewHolder> {

    private static final String TAG = SubDivisionAdapter.class.getSimpleName();
    private List<SubDivision> mData;
    private LayoutInflater mInflater;
    private Context context;
    private Fragment fragment;

    public SubDivisionAdapter(Context context, List<SubDivision> data){
        this.context = context;
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewGroup row = (ViewGroup) mInflater.inflate(R.layout.list_item_sub_division,parent,false);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);

        final SubDivision currentObj = mData.get(position);
        holder.setData(currentObj,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("id",200);

                Fragment fragment = null;

                fragment = new CategoryFragment(currentObj.getCategories().getData());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack("Category "+holder.subDivision.getText().toString());

                fragmentTransaction.commit();



            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subDivision;

        public  MyViewHolder(View itemView) {
            super(itemView);

            subDivision = (TextView) itemView.findViewById(R.id.sub_division);
        }

        public void setData(SubDivision current, int position) {
            Log.d(TAG, "Division: " + current.getName());

            this.subDivision.setText(current.getName());
         }

    }
}
