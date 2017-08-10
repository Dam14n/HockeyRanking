package com.wip.hockey.fragment.SubDivision;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wip.hockey.R;
import com.wip.hockey.adapter.SubDivisionAdapter;
import com.wip.hockey.databinding.FragmentListSubDivisionBinding;
import com.wip.hockey.fragment.BaseFragment;
import com.wip.hockey.fragment.Lifecycle;
import com.wip.hockey.fragment.Selected;
import com.wip.hockey.fragment.Tageable;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.viewModel.SubDivisionViewModel;

import java.util.List;

public class ListSubDivisionFragment extends BaseFragment implements Selected, Tageable,SubDivisionContract.View {

    private final String TAG = ListSubDivisionFragment.class.toString();
    private SubDivisionAdapter subDivisionAdapter;
    private FragmentListSubDivisionBinding binding;
    private SubDivisionContract.ViewModel subDivisionViewModel;
    private Division division;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subDivisionViewModel = ViewModelProviders.of(this).get(SubDivisionViewModel.class);
        subDivisionViewModel.setDivision(this.division);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_sub_division, container, false);

        subDivisionAdapter = new SubDivisionAdapter(this);

        binding.fragmentSubDivisionRecycler.setAdapter(subDivisionAdapter);

        setupRefreshLayout();

        return binding.getRoot();
    }

    private void setupRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener(() -> subDivisionViewModel.getSubDivisions());
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(SubDivision subDivision) {
        showProgress(true);
        Selected selected = (Selected) HandlerFragment.getInstance().changeToFragment(R.id.fragment_category_recycler);
        selected.setSelectedFrom(subDivision);
        Lifecycle.View view = (Lifecycle.View) selected;
        view.setType(this.getType());
    }

    @Override
    public void setSelectedFrom(Object object) {
        this.division = (Division) object;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return this.subDivisionViewModel;
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
    public void setSubDivisions(List<SubDivision> subDivisions) {
        subDivisionAdapter.setSubDivisionList(subDivisions);
    }
}
