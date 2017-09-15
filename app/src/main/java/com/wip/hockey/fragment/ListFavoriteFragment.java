package com.wip.hockey.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import com.wip.hockey.viewModel.factory.DivisionViewModelFactory;
import com.wip.hockey.viewModel.factory.FavoriteViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListFavoriteFragment extends BaseFragment implements Tageable {

    private final String TAG = ListFavoriteFragment.class.toString();
    private FavoriteViewModel favoriteViewModel;
    private FixtureFavoriteAdapter fixtureFavoriteAdapter;
    private FragmentListFavoriteBinding binding;
    private PositionFavoriteAdapter positionFavoriteAdapter;
    private User user;

    @Inject
    FavoriteViewModelFactory favoriteViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        favoriteViewModel = ViewModelProviders.of(this, favoriteViewModelFactory).get(FavoriteViewModel.class);

        this.user = (User) this.getArguments().getSerializable(Constants.USER);

        fixtureFavoriteAdapter = new FixtureFavoriteAdapter(this);
        favoriteViewModel.getPositionsFavoritesByUserId(this.user.getId())
                .observe(this, favorites -> positionFavoriteAdapter.setFavoriteList(favorites) );
        binding.fragmentFavoriteFixtureRecycler.setAdapter(fixtureFavoriteAdapter);

        positionFavoriteAdapter = new PositionFavoriteAdapter(this);
        favoriteViewModel.getFixturesFavoritesByUserId(this.user.getId())
                .observe(this, favorites -> fixtureFavoriteAdapter.setFavoriteList(favorites));
        binding.fragmentFavoritePositionsRecycler.setAdapter(positionFavoriteAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_favorite, container, false);

        return binding.getRoot();
    }

    @Override
    public String getTAG(){
        return TAG;
    }

    public void onClick(Favorite favorite) {
        showProgress(true);
        BaseFragment fragment;
        Bundle bundle = new Bundle();
        if (favorite.getFavoriteType() == ViewType.FIXTURE_VIEW) {
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_pager_date);
            bundle.putSerializable(Constants.OPERATION_TYPE,ViewType.FIXTURE_VIEW);
        }else{
            fragment = (BaseFragment) HandlerFragment.getInstance().changeToFragment(R.id.fragment_table_positions);
            bundle.putSerializable(Constants.OPERATION_TYPE,ViewType.POSITIONS_VIEW);
        }
        bundle.putInt(Constants.PARENT_ID,favorite.getCategory().getId());
        fragment.setArguments(bundle);
    }

    public void onClickFavorite(Favorite favorite) {
        if (favorite != null){
            favoriteViewModel.deleteFavorite(favorite);
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

}
