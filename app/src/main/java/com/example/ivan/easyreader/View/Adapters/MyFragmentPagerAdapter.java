package com.example.ivan.easyreader.View.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.ivan.easyreader.View.Fragments.PageFragment;

/**
 * Created by Myryk on 01.05.2017.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private int PAGE_COUNT = 1;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return PageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public void increasePageCount() {
        PAGE_COUNT++;
        notifyDataSetChanged();
    }
}