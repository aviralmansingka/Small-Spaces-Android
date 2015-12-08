package com.example.aviral.ss;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by Aviral on 11/24/2015.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    Activity context;
    public CustomPagerAdapter(FragmentManager supportFragmentManager, Activity context) {
        super(supportFragmentManager);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MapFragment();

            case 1:
                return new GalleryFragment();
        }
        return null;

    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Object fragment = super.instantiateItem(container, position);
        try {
            final Field saveFragmentStateField = Fragment.class.getDeclaredField("mSavedFragmentState");
            saveFragmentStateField.setAccessible(true);
            final Bundle savedFragmentState = (Bundle) saveFragmentStateField.get(fragment);
            if (savedFragmentState != null) {
                savedFragmentState.setClassLoader(Fragment.class.getClassLoader());
            }
        } catch (Exception e) {
            Log.w("CustomFragmentAdapter", "Could not get mSavedFragmentState field: " + e);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
