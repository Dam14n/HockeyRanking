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
import com.wip.hockey.adapter.SubDivisionAdapter;
import com.wip.hockey.api.Api;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubDivisionFragment extends BaseFragment implements ISelected {

    @BindView(R.id.fragment_sub_division_recycler)
    RecyclerView recyclerView;

    private DataListener mListener;
    private Division parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);

        ButterKnife.bind(this,view);

        SubDivisionAdapter adapter = new SubDivisionAdapter(this.getContext());
        mListener = adapter;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        if(this.parent != null) {
            this.getData();
        }

        return view;
    }

    private void getData(){
        ServiceApi serviceApi = Api.getInstance();
        serviceApi.getSubDivisionsByDivision(new Callback<List<SubDivision>>() {
            @Override
            public void onResponse(Call<List<SubDivision>> call, Response<List<SubDivision>> response) {
                mListener.dataHasChanged(response.body());
                mListener.updateFinish();
            }

            @Override
            public void onFailure(Call<List<SubDivision>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getContext(), "Error al buscar datos", Toast.LENGTH_SHORT).show();
            }
        },this.parent.getId());
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_list_sub_division;
    }

    @Override
    public void setParent(Object object) {
        this.parent = (Division) object;
        this.getData();
    }
}
