package com.wip.hockey.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.databinding.ListItemBoardBinding;
import com.wip.hockey.fragment.ListBoardFragment;
import com.wip.hockey.model.Board;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {

    private static final String TAG = BoardAdapter.class.getSimpleName();
    private final ListBoardFragment mFragment;
    private List<Board> boardList;

    public BoardAdapter(ListBoardFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public BoardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBoardBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_board,
                        parent, false);

        binding.setHandler(mFragment);

        return new BoardAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BoardAdapter.MyViewHolder holder, int position) {
        holder.binding.setBoard(boardList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return boardList != null ? boardList.size() : 0;
    }


    public void setBoardList(final List<Board> boardList) {
        if (this.boardList == null) {
            this.boardList = boardList;
            notifyItemRangeInserted(0, boardList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return BoardAdapter.this.boardList.size();
                }

                @Override
                public int getNewListSize() {
                    return boardList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return BoardAdapter.this.boardList.get(oldItemPosition).getId() ==
                            boardList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Board board = boardList.get(newItemPosition);
                    Board old = boardList.get(oldItemPosition);
                    return board.getId() == old.getId();
                }
            });
            this.boardList = boardList;
            result.dispatchUpdatesTo(this);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        final ListItemBoardBinding binding;

        public MyViewHolder(ListItemBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
