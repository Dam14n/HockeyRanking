package com.wip.hockey.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DataListener;
import com.wip.hockey.adapter.MatchAdapter;
import com.wip.hockey.api.Api;
import com.wip.hockey.api.ServiceApi;
import com.wip.hockey.model.Date;
import com.wip.hockey.model.Match;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchFragment extends BaseFragment implements ISelected{

    @BindView(R.id.fragment_match_recycler)
    RecyclerView recyclerView;
    private Date parent;
    private DataListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);

        ButterKnife.bind(this,view);

        MatchAdapter adapter = new MatchAdapter(this.getContext());
        mListener = adapter;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        if(this.parent != null){
            this.getData();
        }

        return view;
    }

    private void getData(){
        ServiceApi serviceApi = Api.getInstance();
        serviceApi.getMatchesByDate(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mListener.dataHasChanged(response.body());
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        },this.parent.getId());
    }

    @Override
    public String getTAG() {
        return null;
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_list_match;
    }

    @Override
    public void setParent(Object object) {
        this.parent = (Date) object;
        this.getData();
    }
}
