package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DataListener;
import com.wip.hockey.adapter.DivisionAdapter;
import com.wip.hockey.api.Api;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.model.Division;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DivisionFragment extends BaseFragment{

    @BindView(R.id.fragment_division_recycler)
    RecyclerView recyclerView;

    private DataListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);
        ButterKnife.bind(this,view);

        DivisionAdapter adapter = new DivisionAdapter(this.getContext());
        mListener = adapter;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        this.getData();

        return view;
    }

    private void getData(){
        ServiceApi serviceApi = Api.getInstance();
        serviceApi.getDivisions(new Callback<List<Division>>() {
            @Override
            public void onResponse(Call<List<Division>> call, Response<List<Division>> response) {
                mListener.dataHasChanged(response.body());
                mListener.updateFinish();
            }

            @Override
            public void onFailure(Call<List<Division>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getContext(), "Error al buscar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_list_division;
    }

}
