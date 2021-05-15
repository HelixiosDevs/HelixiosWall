package com.helixios.helixioswall;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Parcelable;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEventSource;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.helixios.helixioswall.adapters.RecyclerViewAdapter;
import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.model.SearchPhotos;
import com.helixios.helixioswall.networking.FlickrApi;
import com.helixios.helixioswall.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_frag extends Fragment implements RecyclerViewAdapter.OnPhotoListener {

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
    private FlickrApi flickrApi;

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
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = v.findViewById(R.id.recycler_view_home);
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mPhotoList stores all the instances of Photos
        mPhotoList = new ArrayList<>();

        flickrApi = RetrofitClient.getClient().create(FlickrApi.class);
        Call<SearchPhotos> call = flickrApi.getHomePhotos();
        mAdapter = new RecyclerViewAdapter(getContext(),mPhotoList,this);
        mRecyclerView.setAdapter(mAdapter);
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

                Log.d("foto", String.valueOf(foto.getPhotosNest().getPhotos_list().get(0).getUrl_z()));
                mPhotoList.addAll(foto.getPhotosNest().getPhotos_list());
                Log.i("foto", String.valueOf(mPhotoList.size()));
                Collections.shuffle(mPhotoList);
                //Collections.sort(mPhotoList,compareByRatio);
                Log.d("foto5","working");
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchPhotos> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onPhotoClick(int position) {
        Log.d("foto", "onPhotoClick: is working");
        ImageView imageView = getView().findViewById(R.id.item_home_imageView);
        Intent intent = new Intent(imageView.getContext(), PhotoActivity.class);
        intent.putExtra("photo", mPhotoList.get(position));

        imageView.setTransitionName("sharedPhotoTransition"+mPhotoList.get(position).getId());
        Log.d("foto8", imageView.getTransitionName());

//        FragmentManager fragmentManager = getParentFragmentManager();
//        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
//        fragTransaction.addSharedElement(imageView, imageView.getTransitionName());
//        fragTransaction.setReorderingAllowed(true);
//        fragTransaction.replace(R.id.fragment_placeholder, fragment, VIEWPAGER_FRAGMENT);
//        fragTransaction.addToBackStack(null);
//        fragTransaction.commit();

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) imageView.getContext(),imageView,"sharedPhotoTransition"+mPhotoList.get(position).getId());
//        Fade explode = new Fade();
//        View decor = getActivity().getWindow().getDecorView();
//        explode.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
//        explode.excludeTarget(android.R.id.statusBarBackground, true);
//        explode.excludeTarget(android.R.id.navigationBarBackground, true);
//        explode.excludeTarget(imageView,true);
//        explode.setDuration(1000);
//        getActivity().getWindow().setEnterTransition(explode);
//        getActivity().getWindow().setExitTransition(explode);

        getActivity().getWindow().setSharedElementEnterTransition(new ChangeBounds().addTarget(imageView).setStartDelay(500).setDuration(1000));
        ActivityCompat.startActivity(imageView.getContext(), intent,options.toBundle());
    }

//This comparison is only working partially
//    Comparator<Photo> compareByRatio = (p1, p2) -> {
//        if (p1.getWidth_z()==0 ||p2.getWidth_z()==0)
//            return 1;
//        Log.d("cmp", String.valueOf(p1.getWidth_z())+"-"+String.valueOf(p1.getHeight_z()));
//        double r1 = p1.getHeight_z()/p1.getWidth_z();
//        double r2 = p2.getHeight_z()/p2.getWidth_z();
//        if (r1>r2)
//            return 1;
//        else
//            return 0;
//    };
}

