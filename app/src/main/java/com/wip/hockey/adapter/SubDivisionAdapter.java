package com.wip.hockey.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemSubDivisionBinding;
import com.wip.hockey.fragment.ListSubDivisionFragment;
import com.wip.hockey.model.SubDivision;

import java.util.List;

/**
 * Created by djorda on 11/05/2017.
 */

public class SubDivisionAdapter extends RecyclerView.Adapter<SubDivisionAdapter.MyViewHolder>{

    private static final String TAG = DivisionAdapter.class.getSimpleName();
    private final ListSubDivisionFragment mFragment;
    private List<SubDivision> subDivisionList;

    public SubDivisionAdapter(ListSubDivisionFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public SubDivisionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemSubDivisionBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_sub_division,
                        parent, false);

        binding.setHandler(mFragment);

        return new SubDivisionAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SubDivisionAdapter.MyViewHolder holder, int position) {
        holder.binding.setSubDivision(subDivisionList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return subDivisionList != null ? subDivisionList.size() : 0;
    }


    public void setSubDivisionList(final List<SubDivision> subDivisionList) {
        if (this.subDivisionList == null) {
            this.subDivisionList = subDivisionList;
            notifyItemRangeInserted(0, subDivisionList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return SubDivisionAdapter.this.subDivisionList.size();
                }

                @Override
                public int getNewListSize() {
                    return subDivisionList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return SubDivisionAdapter.this.subDivisionList.get(oldItemPosition).getId() ==
                            subDivisionList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    SubDivision subDivision = subDivisionList.get(newItemPosition);
                    SubDivision old = subDivisionList.get(oldItemPosition);
                    return subDivision.getId() == old.getId();
                }
            });
            this.subDivisionList = subDivisionList;
            result.dispatchUpdatesTo(this);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        final ListItemSubDivisionBinding binding;

        public MyViewHolder(ListItemSubDivisionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
