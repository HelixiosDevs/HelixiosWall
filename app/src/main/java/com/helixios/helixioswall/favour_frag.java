package com.helixios.helixioswall;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.helixios.helixioswall.adapters.RecyclerViewAdapter;
import com.helixios.helixioswall.adapters.RecyclerViewAdapterFavourite;
import com.helixios.helixioswall.database.HelixDatabase;
import com.helixios.helixioswall.model.Photo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favour_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favour_frag extends Fragment implements RecyclerViewAdapter.OnPhotoListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapterFavourite mFaveAdapter;
    private ArrayList<Photo> mPhotoList;

    public favour_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favour_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static favour_frag newInstance(String param1, String param2) {
        favour_frag fragment = new favour_frag();
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
        View v = inflater.inflate(R.layout.fragment_favour, container, false);

        mRecyclerView = v.findViewById(R.id.recycler_view_fave);
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPhotoList = new ArrayList<>();
        mFaveAdapter = new RecyclerViewAdapterFavourite(getContext(),mPhotoList,this);
        mRecyclerView.setAdapter(mFaveAdapter);

        Thread thread = new Thread(() -> {
            mPhotoList.addAll(HelixDatabase.getInstance(getContext()).favDao().getFavourite());
            mFaveAdapter.notifyDataSetChanged();
        });
        thread.start();

        return v;
    }

    @Override
    public void onPhotoClick(int position) {
        ImageView imageView = getView().findViewById(R.id.item_fave_imageView);
        Intent intent = new Intent(imageView.getContext(), PhotoActivity.class);
        intent.putExtra("photo", mPhotoList.get(position));
        startActivity(intent);
    }
}