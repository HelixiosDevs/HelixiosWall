package com.helixios.helixioswall;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.model.SearchPhotos;
import com.helixios.helixioswall.networking.FlickrApi;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link category_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class category_frag extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FlickrApi flickrApi;
    private SearchPhotos foto;
    private ArrayList<Photo> mPhotoList;
    public String tags, user_id,cat;

    public category_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment category_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static category_frag newInstance(String param1, String param2) {
        category_frag fragment = new category_frag();
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
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        CardView card_helix = v.findViewById(R.id.card_Helixios);
        card_helix.setOnClickListener(this);
        CardView card_mirage = v.findViewById(R.id.card_Natural);
        card_mirage.setOnClickListener(this);
        CardView card_light = v.findViewById(R.id.card_Cyber);
        card_light.setOnClickListener(this);
        CardView card_dark = v.findViewById(R.id.card_Assassin);
        card_dark.setOnClickListener(this);
        CardView card_control = v.findViewById(R.id.card_Control);
        card_control.setOnClickListener(this);
        CardView card_forza = v.findViewById(R.id.card_Forza);
        card_forza.setOnClickListener(this);
        CardView card_track = v.findViewById(R.id.card_Track);
        card_track.setOnClickListener(this);
        CardView card_assetto = v.findViewById(R.id.card_Assetto);
        card_assetto.setOnClickListener(this);
        CardView card_nier = v.findViewById(R.id.card_Nier);
        card_nier.setOnClickListener(this);
        CardView card_ds = v.findViewById(R.id.card_DS);
        card_ds.setOnClickListener(this);
        CardView card_hades = v.findViewById(R.id.card_Hades);
        card_hades.setOnClickListener(this);
        CardView card_witcher = v.findViewById(R.id.card_Witcher);
        card_witcher.setOnClickListener(this);
        CardView card_starctz = v.findViewById(R.id.card_Starctz);
        card_starctz.setOnClickListener(this);
        CardView card_godwar = v.findViewById(R.id.card_Godwar);
        card_godwar.setOnClickListener(this);
        CardView card_horizon = v.findViewById(R.id.card_Horizon);
        card_horizon.setOnClickListener(this);
        CardView card_border = v.findViewById(R.id.card_Border);
        card_border.setOnClickListener(this);
        CardView card_uncharted = v.findViewById(R.id.card_Uncharted);
        card_uncharted.setOnClickListener(this);
        CardView card_spiderman = v.findViewById(R.id.card_Spiderman);
        card_spiderman.setOnClickListener(this);
        CardView card_inside = v.findViewById(R.id.card_Inside);
        card_inside.setOnClickListener(this);
        CardView card_gta = v.findViewById(R.id.card_GTA);
        card_gta.setOnClickListener(this);
        CardView card_watchd = v.findViewById(R.id.card_Watch);
        card_watchd.setOnClickListener(this);
        CardView card_jcause = v.findViewById(R.id.card_JCause);
        card_jcause.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.card_Helixios:
                tags = null;
                user_id = "192835674@N07";
                cat = "Helixios";
                break;
            case R.id.card_Natural:
                tags = "natural";
                user_id = null;
                cat = "Natural";
                break;
//            case R.id.card_Light:
//                tags = "light";
//                user_id = null;
//                cat = "Light";
//                break;
//            case R.id.card_Dark:
//                tags = "dark";
//                user_id = null;
//                cat = "Dark";
//                break;
            case R.id.card_Cyber:
                tags = "cyberpunk";
                user_id = null;
                cat = "Cyberpunk 2077";
                break;

            case R.id.card_Assassin:
                tags = "assassin";
                user_id = null;
                cat = "Assassin's Creed";
                break;
            case R.id.card_Control:
                tags ="control";
                user_id = null;
                cat = "Control";
                break;
            case R.id.card_Forza:
                tags = "forza";
                user_id = null;
                cat = "Forza";
                break;
            case R.id.card_Track:
                tags ="trackmania";
                user_id = null;
                cat = "Trackmania";
                break;
            case R.id.card_Assetto:
                tags = "assetto";
                user_id = null;
                cat = "Assetto Corsa";
                break;
            case R.id.card_Nier:
                tags ="nier";
                user_id = null;
                cat = "Nier:Automata";
                break;
            case R.id.card_DS:
                tags ="deathstranding";
                user_id = null;
                cat = "Death Stranding";
                break;
            case R.id.card_Hades:
                tags = "hades";
                user_id = null;
                cat = "Hades";
                break;
            case R.id.card_Witcher:
                tags = "witcher";
                user_id = null;
                cat = "Witcher";
                break;
            case R.id.card_Starctz:
                tags = "star_citizen";
                user_id = null;
                cat = "Star Citizen";
                break;
            case R.id.card_Godwar:
                tags = "godofwar";
                user_id = null;
                cat = "God Of War";
                break;
            case R.id.card_Horizon:
                tags = "horizon_dawn";
                user_id = null;
                cat = "Horizon";
                break;
            case R.id.card_Border:
                tags = "borderlands";
                user_id = null;
                cat = "Borderlands";
                break;
            case R.id.card_Uncharted:
                tags = "uncharted";
                user_id = null;
                cat = "Uncharted";
                break;
            case R.id.card_Spiderman:
                tags = "spiderman";
                user_id = null;
                cat = "Spiderman";
                break;
            case R.id.card_Inside:
                tags = "inside";
                user_id = null;
                cat = "Inside";
                break;
            case R.id.card_GTA:
                tags = "gta";
                user_id = null;
                cat = "GTA";
                break;
            case R.id.card_Watch:
                tags = "watchdogs";
                user_id = null;
                cat = "WatchDogs";
                break;
            case R.id.card_JCause:
                tags = "justcause";
                user_id = null;
                cat = "Just Cause";
                break;
//            case R.id.card_half:
//                tags ="halflife";
//                user_id = null;
//                cat = "Half - Life";
//                break;


            default:
                tags = null;
                user_id =null;
                cat = null;
                break;
        }
        Intent intent = new Intent(view.getContext(), SelectedCategoryActivity.class);
        intent.putExtra("TAGS",tags);
        intent.putExtra("USER",user_id);
        intent.putExtra("CAT1",cat);
        Log.d("cat22", "onCreate: "+tags+" t--u "+user_id+cat);
        startActivity(intent);

    }

}