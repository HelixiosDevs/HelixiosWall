package com.helixios.helixioswall;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.helixios.helixioswall.database.HelixDatabase;
import com.helixios.helixioswall.ui.main.SectionsPagerAdapter;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    SectionsPagerAdapter sectionsPagerAdapter;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo_1);
        logo.setVisibility(View.VISIBLE);
        View shadow = findViewById(R.id.dropshadow_main);
        //Picasso.get().load("https://farm66.staticflickr.com/65535/51157530838_c51d02b22b.jpg").into(logo);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.setDuration(1000);
//        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(500));
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                shadow.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        final int[] TAB_ICONS = new int[]{ R.drawable.home, R.drawable.category, R.drawable.heart,R.drawable.about};
        for(int i=0; i<4; i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            tab.setIcon(TAB_ICONS[i]);
        }
    }

    @Override
    public void onBackPressed() {
        if(tabs.getSelectedTabPosition() != 0)
        {
            //sectionsPagerAdapter.getItem(1);
            tabs.selectTab(tabs.getTabAt(0));
        }
        else{
            this.moveTaskToBack(true);
            super.onBackPressed();
        }
    }
}