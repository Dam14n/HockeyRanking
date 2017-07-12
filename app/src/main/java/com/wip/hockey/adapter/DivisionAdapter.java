package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.api.ApiRealState;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by djorda on 11/05/2017.
 */

public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.MyViewHolder>{

    private static final String TAG = DivisionAdapter.class.getSimpleName();
    private List<Division> mData;
    private LayoutInflater mInflater;
    private Context context;

    public DivisionAdapter(Context context,List<Division> mData ) {
        this.mData = mData;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewGroup row = (ViewGroup) mInflater.inflate(R.layout.list_item_division,parent,false);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        final Division currentObj = mData.get(position);
        holder.setData(currentObj,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.progressBar.setVisibility(View.VISIBLE);
                ServiceApi serviceApi = ApiRealState.getInstance();
                serviceApi.getSubDivisionsByDivision(new Callback<List<SubDivision>>() {
                    @Override
                    public void onResponse(Call<List<SubDivision>> call, Response<List<SubDivision>> response) {
                        HandlerFragment.getInstance().changeToFragment(R.id.fragment_sub_division_recycler, response.body());
                        MainActivity.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<List<SubDivision>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                },currentObj.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView division;

        public  MyViewHolder(View itemView) {
            super(itemView);

            division = (TextView) itemView.findViewById(R.id.division);
        }

        public void setData(Division current, int position) {
            Log.d(TAG, "Division: " + current.getName());

            this.division.setText(current.getName());
         }

    }
}
