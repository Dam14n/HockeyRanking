package com.wip.hockey.fragment;

import android.arch.lifecycle.LifecycleFragment;


public class FavoriteFragment extends LifecycleFragment implements Tageable {
/*
    @BindView(R.id.fragment_favorite_recycler)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(),container,false);
        ButterKnife.bind(this,view);
        getFavorites();
        setRecyclerView();
        return view;
    }

    private void getFavorites(){
        SharedPreferences sharedPrefs = this.getContext().getSharedPreferences("Favorite", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Favorite" , null);
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        ArrayList data = gson.fromJson(json, type);
       // this.setContent(data);
    }

    public void setRecyclerView(){
        //FavoriteAdapter adapter = new FavoriteAdapter(this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);
    }



    protected int getLayoutResourceId() {
        return R.layout.fragment_list_favorite;
    }
*/
@Override
public String getTAG() {
    return null;
}
}
