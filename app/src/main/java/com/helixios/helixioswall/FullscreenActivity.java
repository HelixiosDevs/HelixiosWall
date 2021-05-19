package com.helixios.helixioswall;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     *      * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (AUTO_HIDE) {
                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    ImageView logo;
    LottieAnimationView anim_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_HelixiosWall_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        logo = (ImageView) findViewById(R.id.logo_1);
        anim_logo = findViewById(R.id.animationView);
        ImageView invisi = findViewById(R.id.invisible);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        //getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(500));

        //Set Internet listener here

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_page = new Intent(FullscreenActivity.this, MainActivity.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(FullscreenActivity.this, logo, "shift_up");
                startActivity(main_page,options.toBundle());
            }
        });

        // Set up the user interaction to manually show or hide the system UI.


        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

//        if(!isOnline()) {
//            Toast.makeText(this, "Make sure your device is connected to Internet",Toast.LENGTH_LONG).show();
//            while(!isOnline()) {
//                continue;
//            }
//            Log.d("anim", "onCreate: "+isOnline());
//            anim_logo.setVisibility(View.GONE);
//            logo.setVisibility(View.VISIBLE);
//            logo.callOnClick();
//        }
//        boolean val = false;
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("anim", "run: "+isOnline());
//                while(!isOnline()) {
//                    Log.d("anim", "run-2: "+isOnline());
//                    continue;
//                }
//                boolean val = isOnline();
//                Log.d("anim", "run-3: "+isOnline());
//            }
//        });
//        t1.start();
//        while(t1.isAlive()) {
//            continue;
//        }
//        if(val) {
//            anim_logo.setVisibility(View.GONE);
//            logo.setVisibility(View.VISIBLE);
//            logo.callOnClick();
//        }

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Do something after 5s = 5000ms
//                anim_logo.setRepeatCount(0);
//                anim_logo.playAnimation();
//            }
//        }, 500);

//        anim_logo.setRepeatCount(0);
//        anim_logo.playAnimation();
        anim_logo.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                anim_logo.setVisibility(View.GONE);
                logo.setVisibility(View.VISIBLE);
                logo.callOnClick();
                super.onAnimationEnd(animation);
            }
        });

    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        anim_logo.setRepeatCount(0);
        anim_logo.playAnimation();
        super.onActivityReenter(resultCode, data);
    }

    @Override
    protected void onResume() {
        anim_logo.setRepeatCount(0);
        anim_logo.playAnimation();
        super.onResume();
    }

    @Override
    protected void onStop() {
        anim_logo.setVisibility(View.VISIBLE);
        logo.setVisibility(View.INVISIBLE);
        super.onStop();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

//    public boolean isOnline() {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int     exitValue = ipProcess.waitFor();
//            Log.i("anim", "isOnline: value prob"+exitValue);
//            return (exitValue == 1);
//        }
//        catch (IOException e)          { e.printStackTrace(); }
//        catch (InterruptedException e) { e.printStackTrace(); }
//
//        return false;
//    }
}