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
import com.wip.hockey.adapter.SubDivisionAdapter;
import com.wip.hockey.app.MainActivity;
import com.wip.hockey.databinding.FragmentListSubDivisionBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.viewModel.SubDivisionViewModel;

import java.util.List;

public class ListSubDivisionFragment extends BaseFragment implements Selected {

    private final String TAG = ListSubDivisionFragment.class.toString();
    private SubDivisionAdapter subDivisionAdapter;
    private FragmentListSubDivisionBinding binding;
    private Division parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_sub_division, container, false);

        subDivisionAdapter = new SubDivisionAdapter(this);

        binding.fragmentSubDivisionRecycler.setAdapter(subDivisionAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SubDivisionViewModel subDivisionViewModel = ViewModelProviders.of(this).get(SubDivisionViewModel.class);
        subDivisionViewModel.setDivision(this.parent);
        observeViewModel(subDivisionViewModel);
    }

    private void observeViewModel(final SubDivisionViewModel viewModel) {
        // Update the list when the data changes
        final MainActivity mainActivity = (MainActivity) this.getContext();
        viewModel.getSubDivisionListObservable().observe(this, new Observer<List<SubDivision>>() {
            @Override
            public void onChanged(@Nullable List<SubDivision> subDivisions) {
                mainActivity.showProgress(true);
                if (subDivisions != null) {
                    subDivisionAdapter.setSubDivisionList(subDivisions);
                }
                mainActivity.showProgress(false);
            }
        });
    }


    public String getTAG(){
        return TAG;
    }

    public void onClick(SubDivision subDivision) {
        Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_category_recycler);
        selected.setSelectedFrom(subDivision);
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.parent = (Division) object;
    }
}
