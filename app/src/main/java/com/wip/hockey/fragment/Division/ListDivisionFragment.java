package com.wip.hockey.fragment.Division;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DivisionAdapter;
import com.wip.hockey.databinding.FragmentListDivisionBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.fragment.Tageable;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.viewModel.DivisionViewModel;

import java.util.List;

public class ListDivisionFragment extends BaseFragment implements Tageable,DivisionContract.View {

    private final String TAG = ListDivisionFragment.class.toString();
    private DivisionAdapter divisionAdapter;
    private FragmentListDivisionBinding binding;
    private DivisionContract.ViewModel divisionViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        divisionViewModel = ViewModelProviders.of(this).get(DivisionViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_division, container, false);

        divisionAdapter = new DivisionAdapter(this);

        binding.fragmentDivisionRecycler.setAdapter(divisionAdapter);

        setupRefreshLayout();

        return binding.getRoot();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> divisionViewModel.getDivisions());
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Division division) {
        showProgress(true);
        Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_sub_division_recycler);
        selected.setSelectedFrom(division);
        Lifecycle.View view = (Lifecycle.View) selected;
        view.setType(this.getType());
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return this.divisionViewModel;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        if (binding.swipeRefresh != null) {
            binding.swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void setDivisions(List<Division> divisions) {
        divisionAdapter.setDivisionList(divisions);
    }
}
