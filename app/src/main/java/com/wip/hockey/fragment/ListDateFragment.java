package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.DateAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListDateBinding;
import com.wip.hockey.model.Date;
import com.wip.hockey.viewModel.DateViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListDateFragment extends BaseFragment
        implements Tageable {

    private final String TAG = ListDateFragment.class.toString();
    private DateAdapter dateAdapter;
    private FragmentListDateBinding binding;
    private DateViewModel dateViewModel;
    private ViewType type;
    private DateObserver observer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dateViewModel = ViewModelProviders.of(this).get(DateViewModel.class);
        dateViewModel.setCategoryId(this.getArguments().getInt(Constants.PARENT_ID));
        this.type = (ViewType) this.getArguments().getSerializable(Constants.OPERATION_TYPE);

        subscribeUi(dateViewModel);
    }

    private void subscribeUi(DateViewModel dateViewModel) {
        dateViewModel.getDates().subscribe(observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_date, container, false);
        dateAdapter = new DateAdapter(this);
        binding.fragmentPagerDate.setAdapter(dateAdapter);
        binding.fragmentPagerDate.addOnPageChangeListener(dateAdapter);

        this.observer = new DateObserver();

        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }


    @Override
    public void showMessage(String message) {
       // Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    public void setTitle(String title){
        binding.toolbarDate.toolbarTitle.setText(title);
    }


    private class DateObserver implements Observer<List<Date>> {

        @Override
        public void onSubscribe(Disposable d) {
            showMessage("Subscribe");
        }

        @Override
        public void onNext(List<Date> dates) {
            showMessage("Next");
            dateAdapter.setDateList(dates);
            binding.fragmentPagerDate.setCurrentItem(dates.size(),false);
            showProgress(false);
        }

        @Override
        public void onError(Throwable e) {
            showMessage("Error");
            showProgress(false);
        }

        @Override
        public void onComplete() {

        }
    }
}
