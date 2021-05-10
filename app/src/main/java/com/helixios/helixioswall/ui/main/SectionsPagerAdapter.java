package com.helixios.helixioswall.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.helixios.helixioswall.about_frag;
import com.helixios.helixioswall.category_frag;
import com.helixios.helixioswall.favour_frag;
import com.helixios.helixioswall.home_frag;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new home_frag();
                break;
            case 1:
                fragment = new category_frag();
                break;
            case 2:
                fragment = new favour_frag();
                break;
            case 3:
                fragment = new about_frag();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}