package com.wip.hockey.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DivisionAdapter;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.databinding.FragmentListDivisionBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.viewModel.DivisionViewModel;

import java.util.List;

public class ListDivisionFragment extends BaseFragment{
    private final String TAG = ListDivisionFragment.class.toString();
    private DivisionAdapter divisionAdapter;
    private FragmentListDivisionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_division, container, false);

        divisionAdapter = new DivisionAdapter(this);

        binding.fragmentDivisionRecycler.setAdapter(divisionAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DivisionViewModel divisionViewModel = ViewModelProviders.of(this).get(DivisionViewModel.class);

        observeViewModel(divisionViewModel);
    }

    private void observeViewModel(DivisionViewModel viewModel) {
        // Update the list when the data changes
        final MainActivity mainActivity = (MainActivity) this.getContext();
        viewModel.getDivisionListObservable().observe(this, new Observer<List<Division>>() {
            @Override
            public void onChanged(@Nullable List<Division> divisions) {
                mainActivity.showProgress(true);
                if (divisions != null) {
                    divisionAdapter.setDivisionList(divisions);
                    mainActivity.showProgress(false);
                }
            }
        });
    }


    public String getTAG(){
        return TAG;
    }

    public void onClick(Division division) {
        Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_sub_division_recycler);
        selected.setSelectedFrom(division);
    }
}
