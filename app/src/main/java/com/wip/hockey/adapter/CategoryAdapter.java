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
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.fragment.ISelected;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Category;

import java.util.List;

/**
 * Created by djorda on 11/05/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> implements DataListener {

    private static final String TAG = CategoryAdapter.class.getSimpleName();
    private List<Category> mData;
    private LayoutInflater mInflater;
    private MainActivity context;

    public CategoryAdapter(Context context){
        this.context = (MainActivity)context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewGroup row = (ViewGroup) mInflater.inflate(R.layout.list_item_category,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);

        final Category currentObj = mData.get(position);
        holder.setData(currentObj,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showProgress(true);
                ISelected childFragment = (ISelected) HandlerFragment.getInstance().changeToFragment(R.id.pager);
                childFragment.setParent(currentObj);
            }
        });
        Log.d(TAG,"El objeto "+currentObj.getName());
       /* if(currentObj.isFavorite()){
            holder.star.setImageResource(R.drawable.button_pressed);
        }
        holder.star.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (currentObj.isFavorite()){
                    currentObj.setFavorite(false);
                    MainActivity.favoriteManager.deleteFavorite(currentObj);
                    holder.star.setImageResource(R.drawable.button_normal);
                }else {
                    currentObj.setFavorite(true);
                    MainActivity.favoriteManager.saveFavorite(currentObj);
                    holder.star.setImageResource(R.drawable.button_pressed);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.handlerFragment.changeToFragment(R.id.fragment_match_recycler,MainActivity.repository.getMatches(currentObj));
            }
        });*/
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

        TextView category;
        ImageView star;

        public  MyViewHolder(View itemView) {
            super(itemView);

            category = (TextView) itemView.findViewById(R.id.category);
            star = (ImageView) itemView.findViewById(R.id.favorite);
        }

        public void setData(Category current, int position) {
            Log.d(TAG, "Category: " + current.getName());

            this.category.setText(current.getName());
         }

    }
}
