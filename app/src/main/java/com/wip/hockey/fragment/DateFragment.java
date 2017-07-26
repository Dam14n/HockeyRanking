package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DataListener;
import com.wip.hockey.adapter.DateAdapter;
import com.wip.hockey.api.Api;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Date;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by djorda on 10/07/2017.
 */

public class DateFragment extends BaseFragment implements  ISelected{

    @BindView(R.id.pager)
    ViewPager pager;
    private DataListener mListener;
    private Category parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);

        ButterKnife.bind(this,view);

        DateAdapter adapter = new DateAdapter(this.getContext(),view);
        mListener = adapter;
        pager.setAdapter(adapter);

        if(this.parent != null) {
            this.getData();
        }

        return view;
    }

    private void getData(){
        ServiceApi serviceApi = Api.getInstance();
        serviceApi.getDatesByCategory(new Callback<List<Date>>() {
            @Override
            public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {
                pager.setCurrentItem(response.body().size(),false);
                mListener.dataHasChanged(response.body());
            }

            @Override
            public void onFailure(Call<List<Date>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getContext(), "Error al buscar datos", Toast.LENGTH_SHORT).show();
            }
        },this.parent.getId());
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_dates;
    }

    @Override
    public void setParent(Object object) {
        this.parent = (Category) object;
        this.getData();
    }
}
