package com.wip.hockey.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wip.hockey.R;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Division;

import java.util.ArrayList;
import java.util.List;

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
        holder.star.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SharedPreferences sharedPrefs = context.getSharedPreferences("Favorite", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                Gson gson = new Gson();
                ArrayList<Category> listaDatos = new ArrayList();
                listaDatos.add(currentObj);
                Log.d(TAG,listaDatos.toString());
                String json = gson.toJson(listaDatos);

                editor.putString("Favorite", json);
                editor.commit();
                Toast.makeText(context,"funciona",Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.handlerFragment.setFragment(R.id.fragment_match_recycler,currentObj.getMatch().getData());
            }
        });
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
