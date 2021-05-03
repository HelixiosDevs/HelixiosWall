package com.helixios.helixioswall;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helixios.helixioswall.adapters.RecyclerViewAdapter;
import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.model.SearchPhotos;
import com.helixios.helixioswall.networking.FlickrApi;
import com.helixios.helixioswall.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_frag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;
    private List<SearchPhotos> mSearchPhotos;
    private SearchPhotos foto ;
    private ArrayList<Photo> mPhotoList;

    public home_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static home_frag newInstance(String param1, String param2) {
        home_frag fragment = new home_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = v.findViewById(R.id.recycler_view_home);
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPhotoList = new ArrayList<>();

        FlickrApi flickrApi = RetrofitClient.getClient().create(FlickrApi.class);
        Call<SearchPhotos> call = flickrApi.getHomePhotos();

        Log.d("call", String.valueOf(call.request()));

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

                Log.d("foto", String.valueOf(foto.getPhotosNest().getPhotos_list().get(2).getUrl_z()));
                mPhotoList.addAll(foto.getPhotosNest().getPhotos_list());
                Log.i("foto", String.valueOf(mPhotoList.size()));
                mAdapter = new RecyclerViewAdapter(getContext(),mPhotoList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<SearchPhotos> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });


        //Log.e("foto3", String.valueOf(mPhotoList.size()));
        Log.i("foto3", String.valueOf(foto));


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}