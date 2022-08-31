package com.helixios.helixioswall;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.helixios.helixioswall.ui.main.SectionsPagerAdapter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    SectionsPagerAdapter sectionsPagerAdapter;
    TabLayout tabs;
//    ReviewManager manager;
//    private SharedPreferences prefs;
//    private SharedPreferences.Editor editor;
//    private int totalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo_1);
        logo.setVisibility(View.VISIBLE);
        View shadow = findViewById(R.id.dropshadow_main);

//        manager = ReviewManagerFactory.create(MainActivity.this);

//        Log.d("counter2", "onCreate: "+reviewInfo.toString());
//        prefs = getPreferences(Context.MODE_PRIVATE);
//        editor = prefs.edit();
//        totalCount = prefs.getInt("counter", 0);
//        totalCount++;
//        editor.putInt("counter", totalCount);
//        editor.apply();
//        Log.d("counter", "onCreate: "+totalCount);
//        if((totalCount+2)%4 ==0  ) {
////            Task<ReviewInfo> request = manager.requestReviewFlow();
////            request.addOnCompleteListener(task -> {
////                if (task.isSuccessful()) {
////                    // We can get the ReviewInfo object
////                    reviewInfo[0] = task.getResult();
////                    Task<Void> flow = manager.launchReviewFlow(MainActivity.this, reviewInfo[0]);
////                    flow.addOnSuccessListener(result -> {
////                    });
////                        // The flow has finished. The API does not indicate whether the user
////                        // reviewed or not, or even whether the review dialog was shown. Thus, no
////                        // matter the result, we continue our app flow.
////                }
////            });
//            Log.d("counter", "onCreate: mod4");
//        }

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
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if(isOnline()) {
//
//                }
//            }
//        });
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
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            Log.i("anim", "isOnline: value prob"+exitValue);
            return (exitValue == 1);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}