package com.wip.hockey.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemMatchBinding;
import com.wip.hockey.fragment.ListMatchFragment;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.Team;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder>{

    private static final String TAG = MatchAdapter.class.getSimpleName();
    private final ListMatchFragment mFragment;
    private List<Match> matchList;
    private List<Team> teamList;
    ListItemMatchBinding binding;

    public MatchAdapter(ListMatchFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public MatchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_match,
                        parent, false);

        return new MatchAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MatchAdapter.MyViewHolder holder, int position) {
        Match match = matchList.get(position);
        holder.binding.setMatch(match);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return matchList != null ? matchList.size() : 0;
    }

    public void setMatchList(final List<Match> matchList) {
        if (this.matchList == null) {
            this.matchList = matchList;
            notifyItemRangeInserted(0, matchList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MatchAdapter.this.matchList.size();
                }

                @Override
                public int getNewListSize() {
                    return matchList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MatchAdapter.this.matchList.get(oldItemPosition).getId() ==
                            matchList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Match match = matchList.get(newItemPosition);
                    Match old = matchList.get(oldItemPosition);
                    return match.getId() == old.getId();
                }
            });
            this.matchList = matchList;
            result.dispatchUpdatesTo(this);
        }

    }

    @BindingAdapter({"bind:imgRes"})
    public static void loadImage(ImageView view, String image) {
        if (image != null)
            view.setImageBitmap(transformImage(image));
    }

    private static Bitmap transformImage(String image) {
        byte[] imgBytes = Base64.decode(image.getBytes(),Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        final ListItemMatchBinding binding;

        public MyViewHolder(ListItemMatchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
