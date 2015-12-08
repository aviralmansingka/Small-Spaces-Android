package com.example.aviral.ss;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private CustomPagerAdapter mPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private CustomViewPager mViewPager;
    private Button bGallery;
    private Button bMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        bGallery = (Button) findViewById(R.id.bGallery);
        bMap = (Button) findViewById(R.id.bMap);

        bGallery.setOnClickListener(this);
        bMap.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bGallery:
                mViewPager.setCurrentItem(1);
                bGallery.setBackgroundColor(Color.BLACK);
                bGallery.setTextColor(Color.WHITE);
                bMap.setBackgroundColor(Color.WHITE);
                bMap.setTextColor(Color.BLACK);
                break;
            case R.id.bMap:
                mViewPager.setCurrentItem(0);
                bMap.setBackgroundColor(Color.BLACK);
                bMap.setTextColor(Color.WHITE);
                bGallery.setBackgroundColor(Color.WHITE);
                bGallery.setTextColor(Color.BLACK);
                break;

        }
    }
}
