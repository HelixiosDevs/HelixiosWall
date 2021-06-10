package com.helixios.helixioswall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.helixios.helixioswall.adapters.RecyclerViewAdapter;
import com.helixios.helixioswall.adapters.RecyclerViewAdapterFavourite;
import com.helixios.helixioswall.adapters.RecyclerViewCategoryAdapter;
import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.model.SearchPhotos;
import com.helixios.helixioswall.networking.FlickrApi;
import com.helixios.helixioswall.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;

public class SelectedCategoryActivity extends AppCompatActivity implements RecyclerViewAdapter.OnPhotoListener {

    private RecyclerView mRecyclerView;
    private LinearLayout no_net_cat;
    private SwipeRefreshLayout refreshCat;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewCategoryAdapter mCatAdapter;
    private ArrayList<Photo> mPhotoList;
    FlickrApi flickrApi;
    SearchPhotos foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_category);

        String tags=null ,user_id=null, category=null;
        if(getIntent().hasExtra("TAGS") || getIntent().hasExtra("USER") || getIntent().hasExtra("CAT1")) {
             tags = getIntent().getStringExtra("TAGS");
             user_id = getIntent().getStringExtra("USER");
             category = getIntent().getStringExtra("CAT1");
        }
        else {
            finish();
        }

        mRecyclerView = findViewById(R.id.recycler_view_cat);
        no_net_cat = findViewById(R.id.no_net_cat);
        refreshCat = findViewById(R.id.swipeRefresh_cat);
        ImageView cat_logo = findViewById(R.id.logo_cat);
        TextView cat_text = findViewById(R.id.cat_text);
        AppBarLayout appBar_Cat = findViewById(R.id.appbar_cat);
        mLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPhotoList = new ArrayList<>();
        mCatAdapter = new RecyclerViewCategoryAdapter(this,mPhotoList,this);
        mRecyclerView.setAdapter(mCatAdapter);
        refreshCat.setRefreshing(true);

        flickrApi = RetrofitClient.getClient().create(FlickrApi.class);
        Call<SearchPhotos> call = flickrApi.getCategoryPhotos(tags,user_id);

        Log.i("cat22", call.request().toString());

        call.enqueue(new Callback<SearchPhotos>() {
            @Override
            public void onResponse(Call<SearchPhotos> call, Response<SearchPhotos> response) {
                if (!response.isSuccessful()) {
                    Log.d("API", String.valueOf(response.code()));
                    Log.d("suces", String.valueOf(response.isSuccessful()));
                }
//                mSearchPhotos = new ArrayList<>(response.body());
//                for(SearchPhotos pics:mSearchPhotos) {
//                    mPhotoList.addAll(pics.getPhotosNest().getPhotos_list());
//                }
                foto = response.body();

                //Log.d("foto", String.valueOf(foto.getPhotosNest().getPhotos_list().get(0).getUrl_z()));
                mPhotoList.addAll(foto.getPhotosNest().getPhotos_list());
                Log.i("foto", String.valueOf(mPhotoList.size()));
                Collections.shuffle(mPhotoList);
                //Collections.sort(mPhotoList,compareByRatio);
                Log.d("foto5","working");
                mCatAdapter.notifyDataSetChanged();
                refreshCat.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<SearchPhotos> call, Throwable t) {
                Log.e("Err", "onFailure: "+t.toString() );
                no_net_cat.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                refreshCat.setRefreshing(false);
            }
        });
        refreshCat.setOnRefreshListener(() -> {
            mPhotoList.clear();
            call.clone().enqueue(new Callback<SearchPhotos>() {
                @Override
                public void onResponse(Call<SearchPhotos> call1, Response<SearchPhotos> response) {
                    foto = response.body();

                    mRecyclerView.setVisibility(View.VISIBLE);
                    no_net_cat.setVisibility(View.GONE);

                    mPhotoList.addAll(foto.getPhotosNest().getPhotos_list());
                    Collections.shuffle(mPhotoList);
                    //Collections.sort(mPhotoList,compareByRatio);
                    mCatAdapter.notifyDataSetChanged();
                    refreshCat.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<SearchPhotos> call1, Throwable t) {
                }
            });
            refreshCat.setRefreshing(false);
        });

        switch (category) {
            case "Helixios":
                cat_logo.setImageResource(R.drawable.helixios_excl);
                appBar_Cat.setBackgroundResource(R.drawable.cat_top_helixios);
                break;
//            case "Mirage":
//                cat_logo.setImageResource(R.drawable.mirage_excl);
//                appBar_Cat.setBackgroundResource(R.drawable.cat_top_mirage);
//                break;
            case "Natural":
                cat_logo.setVisibility(View.GONE);
                cat_text.setVisibility(View.VISIBLE);
                cat_text.setText(category);
                appBar_Cat.setBackgroundResource(R.drawable.natural_cat_top);
                break;
//            case "Dark":
//                cat_logo.setVisibility(View.GONE);
//                cat_text.setVisibility(View.VISIBLE);
//                cat_text.setText(category);
//                appBar_Cat.setBackgroundResource(R.drawable.dark_top_shade);
//                break;
            case "Cyberpunk 2077":
                cat_logo.setImageResource(R.drawable.cyberpunk_lg);
                break;
            case "Assassins Creed":
                cat_logo.setImageResource(R.drawable.ac_lg);
                break;
            case "Control":
                cat_logo.setImageResource(R.drawable.control_lg);
                break;
            case "Forza":
                cat_logo.setImageResource(R.drawable.forza_lg);
                break;
            case "Trackmania":
                cat_logo.setImageResource(R.drawable.trackmania_lg);
                break;
            case "Assetto Corsa":
                cat_logo.setImageResource(R.drawable.assetto_lg);
                break;
//            case "Half - Life":
//                cat_logo.setImageResource(R.drawable.halflife_lg);
//                break;
            case "Inside":
                cat_logo.setImageResource(R.drawable.inside_lg);
                cat_logo.setPadding(16,16,16,16);
                break;
            case "Nier:Automata":
                cat_logo.setImageResource(R.drawable.nier_lg);
                break;
            case "Death Stranding":
                cat_logo.setImageResource(R.drawable.deathstrand_lg);
                break;
            case "Hades":
                cat_logo.setImageResource(R.drawable.hades_lg);
                break;
            case "Witcher":
                cat_logo.setImageResource(R.drawable.witcher_lg);
                break;
            case "Star Citizen":
                cat_logo.setImageResource(R.drawable.starcitizen_lg);
                break;
            case "God Of War":
                cat_logo.setImageResource(R.drawable.godwar_lg);
                break;
            case "Horizon":
                cat_logo.setImageResource(R.drawable.horizon_lg);
                break;
            case "Borderlands":
                cat_logo.setImageResource(R.drawable.borderlands_lg);
                break;
            case "Uncharted":
                cat_logo.setImageResource(R.drawable.uncharted_lg);
                break;
            case "Spiderman":
                cat_logo.setImageResource(R.drawable.spiderman_lg);
                break;
            case "GTA":
                cat_logo.setImageResource(R.drawable.gta_lg);
                break;
            case "WatchDogs":
                cat_logo.setImageResource(R.drawable.watchdogs_lg);
                break;
            case "Just Cause":
                cat_logo.setImageResource(R.drawable.justcause_lg);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        mPhotoList.clear();
        mCatAdapter.notifyDataSetChanged();
        super.onDestroy();
    }

    @Override
    public void onPhotoClick(int position) {
        ImageView imageView = findViewById(R.id.item_home_imageView);
        Intent intent = new Intent(imageView.getContext(), PhotoActivity.class);
        intent.putExtra("photo", mPhotoList.get(position));
        startActivity(intent);
    }
}