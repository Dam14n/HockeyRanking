package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.ISelected;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;

import java.util.List;

/**
 * Created by djorda on 11/05/2017.
 */

public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.MyViewHolder> implements DataListener{

    private static final String TAG = DivisionAdapter.class.getSimpleName();
    private List<Division> mData;
    private LayoutInflater mInflater;
    private MainActivity context;

    public DivisionAdapter(Context context) {
        this.context = (MainActivity) context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewGroup row = (ViewGroup) mInflater.inflate(R.layout.list_item_division,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        final Division currentObj = mData.get(position);
        holder.setData(currentObj,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showProgress(true);
                ISelected childFragment = (ISelected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_sub_division_recycler);
                childFragment.setParent(currentObj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public void dataHasChanged(List list) {
        this.mData = list;
        this.notifyDataSetChanged();
    }

    @Override
    public void updateFinish() {
        context.showProgress(false);
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
