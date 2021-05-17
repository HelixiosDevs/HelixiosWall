package com.helixios.helixioswall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
        mLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPhotoList = new ArrayList<>();
        mCatAdapter = new RecyclerViewCategoryAdapter(this,mPhotoList,this);
        mRecyclerView.setAdapter(mCatAdapter);

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
//                mSearchPhotos = new ArraList<>(response.body());
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
            }

            @Override
            public void onFailure(Call<SearchPhotos> call, Throwable t) {
                Log.e("Err", "onFailure: "+t.toString() );
            }
        });
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