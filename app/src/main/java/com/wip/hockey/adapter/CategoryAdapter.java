package com.wip.hockey.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wip.hockey.R;
import com.wip.hockey.api.ApiRealState;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Division;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by djorda on 11/05/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private static final String TAG = CategoryAdapter.class.getSimpleName();
    private List<Category> mData;
    private LayoutInflater mInflater;
    private Context context;
    private Fragment fragment;

    public CategoryAdapter(Context context, List<Category> data){
        this.context = context;
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewGroup row = (ViewGroup) mInflater.inflate(R.layout.list_item_category,parent,false);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);

        final Category currentObj = mData.get(position);
        holder.setData(currentObj,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceApi serviceApi = ApiRealState.getInstance();
                serviceApi.getDatesByCategory(new Callback<List<Date>>() {
                    @Override
                    public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {
                        MainActivity.handlerFragment.setFragment(R.id.pager,response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Date>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                },currentObj.getId());
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
                MainActivity.handlerFragment.setFragment(R.id.fragment_match_recycler,MainActivity.repository.getMatches(currentObj));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
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
