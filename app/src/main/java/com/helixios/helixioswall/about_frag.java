package com.helixios.helixioswall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link about_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class about_frag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public about_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment about_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static about_frag newInstance(String param1, String param2) {
        about_frag fragment = new about_frag();
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
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        LinearLayout helix_url = view.findViewById(R.id.linearLayout2);
        ImageView insta_url = view.findViewById(R.id.imageView4);
        ImageView facebook_url = view.findViewById(R.id.imageView5);
        ImageView youtube_url = view.findViewById(R.id.imageView6);
        ImageView tele_url = view.findViewById(R.id.imageView7);
        ImageView discord_url = view.findViewById(R.id.imageView8);
        LinearLayout linkedin_dt = view.findViewById(R.id.linkedin_dt);
        LinearLayout linkedin_ac = view.findViewById(R.id.linkedin_ac);
        Button contribute = view.findViewById(R.id.button2);

        helix_url.setOnClickListener(view1 -> gotoUrl("http://www.helixios-esports.tech/"));
        insta_url.setOnClickListener(view12 -> gotoUrl("https://www.instagram.com/helixios.corp/"));
        facebook_url.setOnClickListener(view13 -> gotoUrl("https://www.facebook.com/helixios.corp"));
        youtube_url.setOnClickListener(view14 -> gotoUrl("https://www.youtube.com/HelixiosCorp"));
        tele_url.setOnClickListener(view15 -> gotoUrl("https://t.me/helixioscorp"));
        discord_url.setOnClickListener(view16 -> gotoUrl("https://discord.com/invite/tpNjxdr"));
        linkedin_dt.setOnClickListener(view17 -> gotoUrl("https://www.linkedin.com/in/divyansh-tripathi-in/"));
        linkedin_ac.setOnClickListener(view18 -> gotoUrl("https://www.linkedin.com/in/aditya-chaudhary-50a680a6/"));
        contribute.setOnClickListener(view19 -> gotoUrl("https://www.flickr.com/groups/14741456@N25/discuss/72157719228128509/"));

        return view;
    }

    private void gotoUrl(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}