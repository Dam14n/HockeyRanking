package com.wip.hockey.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemDivisionBinding;
import com.wip.hockey.fragment.Division.ListDivisionFragment;
import com.wip.hockey.model.Division;

import java.util.List;

public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.MyViewHolder> {

    private static final String TAG = DivisionAdapter.class.getSimpleName();
    private final ListDivisionFragment mFragment;
    private List<Division> divisionList;

    public DivisionAdapter(ListDivisionFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemDivisionBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_division,
                        parent, false);

        binding.setHandler(mFragment);

        return new MyViewHolder(binding);
 }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.binding.setDivision(divisionList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return divisionList != null ? divisionList.size() : 0;
    }


    public void setDivisionList(final List<Division> divisionList) {
        if (this.divisionList == null) {
            this.divisionList = divisionList;
            notifyItemRangeInserted(0, divisionList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return DivisionAdapter.this.divisionList.size();
                }

                @Override
                public int getNewListSize() {
                    return divisionList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return DivisionAdapter.this.divisionList.get(oldItemPosition).getId() ==
                            divisionList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Division division = divisionList.get(newItemPosition);
                    Division old = divisionList.get(oldItemPosition);
                    return division.getId() == old.getId();
                }
            });
            this.divisionList = divisionList;
            result.dispatchUpdatesTo(this);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        final ListItemDivisionBinding binding;

        public MyViewHolder(ListItemDivisionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
