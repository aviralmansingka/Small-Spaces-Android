package com.example.aviral.ss;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                return new GalleryFragment();

            case 1:
                return new MapFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        return 2;
    }
}
