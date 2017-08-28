package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wip.hockey.R;
import com.wip.hockey.adapter.FixtureFavoriteAdapter;
import com.wip.hockey.adapter.PositionFavoriteAdapter;
import com.wip.hockey.app.Constants;
import com.wip.hockey.databinding.FragmentListFavoriteBinding;
import com.wip.hockey.handler.HandlerFragment;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Favorite;
import com.wip.hockey.model.User;
import com.wip.hockey.viewModel.FavoriteViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListFavoriteFragment extends BaseFragment implements Tageable {

    private final String TAG = ListFavoriteFragment.class.toString();
    private FavoriteViewModel favoriteViewModel;
    private FixtureFavoriteAdapter fixtureFavoriteAdapter;
    private FragmentListFavoriteBinding binding;
    private PositionFavoriteAdapter positionFavoriteAdapter;
    private User user;
    private List<Favorite> favorites;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        this.user = (User) this.getArguments().getSerializable(Constants.USER);

        fixtureFavoriteAdapter = new FixtureFavoriteAdapter(this,this.user,favoriteViewModel);
        addFixtureFavorites(fixtureFavoriteAdapter);
        binding.fragmentFavoriteFixtureRecycler.setAdapter(fixtureFavoriteAdapter);

        positionFavoriteAdapter = new PositionFavoriteAdapter(this,this.user,favoriteViewModel);
        addFavoritesPositions(positionFavoriteAdapter);
        binding.fragmentFavoritePositionsRecycler.setAdapter(positionFavoriteAdapter);


    }

    private void addFavoritesPositions(PositionFavoriteAdapter positionFavoriteAdapter) {
        favoriteViewModel.getPositionsFavoritesByUserId(this.user.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favorites -> {
                    positionFavoriteAdapter.setFavoriteList(favorites);
                },throwable -> Log.d(TAG, "No soy favorito."));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_favorite, container, false);

        return binding.getRoot();
    }

    private void addFixtureFavorites(FixtureFavoriteAdapter fixtureFavoriteAdapter) {
        favoriteViewModel.getFixturesFavoritesByUserId(this.user.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favorites -> {
                    fixtureFavoriteAdapter.setFavoriteList(favorites);
                },throwable -> Log.d(TAG, "No soy favorito."));
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Category category) {
        showProgress(true);
        BaseFragment fragment;
        Bundle bundle = new Bundle();
        if (fixtureFavoriteAdapter.isFixtureType(category)) {
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_pager_date);
            bundle.putSerializable(Constants.OPERATION_TYPE,ViewType.FIXTURE_VIEW);
        }else{
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_board_recycler);
            bundle.putSerializable(Constants.OPERATION_TYPE,ViewType.POSITIONS_VIEW);
        }
        bundle.putInt(Constants.PARENT_ID,category.getId());
        fragment.setArguments(bundle);
    }

}
